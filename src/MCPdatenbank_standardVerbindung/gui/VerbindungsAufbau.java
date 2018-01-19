/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.gui;

import MCPdatenbank_standardVerbindung.datenbank.CConInfo;
import MCPdatenbank_standardVerbindung.datenbank.CDatenbankverbinder;
import MCPdatenbank_standardVerbindung.datenbank.CXmlLeser;
import MCPdatenbank_standardVerbindung.starter.IConnected;
import com.sun.jndi.ldap.Connection;
import java.io.File;
import javax.swing.JOptionPane;

/**ile 
 *
 * @author marian
 */
public class VerbindungsAufbau {
    
    private File verzeichnis;
    private IConnected parent;
    private IPrinter printer;
    
    public VerbindungsAufbau(File verzeichnis, IConnected parent, IPrinter printer)
    {
        if(verzeichnis==null)
        {
               verzeichnis = new File(System.getProperty("user.dir"));
        }
        
        this.verzeichnis = verzeichnis;
        this.parent = parent;
        this.printer = printer;
    }        
    
    public File getVerzeichnis()
    {
        return this.verzeichnis;
    }        
    
    public  boolean macheVerbindung()
    {
        /**
         *@f wenn f==null dann wird App-ordner genommen
         * sucht nach mcp xml und übergibt diese den dtanbankobjekten oder 
         * erzeugt diese falls noch nicht vorhanden
         */
          
          
          /* 
          Prüfen ob  mcpXml vorhanden
          */
          File mcpXML = new File(verzeichnis.getAbsolutePath() + "/mcp.xml");
      
          
          if(mcpXML.exists() && mcpXML.canRead())
          {
              if(!ladeVerb(mcpXML))
              {
                  XMLkonfig();
              }else
              {
                  parent.verbindungErstellt();
              }    
          } else
          {
              printer.Print("Keine mcp.xml vorhanden");
              XMLkonfig();
          }    
          
        
        
        return false;
    }        
    
    private  void XMLkonfig()
    {
        try
        {    
            GMcpXmlErsteller ersteller = new GMcpXmlErsteller(this);
        }finally
        {
            
        }    
    }        
    
    private  boolean ladeVerb(File xml)
    {     
           CXmlLeser leser = new CXmlLeser(xml);
           CConInfo info = leser.leseAusXML();
           
           if(info == null)
           {
                printer.Print("mcp.xml hat Syntaxfehler");
                return false;
           }    
           
           CDatenbankverbinder dcon = new CDatenbankverbinder(info);
           if(!dcon.verbindungAufbauenServer())
           {
               printer.Print("Server nicht erreichbar!"); 
              
               return false;
           } 
           
           if(!dcon.isDatenbankVorhanden())
           {             
                printer.Print("Datenbank kann nicht erstellt werden!"); 
                return false;    
           }    
           
           
           return dcon.verbindungAufbauenDb();
      
    }        
}
