/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseDto;

import annotations.IgnoreField;
import annotations.SQLFieldName;
import databaseDto.typeInfo.TypeInfo;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author marian
 */
public class DatabaseDtoService implements IDatabaseDtoSqlService{

    @Override
    public <TDto>Collection<DtoSqlField> GetSqlFields(TDto dto) 
    {
        Class<TDto> type = (Class<TDto>)dto.getClass();        
        TypeInfo<TDto> typeInfo = new TypeInfo<>(type);
        
        Collection<Field> fields = typeInfo.GetPublicFields();
        
        Collection<DtoSqlField> result = new ArrayList<>();
        
        for(Field f : fields)
        {
            DtoSqlField sqlField = getSqlField(f);
            if(sqlField != null)                
                result.add(sqlField);
        }
        
        return null;
    }

    private DtoSqlField getSqlField(Field field) 
    {
        Collection<Annotation> anotations = toArrayList(field.getAnnotations());        
        if(ignoreField(anotations))
            return null;
        
        String name = getName(field);
        
        return null;
    }
    
    private String getName(Field field) {
        Collection<Annotation> annotations = toArrayList(field.getAnnotations());
        List<SQLFieldName> sqlFieldNames = filterAnnotion(annotations, SQLFieldName.class);
       
        String result = null;
        
        if(sqlFieldNames.isEmpty())
            result = field.getName();
        else
            result = sqlFieldNames.get(0).Name();
        
        return result;
    }
    
    private boolean ignoreField(Collection<Annotation> annoations) {
        //Ist das über get name sicher???
        return filterAnnotion(annoations, IgnoreField.class).size() > 0;
    }
    
    private <Ta extends Annotation> List<Ta> filterAnnotion(Collection annotations, Class<Ta> target)
    {
        return (List<Ta>)annotations.stream().filter(a-> 
                a.getClass().getName().equals(target.getName())).collect(Collectors.toList());
    }
    
    private <T> Collection<T> toArrayList(T[] fields) {
        ArrayList<T> ar_List = new ArrayList<>();
        for(T element : fields)
            ar_List.add(element);
        return ar_List;
    }

   
   
    
}
