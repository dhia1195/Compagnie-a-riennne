
package utils;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author turki
 */
public class MyDB {
    
   private String url="jdbc:mysql://127.0.0.1:3306/transfert";
   private String login="root";
   private String pwd="";
   private Connection cnx;
   private static MyDB instance;

    private MyDB() {
       try {
           cnx=DriverManager.getConnection(url, login, pwd);
           System.out.println("Connexion etablie");
       } catch (SQLException ex) {
           Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   
    
    public static MyDB getInstance(){
        if(instance==null)
           instance=new MyDB();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
   
    
    
}