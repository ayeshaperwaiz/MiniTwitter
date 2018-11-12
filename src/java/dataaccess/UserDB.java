package dataaccess;
import business.User;
import business.Tweet;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB {
    //private Connection connection; 
    
    public static long insert(User user) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Asap786m!";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
                        Statement statement = connection.createStatement();

            String preparedSQL = "Insert into user"
                                   + "(fullname, username, emailAddress, password, birthdate, questionNo, answer) "
                                   + "Values"
                                   + " ('"+user.getFullName()+"','"+user.getUserName()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getBirthDate()+"','"+user.getQuestionNo()+"','"+user.getAnswer()+"')";
            
            int result = statement.executeUpdate(preparedSQL);
            
            return result; 
            
            
            /*String preparedQuery = "INSERT INTO User (fullname, username, emailAddress, birthdate, password, questionNo, answer) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, user.getFullName()); 
            ps.setString(2, user.getEmail());  
            ps.setString(3, user.getBirthDate());
            ps.setString(4, user.getUserName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getQuestionNo());
            ps.setString(7, user.getAnswer());
            
            return ps.executeUpdate(); */
        }
        
        catch(SQLException e) {
          for (Throwable t : e)
              t.printStackTrace();
          return 0;
        }

    }
 
    public static User search(String emailAddress){
        String results = ""; 
        String preparedSQL = "Select * from twitterdb.user where emailAddress = ?"; 
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Asap786m!";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();
                        
           PreparedStatement ps = connection.prepareStatement(preparedSQL);
           ps.setString(1, emailAddress);
           
           ResultSet rs = ps.executeQuery();
           
           User user = new User();
           
           if (rs.next()){
                user.setFullName(rs.getString("fullname"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("emailAddress"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getString("birthDate"));
                user.setQuestionNo(rs.getString("questionNo"));
                user.setAnswer(rs.getString("answer"));


            }
            
            return user;
        } 

        catch(SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
            return null;
        }

        catch (ClassNotFoundException e) {
                results = "<p>Error loading the databse driver: <br>"
                        + e.getMessage() + "</p>";
                return null;
        } 
        
    } 
    
   public static boolean update(User user) {
        
        PreparedStatement ps = null;
            
        String preparedSQL = 
            "UPDATE User SET " + "fullname = ?, " + "emailAddress = ?, " + "birthdate = ?, " + "password = ?, " + "questionNo = ?," + "answer = ?"
            + "WHERE emailAddress = ?";
            
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Asap786m!";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();
        
            ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getBirthDate());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getQuestionNo());
            ps.setString(6, user.getAnswer());
            ps.setString(7, user.getEmail());
            
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
               String results = "<p>Error loading the databse driver: <br>"
                        + e.getMessage() + "</p>";
                return false;
        } 
       
    }
}
