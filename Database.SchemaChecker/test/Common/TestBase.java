package Common;


import datenbank.ConnectionInfo;
import datenbank.Database;
import datenbank.IDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marian
 */
public class TestBase {
     public TestBase() throws Exception
    {
        ConnectionInfo info = new ConnectionInfo("jdbc:hsqldb:file:./hallo.test", 
                "SA", "", "org.hsqldb.jdbcDriver");
        database = new Database(info);
        connection = database.GetConnection();
    }
        
    @Before
    public void ClearSchema() throws SQLException
    {
        Statement st = connection.createStatement();
        
        try {
            st.execute("drop table test");
        } catch (SQLException ex) {
            Logger.getLogger(TestBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected IDatabase database;
    protected Connection connection;
}
