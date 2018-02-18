/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import datenbank.IDatabase;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marian
 */
public class SelectSql implements ISelectSql{

    public SelectSql(IDatabase database) throws Exception {
        connection = database.GetConnection();
    }    
    
    @Override
    public ResultSet SelectAll(String name) throws SQLException {
        Statement st = connection.createStatement();
        return st.executeQuery("select * from " + name);
    }
    
    Connection connection;
}
