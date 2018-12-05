package business;
import java.io.Serializable;
import java.io.InputStream;
import java.sql.Timestamp;

/**
 *
 * @javabean for User Entity
 */
public class User implements Serializable {
    //define attributes fullname, ...
    
    //define set/get methods for all attributes.
    private String userID;
    private String fullname;
    private String emailAddress;
    private String birthdate;
    private String username;
    private String password;
    private String questionNo;
    private String answer;
    private Timestamp lastLogin;
    private InputStream profileURL;

    
    public User(){
        userID = "";
        fullname = "";
        emailAddress = "";
        birthdate = "";
        username = "";
        password = "";
        questionNo = ""; 
        answer = "";
        profileURL = null; 
    }

        public User(String userID, String fullname, String username, String email, String password, String birthdate, String questionNo, String answer){
        this.userID = userID;
        this.fullname = fullname;
        this.username = username;
        this.emailAddress = email;
        this.password = password;
        this.birthdate = birthdate;
        this.questionNo = questionNo;
        this.answer = answer;
    }
        
    public User(String fullname, String emailAddress, String birthdate, String username, String password, String questionNo, String answer) {
        this.fullname = fullname; 
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.username = username;
        this.password = password;
        this.questionNo = questionNo;
        this.answer = answer;
    }
        
    public void setUserID(String userID){
        this.userID = userID;
    }
    public String getUserID(){
        return this.userID;
    }
   
    public String getFullName(){
        return this.fullname;
    }
    public void setFullName(String fullname){
        this.fullname = fullname;
    }
    
    public String getEmail(){
        return this.emailAddress;
    }
    public void setEmail(String emailAddress){
        this.emailAddress = emailAddress;
    }
    
    public String getBirthDate(){
        return this.birthdate;
    }
    public void setBirthDate(String birthdate){
        this.birthdate = birthdate;
    }
    
    public String getUserName(){
        return this.username;
    }
    public void setUserName(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getQuestionNo()
    {
        return this.questionNo;
    }
    public void setQuestionNo(String questionNo){
        this.questionNo = questionNo;
    }
    
    public String getAnswer(){
        return this.answer;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    
    public InputStream getProfileURL(){
        return this.profileURL;
    }
    
     public void setProfileURL(InputStream profileURL){
        this.profileURL = profileURL;
    }
     
     
     
   
    public void setLastlogin()
    {
        this.lastLogin = new Timestamp(System.currentTimeMillis());
    }
    
    
}
