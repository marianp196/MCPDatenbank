/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;


import checker.stringComparer.IStringComparer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import commands.common.ISchemaInfo;
import commands.common.ISqlBuilder;
import datenbank.IDatabase;
import java.sql.SQLException;
import java.sql.Statement;
import sun.reflect.annotation.AnnotationParser;
import tables.Field;
import tables.Table;
/**
 *
 * @author marian
 */
/*
 * ToDos
 * - CaseSensitivität beachten
 */
public class SchemaChecker implements ISchemaChecker {

    public SchemaChecker(ISchemaInfo schemaInfo, 
                         ISqlBuilder sqlBuilder, 
                         IDatabase database) throws Exception {
        this.schemaInfo = schemaInfo;
        this.sqlBuilder = sqlBuilder;       
        this.connection = database.GetConnection();
        
        tables = new ArrayList<>();
    }
    
    /**
     * Dient zur Definition des Schemas. Angwandt wird es durch aufruf von CheckAndGet
     * @param table
     * @throws Exception 
     */
    @Override
    public void AddTable(Table table) throws Exception {
        if(tables.stream().filter(t -> t.GetTableName().equals(table.GetTableName())).count() > 0)
            throw new Exception("Tabelle existiert in Schema bereits");
        
        tables.add(table);
    }

    @Override
    public void CheckAndCreate() throws Exception 
    {
        for(Table table : tables)
        {
            if(!schemaInfo.TableExists(table.GetTableName()))
                createTable(table);
            checkColumns(table);
        }
    }
    
    private void createTable(Table table) throws SQLException, Exception 
    {
        Statement query = connection.createStatement();
        String sql = sqlBuilder.CreateCommand(table.GetTableName(), table.GetPrimaryKeys());
        query.execute(sql);
    }
    
    private void checkColumns(Table table) throws Exception {
        Field[] fieldsFromDatabase = schemaInfo.GetFields(table.GetTableName());
        Field[] targetFields = table.GetFields();
        
        ArrayList<Field> fieldDBList = toArrayList(fieldsFromDatabase);
        
        for(Field field : targetFields)
        {
            if(fieldDBList.stream().filter(f -> f.getName().equals(field.getName())).count() == 0)
                createField(table.GetTableName(),field);
        }
    }

    private void createField(String table,Field field) throws SQLException {
        Statement query = connection.createStatement();
        String sql = sqlBuilder.AddCollumnCommand(table, field);
        query.execute(sql);
    }
    
    private <T> ArrayList<T> toArrayList(T[] fields) {
        ArrayList<T> ar_List = new ArrayList<>();
        for(T element : fields)
            ar_List.add(element);
        return ar_List;
    }
    
    private Collection<Table> tables;
    
    private ISchemaInfo schemaInfo;
    private ISqlBuilder sqlBuilder;
    private Connection connection; 

    
}
