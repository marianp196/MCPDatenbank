/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.datenbank;

import MCPdatenbank_standardVerbindung.datenbank.CConInfo;
import java.sql.*;

/**
 *
 * @author marian
 */
/*
    Stattische variablen für datenbankzugriff
*/
public class CDatenbankverbinder implements IConnectionFactory {
    public static CDatenbankverbinder con = null;
    
    public CConInfo info = null;
    private Connection connectiondb = null;    //[ToDo] zwei verbindungen nonsense. verbindungsaufbau grundsätzlich überarbeiten
    private Connection connectionServer = null;
    private boolean ersteVerbindung = false; 
    
     @Override
    public Connection GetConnection() {
        return connectiondb;
    }
    
    @Override
    public Statement GetStatement()
    {
        Statement s = null;
        if(this.isConnectedDb())
        {
            try
            {    
                s = connectiondb.createStatement();
            }
            catch (Exception e)
            {
                s = null;
            }    
        } 
        return s;
    }
    
    
    public boolean isConnectedDb()
    {
        boolean result = false;
        try
        {
            result = connectiondb != null && !connectiondb.isClosed();
        }catch (Exception e)
        {
            result = false;
        }    
        
        return result;
        
    }        
    
    public boolean isConnectedServer()
    {
        boolean result = false;
        try
        {
            result = connectionServer != null && !connectionServer.isClosed();
        }catch (Exception e)
        {
            result = false;
        }    
        
        return result;
        
    }        
    public boolean isDatenbankVorhanden()
    {
        
        if(!this.isConnectedServer())
        {
            return false;
        }    
        
        Statement st;
        ResultSet rs;
        boolean gefunden = false;
        
        try
        {    
              st = connectionServer.createStatement();
              rs = st.executeQuery("show databases;");
              if(rs == null || rs.isClosed())
              {
                  return false;
              }    
              
              
              //rs.first();
              while(rs.next() && !gefunden)
              {
                 //System.out.println(rs.getString(1));
                 gefunden = rs.getString(1).equals(info.getDatenbank());
              }    
        }catch(Exception e)
        {
            return false;
        }    
        
        
        
        return gefunden;
    }        
   
    public boolean isErsteVerbindung() {
        return ersteVerbindung;
    }
    
    public boolean verbindungAufbauenDb()
    {
      return verbindungAufbauen("db", info.getUrlDatenbank());
    }  
    
     public boolean verbindungAufbauenServer()
    {
       return verbindungAufbauen("s", info.getUrlServer());
    }  
     
   
    
    public CDatenbankverbinder(CConInfo verbindungsinformationen)
    {
       info = verbindungsinformationen;
       ladeDatenbanktreiber();
       con = this;
    }        
    
    private  void ladeDatenbanktreiber()
    {
        try
        {      
            Class.forName(info.getDatenbanktreiber());
        }catch(Exception e)
        {
            System.out.println("Datenbanktreiber nicht geladen : " +  info.getDatenbanktreiber());
        }    
    }  
    
    
    private Connection getConnection(String url)
    {
      Connection c = null;
      try
      {    
        c = DriverManager.getConnection(url, info.getBenutze(), info.getPasswort());
      }
      catch(Exception e)
      {
          System.out.println("Connection kann nicht hergestellt werden:");
          System.out.println(e.getMessage());
      }    
      
      return c;
    } 
    
     private boolean verbindungAufbauen(String verbi, String url)
    {
       boolean result = false;
       Connection c;
       
       
       if(info != null)
       {
           c = getConnection(url); 
           try
           {    
            if(c != null && ! c.isClosed())
            {
                   result = true;
                   if(verbi.equals("s"))
                   {
                       connectionServer = c;
                   }  
                   if(verbi.equals("db"))
                   {
                       connectiondb = c;
                   }  
            }    
           }catch(Exception e)
           {
               result = false;
           }    
       }
       return result; 
    }        
   
}
