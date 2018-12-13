package business;

public class Follow {
    int userID;
    int followedUserID;
    String followDate;
    
    public Follow()
    {
        

        followDate = "";
    }
    
    public void setUserID(int userID){
        this.userID = userID;
    }
    public void setFollowedUserID(int followedUserID)
    {
        this.followedUserID = followedUserID;
    }
    public void setFollowDate(String followDate)
    {
        this.followDate = followDate;
    }
    public int getUserID()
    {
        return this.userID;
    }
    public int getFollowedUserID()
    {
        return this.followedUserID;
    }
    public String getFollowDate()
    {
        return this.followDate;
    }
}
