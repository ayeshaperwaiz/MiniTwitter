package business;

import java.io.Serializable;
import java.sql.Timestamp;

public class Tweet implements Serializable{
    private int tweetID;
    private int tweetUserID;
    private int tweetMentionID;

    private String twit;
    private Timestamp time;

    public void setTime(Timestamp time) {
        this.time = time;
    }
      public Timestamp getTime() {
        return time;
    }
    
    public Tweet()
    {
       
        twit = "";
        time = new Timestamp(System.currentTimeMillis());
    }
    
    public Tweet(int userID, String twit, Timestamp time)
    {
        this.tweetUserID = userID;
        this.twit = twit;
        this.time = new Timestamp(System.currentTimeMillis());
    }
    
    public Tweet(int tweetID, int tweetUserID, int tweetMentionID, String twit, String time)
    {
        this.tweetID = tweetID;
        this.tweetUserID = tweetUserID;
        this.tweetMentionID = tweetMentionID;
        this.twit = twit;
        this.time = new Timestamp(System.currentTimeMillis());

    }
    
    
    public void setTweetID(int tweetID){
        this.tweetID = tweetID;
    }
    
    public int getTweetID(){
        return this.tweetID;
    }
    
  
    public void setTweetUserID(int tweetUserID){
        this.tweetUserID = tweetUserID;
    }
    
    public int getTweetUserID(){
        return this.tweetUserID;
    }
    
    
    public void setTweetMentionID(int tweetMentionID){
        this.tweetMentionID = tweetMentionID;
    }
    
    public int getTweetMentionID(){
        return this.tweetMentionID;
    }
    
    
    public void setTwit(String twit){
        this.twit = twit;
    }
    
    public String getTwit(){
        return this.twit;
    }
    
    
    
    

    

}