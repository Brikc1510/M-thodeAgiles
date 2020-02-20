/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;

/**
 *
 * @author Ahmed
 */
public class GestionOperation {
    private Connection conn;
    
    public GestionOperation()
    {
        conn=new DB_Connection().get_connection();
    }
    
    public void setOperation()
    {
        
    }
}
