package dataaccess;

import business.Follow;
import business.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FollowDB {
    public static int insert(Follow follow)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //Find if this data exist in the database.
        String query = "SELECT * FROM twitterdb.follow WHERE userID = ? AND followedUserID = ?;";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, follow.getUserID());
            ps.setInt(2, follow.getFollowedUserID());
            rs = ps.executeQuery();
            
            //If data already exist, don't add it again.
            if(rs.next()){
                return 1;
            }else{
                query = "INSERT INTO twitterdb.follow (userID, followedUserID, followDate) "
                    + "VALUES (?, ?, ?)";
                try{
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, follow.getUserID());
                    ps.setInt(2, follow.getFollowedUserID());
                    ps.setString(3, follow.getFollowDate());
                    return ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                    return 0;
                }
            }
        } catch(SQLException e) {
            System.out.println(e);
            return 0;
        }finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int delete(Follow follow)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //Find if this data exist in the database.
        String query = "SELECT * FROM twitterdb.follow WHERE userID = ? AND followedUserID = ?;";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, follow.getUserID());
            ps.setInt(2, follow.getFollowedUserID());
            rs = ps.executeQuery();
            
            //If this data exist, delete this data.
            if(rs.next()){
                query = "DELETE FROM twitterdb.follow WHERE userID = ? AND followedUserID = ?;";
                        
                try{
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, follow.getUserID());
                    ps.setInt(2, follow.getFollowedUserID());
                    return ps.executeUpdate();
                }catch (SQLException e) {
                    System.out.println(e);
                    return 0;
                }
            }else{
                return 1;
            }
        }catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<User> followedUsers(String userID) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM twitterdb.follow WHERE userID = ?";
        
        List<User> followedList = new LinkedList();
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            User user = null;
            while(rs.next()){
                user = new User();
                user.setUserID(rs.getInt("followedUserID"));
                followedList.add(user);
            }
            return followedList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int countFollowers(String userID) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        
        String query
                = "SELECT COUNT(*) AS count FROM twitterdb.follow WHERE followedUserID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            
            if(rs.next()){
                count = Integer.parseInt(rs.getString("count"));
            }
            return count;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int countFollowing(String userID) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        
        String query
                = "SELECT COUNT(*) AS count FROM twitterdb.follow WHERE userID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            
            if(rs.next()){
                count = Integer.parseInt(rs.getString("count"));
            }
            return count;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
