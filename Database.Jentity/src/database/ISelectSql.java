/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marian
 */

/*
Schöner wäre wohl ein Interface, das den SqlString zurück gibt
*/
public interface ISelectSql {
    ResultSet SelectAll(String name)  throws Exception;
}
