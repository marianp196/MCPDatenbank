/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.datenbank;

import java.io.File;
import java.io.FileWriter;
//import javax.xml.bind.Element;
import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;

/**
 *
 * @author marian
 */
public class CConInfo {
    public static String DRIVERMYSQL = "com.mysql.jdbc.Driver";
    public static String[] SQLTREIBER = {DRIVERMYSQL};
  
    /*
    Properties
    */
    public String getDatenbanktreiber() {
        return datenbanktreiber;
    }
    

    public String getBenutze() {
        return benutzer;
    }
    
    public String getPasswort()
    {
       return passwort;
    }    

    public String getUrl()
    {
        return this.url;
    }        
    
    /*
    konstruktor
    */
    
    public CConInfo(String urlServer, String datenbank, String benutzer, String passwort, String datenbanktreiber)
    {
       initMember( urlServer,  datenbank,  benutzer,  passwort,  datenbanktreiber);
    } 
          
    
    private void initMember(String urlServer, String datenbank, String benutzer, String passwort, String datenbanktreiber)
    {        
            
        this.benutzer = benutzer;
        this.passwort = passwort;
        this.datenbanktreiber = datenbanktreiber;
    
    }    
       
       
    private String url;
    private String benutzer;
    private String passwort;
    private String datenbanktreiber;   

}   
