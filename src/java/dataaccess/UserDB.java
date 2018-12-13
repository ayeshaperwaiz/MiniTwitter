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
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB {
    //private Connection connection; 

    public static long insert(User user) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Phuongnghi2031376";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            String preparedSQL = "Insert into user"
                    + "(fullname, username, emailAddress, password, birthdate, questionNo, answer,salt) "
                    + "Values"
                    + " ('" + user.getFullName() + "','" + user.getUserName() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.getBirthDate() + "','" + user.getQuestionNo() + "','" + user.getAnswer() + "','" + user.getSalt() + "')";

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
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }

    }

    public static User search(String emailAddress) {
        String results = "";
        String preparedSQL = "Select * from twitterdb.user where emailAddress = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Phuongnghi2031376";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, emailAddress);

            ResultSet rs = ps.executeQuery();

            User user = new User();

            if (rs.next()) {
                user.setUserID(rs.getInt("userID"));
                user.setFullName(rs.getString("fullname"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("emailAddress"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getString("birthDate"));
                user.setQuestionNo(rs.getString("questionNo"));
                user.setAnswer(rs.getString("answer"));
                user.setSalt(rs.getString("salt"));
            }

            return user;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        } catch (ClassNotFoundException e) {
            results = "<p>Error loading the databse driver: <br>"
                    + e.getMessage() + "</p>";
            return null;
        }

    }

    public static boolean update(User user) {

        PreparedStatement ps = null;

        String preparedSQL
                = "UPDATE User SET " + "fullname = ?, " + "emailAddress = ?, " + "birthdate = ?, " + "password = ?, " + "questionNo = ?," + "answer = ?"
                + "WHERE emailAddress = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Phuongnghi2031376";
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
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return false;
        } catch (ClassNotFoundException e) {
            String results = "<p>Error loading the databse driver: <br>"
                    + e.getMessage() + "</p>";
            return false;
        }

    }

    public static int updateLastLogin(String emailAddress) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String preparedSQL = "UPDATE user set lastLogin = current_timestamp WHERE "
                + "emailAddress= ? ";
        int Result = 0;
        try {

            ps = connection.prepareStatement(preparedSQL);
     
            ps.setString(1, emailAddress);
            Result = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        
        finally 
        {
          DBUtil.closePreparedStatement(ps);
           pool.freeConnection(connection);
        
        }
        return Result;
    }

    public static ArrayList<User> selectUsers() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String preparedSQL = "Select * FROM twitterdb.user;";

        try {
            ArrayList<User> users = new ArrayList<User>();
            ps = connection.prepareStatement(preparedSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("username"));
                user.setFullName(rs.getString("fullname"));
                user.setBirthDate(rs.getString("birthdate"));
                user.setPassword(rs.getString("password"));
                user.setQuestionNo(rs.getString("questionNo"));
                user.setAnswer(rs.getString("answer"));

                users.add(user);

            }

            return users;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
}
