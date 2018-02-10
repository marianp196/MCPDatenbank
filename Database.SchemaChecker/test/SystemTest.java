/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Common.TestBase;
import checker.ISchemaChecker;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import tables.EDataType;
import tables.Field;
import tables.Table;

/**
 *
 * @author marian
 */
public class SystemTest extends TestBase
{   
    public SystemTest() throws Exception 
    {
        super();
    }
    
    @Test
    public void CcheckAndCreate_ShouldCreateTable_IfDoesntExist() throws Exception
    {
        Table table = createTestTable();
        createTableInDb(table);
        
        Statement st = database.GetStatement();
        Assert.assertTrue(st.execute("select primKey1, primKey2, hallo from test"));
    }
    
    @Test
    public void CcheckAndCreate_ShouldCreateFields_IfTableExist() throws Exception
    {
        Table table = createTestTable();
        createTableInDb(table);
        
        table.AddField(new Field("varcharFeld300", EDataType.charString, 300));
        
        createTableInDb(table);
        
        Statement st = database.GetStatement();
        st.execute("select primKey1, primKey2, hallo, varcharFeld300 from test");
    }
    
     @Test
    public void CcheckAndCreate_ShouldCreateTable_IfOneExistsAllreadyExist() throws Exception
    {
        Table table = createTestTable();
        createTableInDb(table);
        
        Table table2 = new Table("test2");
        table2.AddPrimaryKey(new Field("id", EDataType.integer));
        createTableInDb(table2);
        Assert.assertTrue(connection.createStatement().execute("select id  from test2"));
    }

    private void createTableInDb(Table table) throws Exception {
        ISchemaChecker sut = createSut();
        sut.AddTable(table);
        sut.CheckAndCreate();
    }

    private Table createTestTable() throws Exception {
        Table table = new Table("test");
        table.AddPrimaryKey(new Field("primKey1", EDataType.integer));
        table.AddPrimaryKey(new Field("primKey2", EDataType.charString, 200));
        table.AddField(new Field("hallo", EDataType.integer));
        return table;
    }
    
    private ISchemaChecker createSut() throws Exception
    {
        return SchemaCheckerFactory.CreateMySqlSchemaChecker(database);
    }
}
