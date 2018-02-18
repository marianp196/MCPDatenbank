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
    }
    
    
    @Override
    public <TDto> Collection<TDto> Select(String table, Class<TDto> type) throws Exception {           
        ResultSet resultSet = selectSql.SelectAll(table);
        return getInstancesFromResultSet(resultSet, type);        
    }
    
    private <TDto> Collection<TDto> getInstancesFromResultSet(ResultSet resultSet, Class<TDto> type) throws Exception {
        Collection<DtoSqlField> fields = databaseDtoSqlService.GetSqlFields(type);
        Collection<TDto> result = new ArrayList<>();
        
        while(resultSet.next())
        {
            TDto dto = type.newInstance();
            for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
            {
                String sqlFieldName = resultSet.getMetaData().getColumnName(i);
                DtoSqlField field = getSqlFieldFromObjectFieldList(fields, sqlFieldName);
                if(field == null)
                    continue;
                initField(resultSet, sqlFieldName, field, dto); 
                
            }
            result.add(dto);
        }
        return result;
    }

    private DtoSqlField getSqlFieldFromObjectFieldList(Collection<DtoSqlField> fields, String sqlFieldName) {
        List<DtoSqlField> filteredFields = fields.stream().filter(
                f -> f.getName().equalsIgnoreCase(sqlFieldName)).collect(Collectors.toList());
        if (filteredFields.size() == 0) 
            return null;
        
        return filteredFields.get(0);
    }
    
    private <TDto>void initField(ResultSet resultSet, String sqlFieldName, DtoSqlField field, TDto instance) 
            throws SQLException, IllegalArgumentException, IllegalAccessException {
        Object value = resultSet.getObject(sqlFieldName);
        field.getField().set(instance, value);
    }
    
    private ISelectSql selectSql;  
    private IDatabaseDtoSqlService databaseDtoSqlService;
    
   
}
