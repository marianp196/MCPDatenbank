/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import datenbank.ConnectionInfo;
import datenbank.Database;
import datenbank.IDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.IJentity;
import service.JentityFactory;
import testDtos.JentitySelectDto;

/**
 *
 * @author marian
 */
public class JentitySelectTest {
    
    public JentitySelectTest() throws Exception 
    {
        ConnectionInfo info = new ConnectionInfo("jdbc:hsqldb:file:./testDB", 
                "SA", "", "org.hsqldb.jdbcDriver");
        database = new Database(info);
    }
      
    
    public void setUp(int n) throws Exception 
    {
        Connection con = database.GetConnection();
        try {
            con.createStatement().execute("drop table test");
        } catch (SQLException ex) {
            Logger.getLogger(JentitySelectTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con.createStatement().execute("CREATE TABLE test (id int, str varchar(100), zahl float, zahlGroß BigInT)");
       for(int i = 0; i < n; i++ )
       {
            Statement st = con.createStatement();
            st.execute("INSERT INTO test(id, str, zahl, zahlGroß) values (0, 'text', 0.23, 123); "
                + "INSERT INTO test(id, str, zahl, zahlGroß) values (1, 'text', 0.23, 123);"
                + "INSERT INTO test(id, str, zahl, zahlGroß) values (2, 'text', 0.23, 123);");
       }
       
        
    }
    
    @Test
    public void Select_ShouldSelectAll() throws Exception 
    {
        setUp(1);
        IJentity sut = createSut();
        Collection<JentitySelectDto> result = sut.Select("test", JentitySelectDto.class);
        Assert.assertEquals(3, result.size());
    }
    
    @Test
    public void Select_ShouldSelectAll_InTimeFrame() throws Exception 
    {
        setUp(1000);
        IJentity sut = createSut();
        long time = System.currentTimeMillis();
        Collection<JentitySelectDto> result = sut.Select("test", JentitySelectDto.class);
        Assert.assertEquals(3000, result.size());
        System.out.println("time");
        Assert.assertTrue(System.currentTimeMillis() - time < 1000);
    }
    
    private IJentity createSut() throws Exception
    {
        return JentityFactory.CreateInstance(database);
    }

    private IDatabase database;
}
