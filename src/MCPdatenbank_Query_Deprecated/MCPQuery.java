/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_Query_Deprecated;

import MCPdatenbank_standardVerbindung.datenbank.CDatenbankverbinder;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ToDo professionellers exceptionHandling
 * @author marian
 */
public class MCPQuery{
    private String SQL = null ;
    private ArrayList <MCPParameter> ar_parameter = new ArrayList <MCPParameter>();

    public MCPQuery(String SQL) {
        this.setSQL(SQL);
    }

    public String getSQL() {
        return SQL;
    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
        
    }
    
      public void Feuer() throws Exception
    {
        Statement st = getQuery();
        
        st.execute(getSQLmitParameter());
    }
      
    public void param(String param, int wert) throws Exception
    {
        setzeParameter(param, String.valueOf(wert));
    }
    
    public void param(String param, String wert) throws Exception
    {
        setzeParameter(param, "'" + wert + "'");
    }
    
    public void param(String param, long wert) throws Exception
    {
        setzeParameter(param, String.valueOf(wert));
    }
      
    
    public ResultSet FeuerResultSet() throws Exception
    {
        Statement st = getQuery();
        return st.executeQuery(getSQLmitParameter());
    }
    
    private boolean parameterVorhanden(String param)
    {
        if(this.SQL == null  || this.SQL.equals(""))
        {
            return false;
        }
        
        return this.SQL.indexOf(param) != -1;
    }
    
    private void setzeParameter(String param, String wert) throws Exception
    {
        if(!this.parameterVorhanden(param))
        {
            throw new Exception("Paramter " + param + " nicht gefunden");
        }
        
        boolean gefunden = false;
        for(MCPParameter p : ar_parameter)
        {
            if(p.getName().equals(param))
            {
                p.setWert(wert);
                gefunden = true;
            }
        }
        
        if(!gefunden)
        {
            ar_parameter.add(new MCPParameter(param, wert));
        }
    }
    
    private String getSQLmitParameter() throws Exception
    {
        String result = this.SQL;
        
        if(result == null || result.equals(""))
        {
            throw new Exception("Kein SQL vorhanden");
        }
        
        for(MCPParameter p : ar_parameter)
        {
           result = result.replaceAll(p.getName(), p.getWert());
        }
        
        return result;
    }
    
    
    
    private Statement getQuery() throws Exception
    {
        if(CDatenbankverbinder.con == null || !CDatenbankverbinder.con.isConnectedServer())
        {
            throw new Exception("Keine Datenbankverbindung");
        }
        
        return CDatenbankverbinder.con.getQuery();
    }
        
}
