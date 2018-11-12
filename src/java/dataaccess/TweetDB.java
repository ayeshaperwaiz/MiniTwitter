package dataaccess;
import java.io.*;
import java.sql.*;
import business.Tweet;
import business.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TweetDB {
    public static boolean insert(Tweet tweet) throws IOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        try {
             String preparedSQL = "INSERT INTO "
                           + "twitterdb.tweet(userID, twit, time)"
                           + "value (?,?,?)";
             
            ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, tweet.getTweetUserID());
            ps.setString(2, tweet.getTwit());
            ps.setString(3, tweet.getTime());            
            
            ps.executeUpdate();
            return true;
        } catch (SQLException  e) {
            for (Throwable t : e)
                t.printStackTrace();
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    
    }
    
    public static ArrayList<Tweet> selectTweets(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String preparedSQL = "Select * FROM twitterdb.tweet ORDER BY time DESC;";
        
        try{
           ArrayList<Tweet> tweets = new ArrayList<Tweet>();
           ps = connection.prepareStatement(preparedSQL);
           ResultSet rs = ps.executeQuery();
           
           while (rs.next()){
                Tweet tweet = new Tweet(); 
                tweet.setTwit(rs.getString("twit"));
                tweet.setTime(rs.getString("time"));
                tweet.setTweetUserID(rs.getString("userID"));
                tweet.setTweetID(rs.getString("tweetID"));
                tweets.add(tweet); 
           }
           
           return tweets; 
        }
        
        catch(SQLException e) {
          for (Throwable t : e)
              t.printStackTrace();
              return null;
        }
        finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
                
        
    }
    
    public static boolean delete (String tweetID) throws IOException{
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String preparedSQL = "DELETE FROM twitterdb.tweet WHERE tweetID = ?";
        
        try{
            ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, Integer.parseInt(tweetID));
            
            ps.executeUpdate(); 
            return true; 
        }
        catch(SQLException e) {
          for (Throwable t : e)
              t.printStackTrace();
              return false;
        }
        finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    

}