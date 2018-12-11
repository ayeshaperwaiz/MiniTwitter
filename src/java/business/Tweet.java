package business;

import java.io.Serializable;
import java.sql.Timestamp;

public class Tweet implements Serializable{
    String tweetID;
    String tweetUserID;
    String tweetMentionID;
    String twit;
    Timestamp time;
    
    public Tweet()
    {
        tweetID = "";
        tweetUserID = "";
        tweetMentionID = "";
        twit = "";
        time = new Timestamp(System.currentTimeMillis());
    }
    
    public Tweet(String userID, String twit, String time)
    {
        this.tweetUserID = userID;
        this.twit = twit;
        this.time = new Timestamp(System.currentTimeMillis());
    }
    
    public Tweet(String tweetID, String tweetUserID, String tweetMentionID, String twit, String time)
    {
        this.tweetID = tweetID;
        this.tweetUserID = tweetUserID;
        this.tweetMentionID = tweetMentionID;
        this.twit = twit;
        this.time = new Timestamp(System.currentTimeMillis());

    }
    
    public void setTweetID(String tweetID){
        this.tweetID = tweetID;
    }
    
    public String getTweetID(){
        return this.tweetID;
    }
    
    
    public void setTweetUserID(String tweetUserID){
        this.tweetUserID = tweetUserID;
    }
    
    public String getTweetUserID(){
        return this.tweetUserID;
    }
    
    
    public void setTweetMentionID(String tweetMentionID){
        this.tweetMentionID = tweetMentionID;
    }
    
    public String getTweetMentionID(){
        return this.tweetMentionID;
    }
    
    
    public void setTwit(String twit){
        this.twit = twit;
    }
    
    public String getTwit(){
        return this.twit;
    }
    
    
    
    
    public Timestamp getTime(){
        return this.time;
    }
    

}