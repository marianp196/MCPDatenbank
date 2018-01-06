/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_Query;

/**
 *
 * @author marian
 */
public class MCPParameter {
    private String name;
    private String wert;

    public MCPParameter(String name, String wert) {
        this.name = name;
        this.wert = wert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWert() {
        return wert;
    }

    public void setWert(String wert) {
        this.wert = wert;
    }
    
    
}
