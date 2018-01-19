/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenzKonfiguartion;

import datenbank.CConnectionInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import javax.xml.bind.JAXB;
import persistenzKonfiguartion.chiffrierung.IPasswortChiffre;
import persistenzKonfiguartion.chiffrierung.IPasswortDechiffre;

/**
 *
 * @author marian
 */
public class XmlWriter {
 
    public XmlWriter(File file, IPasswortChiffre passwortChiffre) throws Exception {
        if(file == null)
            throw new NullPointerException("file");
        this.file = file;
        this.passwortChiffre = passwortChiffre;
        checkFile();
    }
    
    public void WriteInfo(CConnectionInfo info) throws Exception
    {
        ConnectionInfoDto connectionInfoDto = getConnectionInfoDTo(info);
        JAXB.marshal(connectionInfoDto, file);
    }
    
    
    private ConnectionInfoDto getConnectionInfoDTo(CConnectionInfo info) {
        ConnectionInfoDto result = new ConnectionInfoDto();
        result.Benutzer = info.GetBenutzer();
        result.Datenbanktreiber = info.GetDatenbanktreiber();
        result.Passwort = getPasswortChiffre(info.GetPasswort());
        result.Url = info.GetUrl();
        return result;
    }
    
    private String getPasswortChiffre(String text)
    {
        if(passwortChiffre == null)
            return text;
        return passwortChiffre.Chiffre(text);
    }
    
    private void checkFile() throws Exception {
        if(file.isDirectory())
            throw new FileSystemException("Verzeichnis darf kein Ordner sein: " + file.getAbsolutePath());
        if(!file.exists())
        {
            if(!file.createNewFile())
                throw new FileSystemException("Kann File nicht erzeugen");
        }
            
    }
    
    private File file;
    private IPasswortChiffre passwortChiffre;

    
    

  
}
