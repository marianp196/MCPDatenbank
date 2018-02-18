/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDtos;

import annotations.IgnoreField;
import annotations.SQLFieldName;

/**
 *
 * @author marian
 */

public class DtoTest {
    //Fields
    public String Field1;    
    public int Field2;
    
    //Fields with other Name
    public @SQLFieldName( Name = "annoName" )  Object wasGeht;
    
    //Ignore Fields
    private String PrivateField;
    
    public @IgnoreField Object ShouldBeIgnored;
    
    @IgnoreField
    private Integer ShouldBeIgnoredAndPrivate;
}
