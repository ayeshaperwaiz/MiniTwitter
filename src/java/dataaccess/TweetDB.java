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
            ps.setTimestamp(3, tweet.getTime());            
            
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
                tweet.setTweetUserID(rs.getString("userID"));
                tweet.setTweetID(rs.getString("tweetID"));
                
                StringBuilder message = new StringBuilder(tweet.getTwit());
                String newMessage = "";
                int startInd = 0;
                int tracker = 0;
                int lengthT = message.length();
                while (message.indexOf("@", startInd) != -1 && tracker <= lengthT) {
                    int indexOf = message.indexOf("@", startInd);
                    int indexOfSpace = message.indexOf(" ", indexOf + 1);
                    if (indexOfSpace == -1) {
                        indexOfSpace = message.length();
                    }
                    message = message.insert(indexOfSpace, "</a>");
                    message = message.insert(indexOf, "<a class='blueX'>");
                    newMessage = message.toString();

                    startInd = indexOf + 21;
                    lengthT = message.length();
                    tracker++;
                }
               if (newMessage != "") {
                    tweet.setTwit(newMessage);
                    message = new StringBuilder(tweet.getTwit());
                }

                startInd = 0;
                tracker = 0;
                lengthT = message.length();

                while (message.indexOf("#", startInd) != -1 && tracker <= lengthT) {
                    int indexOf = message.indexOf("#", startInd);
                    int indexOfSpace = message.indexOf(" ", indexOf + 1);
                    if (indexOfSpace == -1) {
                        indexOfSpace = message.length();
                    }
                    message = message.insert(indexOfSpace, "</a>");
                    message = message.insert(indexOf, "<a class='blueX'>");
                    newMessage = message.toString();

                    startInd = indexOf + 21;
                    lengthT = message.length();
                    tracker++;
                }

                if (newMessage != "") {
                    tweet.setTwit(newMessage);
                }
                
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