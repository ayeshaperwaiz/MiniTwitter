<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css">
         <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
         <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
        <%@ include file="header.jsp" %>
        <title>Forgot Password</title>
    </head>
    <body>
        <div class ="login-block">
            <div class="signup"> Forgot Password </div>
                <div class="forgot-form">
                    <p class = "errorMessage" >${forgotMessage}</p><br>
                <form name="membership" method="get" action="membership">
                    <input type="hidden" name="action" value="forgot">
                    
                     <span id="email_error" class="notVisible">*</span>
                     <input name="emailAddress" type="email" placeholder="Email Address" class="input" id="email" autocomplete="off" 
                      onkeyup="validateEmail()">
                     <div id ="errorMessage5" class ="notVisible"> Input has invalid characters! </div><br>
                     
                    <select name="questionNo" class="input" id="SecurityQuestion" value="${user.questionNo}">
                    <option value="SQ" disbled slected>Security Question</option>
                    <option value="1">What is the name of your first pet?</option>
                    <option value="2">What is the make of your first car?</option>
                    <option value="3">What is the name of your first school?</option>
                    </select>
                    <span id="answer_error" class="notVisible">*</span>
                    <br><input name ="answer" type="text" placeholder="Answer" class="inputAnswer" id="Answer" onkeyup="validateSecurity()" 
                     autocomplete="off" >
                    <div id ="errorMessage3" class ="notVisible"> Input has invalid characters! </div><br>
                    <input type="submit" name="submit" value="Submit" class="btn">
                </form>
            </div>
        </div>
                    
        <script src="includes/event.js" ></script>
               
        <%@ include file="footer.jsp" %>
    </body>
</html>
