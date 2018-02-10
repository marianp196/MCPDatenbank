/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Common.TestBase;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import commands.common.ISchemaInfo;
import commands.hsqldb.HSQLDBSchemaInfo;
import tables.EDataType;
import tables.Field;

/**
 *
 * @author marian
 */
public class HSQLDBSchemaInfoTest extends TestBase {

    public HSQLDBSchemaInfoTest() throws Exception {
        super();
    }   
    
    @Test
    public void TableExists_ShouldReturnTrue_IfTableExists() throws Exception
    {
        CreateSchemaForTest();
        ISchemaInfo sut = createSut();
        Assert.assertTrue(sut.TableExists("test"));
    }
    
    @Test
    public void TableExists_ShouldReturnFalse_IfTableDoesntExist() throws Exception
    {
        ISchemaInfo sut = createSut();
        Assert.assertFalse(sut.TableExists("test"));
    }
    
    @Test
    public void GetFields_ShouldReturnFields() throws Exception
    {
        CreateSchemaForTest();
        ISchemaInfo sut = createSut();
        Field[] fields = sut.GetFields("test");
        
        Field[] shouldFelder = new Field[3];
        shouldFelder[0] = new Field("id", EDataType.integer);
        shouldFelder[1] = new Field("text", EDataType.charString);
        shouldFelder[2] = new Field("zahl", EDataType.bigInt);
        
        Assert.assertArrayEquals(shouldFelder, fields);
        
    }
    
    private ISchemaInfo createSut() throws Exception
    {
        return new HSQLDBSchemaInfo(database);
    }
    
     public void CreateSchemaForTest() throws Exception
    {
        Statement query = connection.createStatement();
        try
        {
           //Hack :)
           query.executeQuery("select * from test");
        }catch(Exception e)
        {
            query = connection.createStatement();
            query.execute("create table test(id int not null, text varchar(50), zahl bigint, primary key(id))");
        }
    } 
  

}
