/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 
 */
public class mentions {
    
    private String username;
    private int mentionID;
    private int tweetID;

    public mentions() {
    }

    public mentions(String username, int tweetID) {
        this.username = username;
        this.tweetID = tweetID;
    }    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMentionID() {
        return mentionID;
    }

    public void setMentionID(int mentionID) {
        this.mentionID = mentionID;
    }

    public int getTweetID() {
        return tweetID;
    }

    public void setTweetID(int tweetID) {
        this.tweetID = tweetID;
    }
    
    
    
}
