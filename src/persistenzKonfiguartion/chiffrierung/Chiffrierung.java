/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenzKonfiguartion.chiffrierung;

/**
 * 
 * @author marian
 */
public class Chiffrierung implements IPasswortChiffre, IPasswortDechiffre{

    /*
    Erste implementierung...Braucht eindeutig verbesserung
    */
    
    @Override
    public String Chiffre(String paswt) {
        String result="";
        char c;
        
        for(int i = 0; i<paswt.length(); i++ )
        {
          c = paswt.charAt(i);
          c = (char)(c + 2);
          result += String.valueOf(c);
          
        }  
        
        return result;
    }

    @Override
    public String Dechiffre(String paswt) {
        String result="";
        char c;
        
        for(int i = 0; i<paswt.length(); i++ )
        {
          c = paswt.charAt(i);
          c = (char)(c - 2);
          result += String.valueOf(c);
          
        }  
        
        return result;
    }
 
    
     
}
