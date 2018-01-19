/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenzKonfiguartion;

import datenbank.ConnectionInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import javax.xml.bind.JAXB;
import persistenzKonfiguartion.chiffrierung.IPasswortDechiffre;

/**
 *
 * @author marian
 */
public class XmlReader {

    public XmlReader(File file, IPasswortDechiffre passwortDechiffre) throws Exception {
        if(file == null)
            throw new NullPointerException("file");
        this.file = file;
        this.passwortDechiffre = passwortDechiffre;
        checkFile();
    }
    
    public ConnectionInfo GetInfo() throws Exception
    {
        ConnectionInfoDto conInfo = JAXB.<ConnectionInfoDto>unmarshal(file, ConnectionInfoDto.class);
        if(conInfo == null)
            throw new Exception("Konnte nicht parsen.");
        return new ConnectionInfo(conInfo.Url, conInfo.Benutzer, 
                getPasswortKlartext(conInfo.Passwort), conInfo.Datenbanktreiber);
    }
    
    private String getPasswortKlartext(String text)
    {
        if(passwortDechiffre == null)
            return text;
        return passwortDechiffre.Dechiffre(text);
    }
    
    private void checkFile() throws Exception {
        if(!file.exists())
            throw new FileNotFoundException(file.getAbsolutePath());
        if(!file.canRead())
            throw new FileSystemException(file.getAbsolutePath());
    }
    
    private File file;
    private IPasswortDechiffre passwortDechiffre;
    
}
