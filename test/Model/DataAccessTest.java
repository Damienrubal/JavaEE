/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dam's
 */
public class DataAccessTest {
    private Connection connection=null;
    private String url="jdbc:mysql://localhost:3306/bdd_java";
    private String loginBdd="root";
    private String passwordBdd="root";
    
    public DataAccessTest() {
                try {
            connection=DriverManager.getConnection(url,loginBdd,passwordBdd);
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    
}
