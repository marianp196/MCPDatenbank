/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datenbank;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author marian
 */
public interface IConnectionFactory 
{
    public Connection GetConnection() throws Exception;
    
    @Deprecated
    public Statement GetStatement() throws Exception;
}
