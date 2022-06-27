/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Register_SignInForms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hp
 */
public class ConnexionDB {
    private Connection conDB;
   private Statement stmt;
   
    public ConnexionDB()
    {
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        conDB=DriverManager.getConnection("jdbc:oracle:thin:TestUser/testuser@localhost") ;
        stmt = conDB.createStatement();
    }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        catch (SQLException e) { e.printStackTrace(); } 
    }
    
    public Connection getConDB()
    {
        return conDB;
    }
    public Statement getStmt ()
    {
        return stmt;
    }
    

}
