package dataaccess;

import java.io.*;
import java.sql.*;
import business.Tweet;
import business.User;
import business.hashtag;
import business.mentions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TweetDB {

    public static boolean insert(Tweet tweet) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String preparedSQL = "INSERT INTO "
                    + "twitterdb.tweet(userID, twit, time)"
                    + "value (?,?,?)";

            ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, tweet.getTweetUserID());
            ps.setString(2, tweet.getTwit());
            ps.setTimestamp(3, tweet.getTime());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static boolean Mention(mentions mention, String username, int userID) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String preparedSQL = "INSERT INTO "
                    + "twitterdb.mention(username, tweetID, userID, mentionusername)"
                    + "value (?,?,?,?)";

            ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, username);
            ps.setInt(2, mention.getTweetID());
            ps.setInt(3, userID);
            ps.setString(4, mention.getUsername());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;

        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean hashtag(hashtag hashtags, int tweetID) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String preparedSQL = "INSERT INTO "
                    + "twitterdb.hashtag(tweetID,hashtagText)"
                    + "value (?,?)";

            ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, tweetID);
            ps.setString(2, hashtags.getHashtagText());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;

        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<hashtag> SelectH() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String preparedSQL = "SELECT COUNT(hashtagText), hashtagText FROM hashtag GROUP BY hashtagText ORDER BY COUNT(hashtagText) DESC";

        try {

            ps = connection.prepareStatement(preparedSQL);

            ResultSet rs = ps.executeQuery();
            ArrayList<hashtag> hashes = new ArrayList<hashtag>();
            while (rs.next()) {

                hashtag hash = new hashtag();
                hash.setHashtagText(rs.getString("hashtagText"));
                hashes.add(hash);
            }
            return hashes;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }
        return null;

    }

    public static ArrayList<hashtag> SelectOneH(String page) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String preparedSQL = "SELECT * FROM hashtag WHERE hashtagText=?";

        try {

            ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, page);
            ResultSet rs = ps.executeQuery();
            ArrayList<hashtag> hashes = new ArrayList<hashtag>();
            while (rs.next()) {

                hashtag hash = new hashtag();
                hash.setHashtagText(rs.getString("hashtagText"));
                hash.setTweetID(rs.getInt("tweetID"));
                hashes.add(hash);
            }
            return hashes;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }
        return null;

    }

    public static Tweet SelectOneT(int tweetID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String preparedSQL = "SELECT * FROM twitterdb.tweet WHERE tweetID =?";

        try {
            ps= connection.prepareStatement(preparedSQL);
            ps.setInt(1, tweetID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                //Tweet tweet = new Tweet();
                int ID = rs.getInt("tweetID");
                String twit = rs.getString("twit");
                int userID = rs.getInt("userID");
                Timestamp time = rs.getTimestamp("time");
                Tweet tweet = new Tweet(userID, twit, time);
                /*tweet.setTwit(rs.getString("twit"));
                tweet.setTweetUserID(rs.getInt("userID"));
                tweet.setTweetID(rs.getInt("tweetID"));*/
                
                return tweet;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return null;

    }

    public static String Search(String username) throws IOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String preparedSQL = "Select * FROM twitterdb.user WHERE username=?";

        try {
            ps = connection.prepareStatement(preparedSQL);
            ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String newUsername = rs.getString("username");

                return newUsername;

            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }

        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }

        return null;

    }

    public static ArrayList<Tweet> selectTweets() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String preparedSQL = "Select * FROM twitterdb.tweet ORDER BY time DESC;";

        try {
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            ps = connection.prepareStatement(preparedSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTwit(rs.getString("twit"));
                tweet.setTweetUserID(rs.getInt("userID"));
                tweet.setTweetID(rs.getInt("tweetID"));

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
                    newMessage = message.substring(indexOf + 1, indexOfSpace);
                    message = message.insert(indexOf, "<a class='blueX' href=\"tweet?action=pageHash&hashMessage=" + newMessage + "\">");
                    int PreLength = message.length();
                    indexOfSpace += PreLength;
                    indexOfSpace -= lengthT;
                    message = message.insert(indexOfSpace, "</a>");
                    newMessage = message.toString();
                    PreLength = newMessage.length();
                    PreLength -= lengthT;

                    startInd = indexOf + PreLength;
                    lengthT = message.length();
                    tracker++;
                }

                if (newMessage != "") {
                    tweet.setTwit(newMessage);
                }

                tweets.add(tweet);

            }

            return tweets;
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

    public static ArrayList<Tweet> selectTweetsNone() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String preparedSQL = "Select * FROM twitterdb.tweet ORDER BY time DESC;";

        try {
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            ps = connection.prepareStatement(preparedSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTwit(rs.getString("twit"));
                tweet.setTweetUserID(rs.getInt("userID"));
                tweet.setTweetID(rs.getInt("tweetID"));

                tweets.add(tweet);

            }

            return tweets;
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

    public static boolean delete(String tweetID) throws IOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String preparedSQL = "DELETE FROM twitterdb.tweet WHERE tweetID = ?";

        try {
            ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, Integer.parseInt(tweetID));

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
     public static ArrayList<Tweet> selectTweetNotifications(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query = "Select * FROM tweetnotifications WHERE (mentionusername = ? AND tweetdate >= memberLastLogin)";
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();

            while (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTweetID(rs.getInt("tweetID"));
                tweet.setTwit(rs.getString("tweet"));
                tweet.setTweetMentionID(rs.getInt("tweetID"));
                tweet.setTime(rs.getTimestamp("tweetdate"));

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
                if (newMessage == "") {
                } else {
                    tweet.setTwit(newMessage);
                }
                tweets.add(tweet);
            }
            return tweets;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return null;
    }
    

}
