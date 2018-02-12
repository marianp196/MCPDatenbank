package typeInfo;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marian
 */
public class TypeInfo<T> {

    public TypeInfo(Class<T> typeInfo) {
        this.typeInfo = typeInfo;
    }
    
    public Collection<Field> GetPrivateFields()
    {
        EModifier[] privateField = new EModifier[]{EModifier.Private};        
        return GetFilteredFields(privateField);
    }
            
    public Collection<Field> GetPublicFields()
    {
        EModifier[] publicField = new EModifier[]{EModifier.Public};        
        return GetFilteredFields(publicField);
    }
    
    public Collection<Field> GetFilteredFields(EModifier[] modifiers)
    {                   
        Collection<Field> result = new ArrayList<>();
        
        for(Field f : typeInfo.getFields())
        {
            if(!isIn(modifiers, f.getModifiers()))
                continue;
            result.add(f);
        }
        
        return result;
    }
    
     private boolean isIn(EModifier[] array, int value)
     {           
            for(EModifier element : array)
                if(convertEmumInt(element) == value)
                    return true;
            
            return false;
     }
     
     private int convertEmumInt(EModifier m)
     {
         if(m == EModifier.Public)
             return 2;
         else if(m == EModifier.Private)
             return 1;
         else 
             throw new NotImplementedException();                
     }
     
     Class<T> typeInfo;
}
