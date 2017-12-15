/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class BD {
    
    static Connection con;
        
    public static boolean conectar(){
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bloodbank4", "Francisco", "qwerty"); //on("jdbc:mysql://localhost:3306/mydb", "Fr
            return true;
        } catch (SQLException se){
            System.err.println("ERROR CONNECTION: " + se.getMessage());
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se2){                
                System.err.println("ERROR CLOSE CONN: "+ se.getMessage());
            } 
        }        
        return false;
    }
    
}
