/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.datenbank;

import java.io.File;
import java.io.FileWriter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author marian
 */
public class CXmlErsteller {
    public File xml = null;
    public CConInfo info = null;
    
    public CXmlErsteller(File verzeichnis, CConInfo info){
                
        this.xml = verzeichnis;
        this.info = info;
    }
    
    private  String chiffrePasswort(String str)
    {
        String result="";
        char c;
        
        for(int i = 0; i<str.length(); i++ )
        {
          c = str.charAt(i);
          c = (char)(c + 2);
          result += String.valueOf(c);
          
        }  
        
        return result;
    }  
    
     private File erstelleXML(File verzeichnis) throws Exception
    {
       File result = null;
              
       if(!verzeichnis.exists())
       {
          if(!verzeichnis.mkdir())
          {
             return null; 
          }              
       }
       
       String sep = "";
       
       if(verzeichnis.getAbsolutePath().charAt(verzeichnis.getAbsolutePath().length()-1)!='/')
       {
          sep = "/"; 
       }    
       
       result = new File(verzeichnis.getAbsolutePath() + sep + "mcp.xml");
       if(!result.exists())
       {    
           if(result.createNewFile())
           {    
              return result;
           }    
           else
           {
              return null;
           }    
        
        } 
       return result;
    }   
    
    public boolean saveToXML()  
    {
       File fxml;
       FileWriter io;
        try
        {
             fxml = erstelleXML(this.xml);
             io = new FileWriter(fxml);
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
           return false;
        }
        
        /*
        Xml-Struktur festlegen
        */
        Element root = new Element("mcp");
        Element datenfeld;
        
        datenfeld = new Element("url");
        datenfeld.setContent(new Text(info.getUrlServer().trim()));
        datenfeld.addContent(datenfeld);
        
        datenfeld = new Element("datenbank");
        datenfeld.setContent(new Text(info.getDatenbank().trim()));
        root.addContent(datenfeld);
        
        datenfeld = new Element("benutzer");
        datenfeld.setContent(new Text(info.getBenutze().trim()));
        root.addContent(datenfeld);
        
        datenfeld = new Element("passwort");
        datenfeld.setContent(new Text(chiffrePasswort(info.getPasswort().trim())));
        root.addContent(datenfeld);
        
        datenfeld = new Element("treiber");
        datenfeld.setContent(new Text(info.getDatenbanktreiber().trim()));
        root.addContent(datenfeld);
        /*
        Dokument speichern
        */
        try
        {    
            Document xml = new Document(root);
            XMLOutputter out = new XMLOutputter();
            out.output(xml, io);
        }catch(Exception e)
        {
            return false;
        }    
        return true;
    }
       
    
        
}
