/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.stringComparer;

/**
 *
 * @author marian
 */
public class CaseSensitive implements IStringComparer{

    @Override
    public boolean IsEqual(String str0, String str1) {
        return str0.equals(str1);
    }
    
}
