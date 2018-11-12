package controller;

import business.User;
import business.Tweet;
import dataaccess.TweetDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tweetServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String url = "/home.jsp";
        
        if(action == null){
            action = "viewTweets";
        }
        
        if(action.equals("postTweet")){
            postTweet(request, response);

        }
        
        if(action.equals("deleteTweet")){
            String tweetID = request.getParameter("tweetID");
            TweetDB.delete(tweetID);  
            
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            tweets = TweetDB.selectTweets(); 
            session.setAttribute("tweets", tweets); 
        }
        
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    protected void postTweet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String tweet = request.getParameter("tweet");
        User user = (User) session.getAttribute("user");
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
        String time= (String) dtf.format(now);
        
        Tweet twit = new Tweet(user.getUserName(), tweet, time);
        TweetDB.insert(twit);
        
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets = TweetDB.selectTweets(); 
        session.setAttribute("tweets", tweets);
    }
    
    


}