/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableDto;

import annotations.IgnoreField;
import annotations.SQLFieldName;
import tableDto.typeInfo.TypeInfo;
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
    public <TDto> Collection<DtoSqlField> GetSqlFields(Class<TDto> type) throws Exception {
        return getFields(type);
    }
    
    @Override
    public <TDto>Collection<DtoSqlField> GetSqlFields(TDto dto) throws Exception 
    {
        Class<TDto> type = (Class<TDto>)dto.getClass();        
        return getFields(type);
    }

    private <TDto> Collection<DtoSqlField> getFields(Class<TDto> type) throws Exception {
        TypeInfo<TDto> typeInfo = new TypeInfo<>(type);
        
        Collection<Field> fields = typeInfo.GetPublicFields();
        
        Collection<DtoSqlField> result = new ArrayList<>();
        
        for(Field f : fields)
        {
            DtoSqlField sqlField = getSqlField(f);
            if(sqlField != null)                
                result.add(sqlField);
        }
        
        return result;
    }

    private DtoSqlField getSqlField(Field field) throws Exception 
    {          
        if(ignoreField(field))
            return null;
        
        String name = getName(field);
        
        if(name == null || name.isEmpty())
            throw new Exception("Name konnte nicht gelesen werden");
               
        
        return new DtoSqlField(field, name);
    }
    
    private String getName(Field field) {
        
         SQLFieldName annotationName = field.getAnnotation(SQLFieldName.class);
       
        String result = null;
        
        if(annotationName == null)
            result = field.getName();
        else
            result = annotationName.Name();
        
        return result;
    }
    
    private boolean ignoreField(Field field) {
        
        return field.getAnnotation(IgnoreField.class) != null;
    }
  
   
}

 /*private <Ta extends Annotation> List<Ta> filterAnnotion(Collection annotations, Class<Ta> target)
    {
        return (List<Ta>)annotations.stream().filter(a-> 
                a.getClass().getName().equals(target.getName())).collect(Collectors.toList());
    }
    
    private <T> Collection<T> toArrayList(T[] fields) {
        ArrayList<T> ar_List = new ArrayList<>();
        for(T element : fields)
            ar_List.add(element);
        return ar_List;
    } */ 
