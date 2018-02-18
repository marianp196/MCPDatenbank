/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableDto;

import java.lang.reflect.Field;

/**
 *
 * @author marian
 */
public class DtoSqlField {

    public DtoSqlField(Field field, String name) {
        this.field = field;
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }   
        
    private Field field;
    private String name;
}
