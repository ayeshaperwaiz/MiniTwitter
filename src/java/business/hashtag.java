/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

public class hashtag {
    
    private String hashtagText;
    private int tweetID;
    private int hashtagID;
    
    public hashtag()
    {
        
    }
    
    public hashtag(String hashtagText, int tweetID)
    {
        this.tweetID = tweetID;
        this.hashtagText = hashtagText;
    }
    
    public String getHashtagText()
    {
        return hashtagText;
    }
    
    public void setHashtagText(String a)
    {
        hashtagText = a;
    }
    
    public int getTweetID()
    {
        return tweetID;
    }
    
    public void setTweetID(int a)
    {
        tweetID = a;
    }
    
}
