/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDtos;

import annotations.SQLFieldName;

/**
 *
 * @author marian
 */
public class JentitySelectDto {
    public int Id;
    
    @SQLFieldName(Name = "str")
    public String text;
    
    public double zahl;
    
    public long zahlGross;
}
