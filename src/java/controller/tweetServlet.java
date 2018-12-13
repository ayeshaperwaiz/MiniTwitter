package controller;

import business.Follow;
import business.User;
import business.Tweet;
import business.hashtag;
import business.mentions;
import dataaccess.FollowDB;
import dataaccess.TweetDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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

        if (action == null) {
            action = "viewTweets";
        }

        if (action.equals("postTweet")) {
            postTweet(request, response);

        }
        if (action.equals("pageHash") )
        {
          String page = request.getParameter("hashMessage");
          ArrayList<hashtag> hashes = TweetDB.SelectOneH(page);
          ArrayList<Tweet> hashTweets = new ArrayList<Tweet>();
          
          for(hashtag x : hashes){
              Tweet tweet = new Tweet();
              tweet = TweetDB.SelectOneT(x.getTweetID());
              //String tst = tweet.getTwit();
              hashTweets.add(tweet);
              
          
          }
          session.setAttribute("hashTweets", hashTweets);
          url= "/hashtag.jsp";
          
        }
        if (action.equals("follow_user")) {
            String emailAddress= request.getParameter("emailAddress");

            User thisUser = (User) session.getAttribute("user");
            User followedUser = UserDB.search(emailAddress);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = (String) dtf.format(now);

            Follow follow = new Follow();
            follow.setUserID(thisUser.getUserID());
            follow.setFollowedUserID(followedUser.getUserID());
            follow.setFollowDate(date);
            FollowDB.insert(follow);
        }
        if (action.equals("unfollow_user")) {
            String emailAddress= request.getParameter("emailAddress");

            User thisUser = (User) session.getAttribute("user");
            User followedUser = UserDB.search(emailAddress);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = (String) dtf.format(now);

            Follow follow = new Follow();
            follow.setUserID(thisUser.getUserID());
            follow.setFollowedUserID(followedUser.getUserID());
            follow.setFollowDate(date);
            FollowDB.insert(follow);
        }

        if (action.equals("deleteTweet")) {
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
        String time = (String) dtf.format(now);
        Timestamp daytime = new Timestamp(System.currentTimeMillis());

        Tweet twit = new Tweet(user.getUserID(), tweet, daytime);
        TweetDB.insert(twit);

        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets = TweetDB.selectTweetsNone();
        for (int i = 0; i < tweets.size(); i++) {
            String Twit = tweets.get(i).getTwit();
            int userID = tweets.get(i).getTweetUserID();
            int SetUser = user.getUserID();
            
            if (tweet.equals(Twit) && SetUser == userID) {
                int TweetID = tweets.get(i).getTweetID();

                String message = tweet;
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
                    newMessage = message.substring(indexOf + 1, indexOfSpace);
                    mentions mention = new mentions(newMessage, TweetID);

                    TweetDB.Mention(mention, user.getUserName(), user.getUserID());

                    startInd = indexOfSpace + 1;
                    lengthT = message.length();
                    tracker++;
                }
                 message = tweet;
                 newMessage = "";
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
                    hashtag hashtags = new hashtag(newMessage, TweetID);

                    TweetDB.hashtag(hashtags, TweetID);

                    startInd = indexOfSpace + 1;
                    lengthT = message.length();
                    tracker++;
                }

                
            }

        }

        tweets = TweetDB.selectTweets();
        session.setAttribute("tweets", tweets);
    }

}
