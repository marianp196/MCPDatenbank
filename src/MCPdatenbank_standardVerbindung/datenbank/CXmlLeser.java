/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.datenbank;

import java.io.File;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author marian
 */
public class CXmlLeser {
    
    private File xml = null;
    
    public CXmlLeser(File xml)
    {
        this.xml = xml;
    }        
    
    private  String dechiffrePasswort(String str)
    {
        String result="";
        char c;
        
        for(int i = 0; i<str.length(); i++ )
        {
          c = str.charAt(i);
          c = (char)(c - 2);
          result += String.valueOf(c);
          
        }  
        
        return result;
    }     
    
    public CConInfo leseAusXML()
    {
      CConInfo result;
        
      if(this.xml == null  || !this.xml.exists() || !this.xml.canRead() )
      {
          System.out.println("mcp.xml nicht vorhanden");
          return null;
      }    
      try
      {     
        SAXBuilder XMLleser = new SAXBuilder();
        Document XML = XMLleser.build(this.xml);
        Element root = XML.getRootElement();
      
        result = new CConInfo( root.getChildText("url"), 
              root.getChildText("datenbank"),  
              root.getChildText("benutzer"), 
              dechiffrePasswort(root.getChildText("passwort")),  
              root.getChildText("treiber"));    
      }catch(Exception e)
      {
          result = null;
      }    
    
      return result;
    }  
}
