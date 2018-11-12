<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
        <link rel="stylesheet" href="styles/main.css">
        <%@ include file="header.jsp"%>
        
    </head>
    <body>
        <div class="login-block">
            
            <!--div class="signup"> Create Account </div-->
            <div class="login"> Login </div>
          
            <form name="membership" method="get" action="membership">
                <input type = "hidden" name = "action" value = "login">
              
                <div id ="errorMessage" class ="notVisible"></div>
                <div class= "login-form" id="login-form">
                  <p class = "errorMessage" >${message}</p><br>
                  <input type="text" placeholder="Email or Username" name="emailAddress"
                         id = "emailAddress" class="input"><br>
                  <input type="password" name="password" placeholder="Password" class= "input" id="passInput" >
                  <a href="forgotpassword.jsp" class="link">Forgot password?</a> <br><br>
                  <input type="submit" name="submit" value="Login" class="btn">
                  
                  <p class="account"> New user? </p>
                  <a href ="signup.jsp" class="link2"> Sign up now </a>
                </div>
                
            </form>
            </div>       
        
        <script src="includes/main.js" ></script>
        <script src="includes/event.js" ></script>
        
        <%@ include file="footer.jsp" %>
       
    </body>
</html>