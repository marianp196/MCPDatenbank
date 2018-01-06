/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.gui;

import MCPdatenbank_standardVerbindung.datenbank.CConInfo;
import MCPdatenbank_standardVerbindung.datenbank.CDatenbankverbinder;
import MCPdatenbank_standardVerbindung.datenbank.CXmlLeser;
import java.io.File;
import javax.swing.JOptionPane;
import MCPdatenbank_standardVerbindung.starter.Starter;

/**ile 
 *
 * @author marian
 */
public class CVerbindungFactory {
    
    private File verzeichnis;
    private Starter parent;
    
    public CVerbindungFactory(File verzeichnis, Starter parent)
    {
        if(verzeichnis==null)
        {
               verzeichnis = new File(System.getProperty("user.dir"));
        }
        
        this.verzeichnis = verzeichnis;
        this.parent = parent;
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
              JOptionPane.showMessageDialog(null, "Keine mcp.xml vorhanden", "Fehler!", JOptionPane.OK_CANCEL_OPTION);
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
               JOptionPane.showMessageDialog(null, "mcp.xml hat Syntaxfehler", "Fehler!", JOptionPane.OK_CANCEL_OPTION);
                return false;
           }    
           
           CDatenbankverbinder dcon = new CDatenbankverbinder(info);
           if(!dcon.verbindungAufbauenServer())
           {
              JOptionPane.showMessageDialog(null, "Server nicht erreichbar!", "Fehler!", JOptionPane.OK_CANCEL_OPTION); 
              
               return false;
           } 
           
           if(!dcon.isDatenbankVorhanden())
           {
             if(!dcon.datenbankErstellen())
             {
                JOptionPane.showMessageDialog(null, "Datenbank kann nicht erstellt werden!", "Fehler!", JOptionPane.OK_CANCEL_OPTION); 
                return false;
             }    
           }    
           
           return dcon.verbindungAufbauenDb();
      
    }        
}
