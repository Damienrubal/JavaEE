package Model;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dams
 */
public class DataAccess {

    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/bdd_java";
    private String loginBdd = "root";
    private String passwordBdd = "root";
    private String sqlMessage;
    private ResultSet resultat = null;
      
    /* La liste qui contiendra tous les résultats de nos essais */
    private List<String> messages;

    public static void main(String[] args) throws SQLException {
        DataAccess access = new DataAccess();
       // System.out.println("checkPasswpord()=" + access.checkPassword("yass", "chipotle"));
       //System.out.print("add()="+ access.add("Danielle","100"));
       //System.out.print("remove() :" +access.remove("Jess","54"));
      /* List<String>mess=access.display();
       for ( String stg : mess){
           System.out.print(stg);
           }
       */
       System.out.print("update():"+access.update("16", "alan","4"));
    }

    public DataAccess() {

        /* Chargement du driver JDBC pour MySQL */
     try {
            Class.forName( "com.mysql.jdbc.Driver" );
            }
        catch ( ClassNotFoundException e ) {
         }
        try {
            //System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, loginBdd, passwordBdd);

            //System.out.println("connection succesful");
           // boolean check = checkPassword("yass", "chipotle");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            //Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checkPassword(String login, String password) {
        try {
            Statement s = connection.createStatement();
            sqlMessage = "select * from USERS";
            sqlMessage += " where LOGIN='" + login + "'";
            sqlMessage += " and PASSWORD='" + password + "'";
            ResultSet rs = s.executeQuery(sqlMessage);
            // Si rs.next() trouve un element retournera true -> ok 
            // si rs.next() , ne trouve rien , retournera false -> erreur login et/ou password
            boolean r = rs.next();
            rs.close();
//            if (r) {
//                System.out.print("Les données utilisateur + mot de passe sont correct");
//            } else {
//                System.out.print("les données n'existent pas ");
//            }
            return r;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean add(String login,String name, String value) {
       Boolean c =false;
        try {
            Statement s = connection.createStatement();
            sqlMessage="INSERT INTO userdata(login,name,value) VALUES ('"+login+"','"+name+"','"+value+"');";
            int statut = s.executeUpdate(sqlMessage);
            
          if (statut==1) {
                c= true ;
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public boolean update(String id, String name, String value) {
        Boolean c =false;
        try {
            Statement s = connection.createStatement();
            sqlMessage=" UPDATE userdata SET name='"+name+"',value='"+value+"' WHERE id_userData='"+id+"';";
            int statut = s.executeUpdate(sqlMessage);
            
          if (statut==1) {
                c= true ;
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public boolean delete(String name, String value) {
       
        boolean c=false;
        try {
            Statement s = connection.createStatement();
            sqlMessage="DELETE FROM userdata where name='"+name+"' and value='"+value+"';";
            int statut = s.executeUpdate(sqlMessage);
           
          // statut show number of lignes which were concerned; 
          if (statut>0) {
                c= true ;
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    
    public List<String> display(){
        messages = new ArrayList<String>();    
        
        for ( String personn : messages){
            System.out.print(personn);
        }
        try {
            Statement s = connection.createStatement();
            sqlMessage="SELECT* from userdata; ";
            resultat = s.executeQuery(sqlMessage);
            
               /* Récupération des données du résultat de la requête de lecture */
        while ( resultat.next() ) {
            int idUser = resultat.getInt(1);
            String login = resultat.getString(2);
            String name = resultat.getString(3);
            String value = resultat.getString(4);
            /* Formatage des données pour affichage dans la JSP finale. */
            messages.add( " id_userData = " + idUser+ ", login= " + login
                    + ", name = "
                    + name + ", value = " + value + "\n" );
        
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          
        for ( String personn : messages){
            System.out.print(personn);
        }
         
        return messages;
        
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
