/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.ISelectSql;
import database.SelectSql;
import datenbank.IDatabase;
import javax.crypto.SealedObject;
import tableDto.DatabaseDtoService;
import tableDto.IDatabaseDtoSqlService;

/**
 *
 * @author marian
 */
public class JentityFactory 
{
    
    public static IJentity CreateInstance(IDatabase database) throws Exception
    {
        ISelectSql sqlSelect = new SelectSql(database);
        IDatabaseDtoSqlService databaseDtoSqlService = new DatabaseDtoService();
        
        return new Jentity(sqlSelect, databaseDtoSqlService);
    }
}
