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
    
    private String urlServer; 
    private String datenbank;
    private String benutze;
    private String passwort;
    private String datenbanktreiber;

    /*
    Properties
    */
    public String getDatenbanktreiber() {
        return datenbanktreiber;
    }

    public String getUrlServer() {
        return urlServer;
    }

    public String getDatenbank() {
        return datenbank;
    }

    public String getBenutze() {
        return benutze;
    }
    
    public String getPasswort()
    {
       return passwort;
    }    

    public String getUrlDatenbank()
    {
        return this.urlServer + "/" + this.datenbank;
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
            
        this.benutze = benutzer;
        this.passwort = passwort;
        this.datenbanktreiber = datenbanktreiber;
        this.datenbank = datenbanknameInRichtigesFormat(datenbank);
        this.urlServer = urlInRichtigesFormat(urlServer);  
    }    
    /*
    private Methoden
    */
    
    private String urlInRichtigesFormat(String str)
    {
       /*
        Schneidet letzte Separatoren ab
        */
        String result = "";
        str = str.trim();
        int pos;
        
        for(pos = str.length()-1; pos >= 0; pos--)
        {
            if(str.charAt(pos) != '/' && str.charAt(pos) !='\\')
            {
              break;
            }                
        } 
        
        for(int z = 0; z<=pos;  z++)
        {
            
           result += str.charAt(z);
        }    
        
        return result;
    }
    
    private String datenbanknameInRichtigesFormat(String str)
    {
       /*
        schneidet führende Separatoren ab
        */
        String result = "";
        str = str.trim();
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) != '/' && str.charAt(i) != '\\')
            {
                result+= str.charAt(i);
            }
        }   
        
        return result;
    }
    
       
    
    
    
    
    /*
    public methoden
    */

}   
