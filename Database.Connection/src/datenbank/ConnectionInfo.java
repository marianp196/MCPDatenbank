/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

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
public class ConnectionInfo {
    public static String DRIVERMYSQL = "com.mysql.jdbc.Driver";
    public static String[] SQLTREIBER = {DRIVERMYSQL};
  
    public ConnectionInfo(String url, String benutzer, String passwort, String datenbanktreiber)
    {
        this.url = url;
        this.benutzer = benutzer;
        this.passwort = passwort;
        this.datenbanktreiber = datenbanktreiber;
    } 
   
    public String GetDatenbanktreiber() {
        return datenbanktreiber;
    }
    

    public String GetBenutzer() {
        return benutzer;
    }
    
    public String GetPasswort()
    {
       return passwort;
    }    

    public String GetUrl()
    {
        return this.url;
    }        
   
     
    private String url;
    private String benutzer;
    private String passwort;
    private String datenbanktreiber;   

}   
