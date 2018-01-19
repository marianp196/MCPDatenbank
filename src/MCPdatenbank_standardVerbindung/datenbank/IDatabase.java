/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.datenbank;

import java.sql.Statement;

/**
 *
 * @author marian
 */
public interface IDatabase {

    Statement getQuery();

    boolean isConnectedDb();
    
}
