/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;

import tables.Table;

/**
 *
 * @author marian
 */
public interface ISchemaChecker 
{
    void AddTable(Table table) throws Exception;
    
    void CheckAndCreate() throws Exception;
}
