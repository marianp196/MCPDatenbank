
import checker.ISchemaChecker;
import checker.SchemaChecker;
import commands.common.ISchemaInfo;
import commands.common.ISqlBuilder;
import commands.hsqldb.HSQLDBCreateCommand;
import commands.hsqldb.HSQLDBSchemaInfo;
import datenbank.IDatabase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marian
 */
public class SchemaCheckerFactory {
    
    public static ISchemaChecker CreateMySqlSchemaChecker(IDatabase database) throws Exception
    {
        ISqlBuilder sqlBuilder = new  HSQLDBCreateCommand();
        ISchemaInfo schemaInfo = new HSQLDBSchemaInfo(database);
        return new SchemaChecker(schemaInfo, sqlBuilder, database);
    }
        
}
