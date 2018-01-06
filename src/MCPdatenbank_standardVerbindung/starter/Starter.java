/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.starter;

import MCPdatenbank_standardVerbindung.datenbank.CConInfo;
import MCPdatenbank_standardVerbindung.datenbank.CDatenbankverbinder;
import MCPdatenbank_standardVerbindung.gui.CVerbindungFactory;
import java.io.File;

/**
 *
 * @author marian
 */
public abstract class Starter {
    
    public Starter(){
        verbindungZuServer();
    }
    
    
    private void verbindungZuServer()
    {
        //aufbau();
        CVerbindungFactory fac = new CVerbindungFactory(null,this);
        fac.macheVerbindung();
    }        
   
    public abstract void verbindungErstellt();
    
        /*
        Wird aufgerufen wenn Verbindung erstellt ist und in Standardklasse statisch abgekegt wurde
        */
          
    
}
