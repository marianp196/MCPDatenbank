/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.ISelectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.HTML;
import service.cases.Select;
import tableDto.DtoSqlField;
import tableDto.IDatabaseDtoSqlService;

/**
 *
 * @author marian
 */
public class Jentity implements IJentity{
 
    
    public Jentity(ISelectSql selectSql, IDatabaseDtoSqlService databaseDtoSqlService) {
        this.selectSql = selectSql;
        this.databaseDtoSqlService = databaseDtoSqlService;
        
        selectProcessor = new Select(selectSql, databaseDtoSqlService);
    }
    
   
    @Override
    public <TDto> Collection<TDto> Select(String table, Class<TDto> type) throws Exception {
        return selectProcessor.Select(table, type);
    }

    
    private Select selectProcessor;
    
    private final ISelectSql selectSql;
    private final IDatabaseDtoSqlService databaseDtoSqlService;   
}
