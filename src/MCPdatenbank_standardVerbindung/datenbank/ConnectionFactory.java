/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.datenbank;

import MCPdatenbank_standardVerbindung.datenbank.CConInfo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marian
 */
/*
    Stattische variablen für datenbankzugriff
*/
public class ConnectionFactory implements IConnectionFactory 
{   
    public ConnectionFactory(CConInfo verbindungsinformationen) throws Exception
    {
        if(connectionInfo == null)
            throw new NullPointerException("connectionInfo");
        
        this.connectionInfo = verbindungsinformationen;
        ladeDatenbanktreiber();
    }   
    
    @Override
    public Connection GetConnection() throws SQLException 
    {
        return getConnection(connectionInfo.getUrl(), 
                connectionInfo.getBenutze(), connectionInfo.getPasswort());
    }
    
    @Override
    @Deprecated
    public Statement GetStatement() throws SQLException
    {
        return GetConnection().createStatement();
    }
      
   
    private  void ladeDatenbanktreiber() throws Exception
    {
          
        try {
            Class.forName(connectionInfo.getDatenbanktreiber());
        } catch (ClassNotFoundException ex) {
            throw new Exception("Treiber kann nicht geladen werden \n" + ex.getMessage());
        }
       
    }  
        
    private Connection getConnection(String url, String benutzer, String passwort) throws SQLException
    {
      Connection c = null;
     
      c = DriverManager.getConnection(url, benutzer, passwort);
           
      return c;
    } 
    
       
    private CConInfo connectionInfo;
}
