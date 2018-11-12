<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
        <link rel="stylesheet" href="styles/main.css">
        <%@ include file="header.jsp" %>
    </head>
    <body>
    
    <div class="login-block">
   
     <c:choose>
            <c:when test="${user != null}">
               <div class="signup"> Update Info </div>
            </c:when>  
            <c:otherwise>
                <div class="signup"> Create Account </div>
            </c:otherwise>
        </c:choose> 
    <!--div class="login"> Login </div-->
    <form name="membership" method="post" action="membership" onsubmit="return validateForm()" >
        <div id ="errorMessage" class ="notVisible"></div>
        <div class="signup-form" id="signup-form">
            
        <c:choose>
            <c:when test="${user != null}">
                <input type = "hidden" name = "action" value = "updateUser">
            </c:when>  
            <c:otherwise>
                <input type = "hidden" name = "action" value = "signup">
            </c:otherwise>
        </c:choose> 
        
          <p class = "errorMessage">${message}</p><br>
          
          <c:choose>
              <c:when test="${user != null}">
                  Email: 
                  <input id="email" name="emailAddress" type="text" class="input" value="<c:out value='${user.email}'/>" readOnly/>
              </c:when>
              <c:otherwise>
                  <span id="email_error" class="notVisible">*</span>
                  <input name="emailAddress" type="email" placeholder="Email" class="input" id="email" autocomplete="off" onkeyup="validateEmail()">
                  <div id ="errorMessage5" class ="notVisible"> Input has invalid characters! </div><br>
              </c:otherwise>       
          </c:choose>
          
          
          <c:choose>
              <c:when test="${user != null}">
                  Full Name: 
                  <input id="fullName" name="fullname" type="text" class="input" value="<c:out value='${user.fullName}'/>">
              </c:when>
              <c:otherwise>
                  <span id="fullName_error" class="notVisible">*</span>
                  <input name="fullname" type="text" placeholder="Full Name" class="input" id="fullName" autocomplete="off" onkeyup="validateFullname()">
                  <div id ="invalidName" class="notVisible"> Error: Full name is not valid! </div>
                  <div id ="errorMessage6" class ="notVisible"> Input has invalid characters! </div><br>

              </c:otherwise>        
          </c:choose>


          <c:choose>
              <c:when test="${user != null}">
                  Date of Birth:
                  <input name ="birthdate" type="text" value="<c:out value='${user.birthDate}'/>" class="inputDate" onfocus="(this.type='date')" onblur="(this.type='text')" ><br>
              </c:when>
              <c:otherwise>
                  <span id="DateOfBirth_error" class="notVisible">*</span>
                  <input name ="birthdate" type="text" placeholder="Date of Birth" class="inputDate" onfocus="(this.type='date')" onblur="(this.type='text')" ><br>
              </c:otherwise>  
          </c:choose>
  
                  
          <c:choose>
              <c:when test="${user != null}">
                  Username:
                  <input id="Username" name ="username" type="text" class="input" value="<c:out value='${user.userName}'/>" readOnly/>
              </c:when>
              <c:otherwise>
                  <span id="username_error" class="notVisible">*</span>
                  <input name ="username" type="text" placeholder="Username" class="input" id="Username" onkeyup="validateUsername()" autocomplete="off" >
                  <div id ="errorMessage1" class ="notVisible"> Input has invalid characters! </div><br><br><br>
              </c:otherwise>
                      
          </c:choose>     
                  
          <c:choose>
              <c:when test="${user != null}">
                  Password: 
                  <input id="pass" name="password" type="password" class="input" value="<c:out value='${user.password}'/>" onkeyup="validatePassword()" >
              </c:when>
              <c:otherwise>
                  <span id="pass_error" class="notVisible">*</span>
                  <input name="password" type="password" placeholder="Password" class="input" id="pass" onkeyup="validatePassword()" >
                  <div id ="invalidPass" class ="notVisible"> Error: Password must contain an uppercase letter, a lowercase letter, and a number! </div>
                  <div id ="errorMessage4" class ="notVisible"> Input has invalid characters! </div><br>
              </c:otherwise>
                      
          </c:choose>
                  
          <c:choose>
              <c:when test="${user != null}">
                  <input id="confirmPas" name = "confirm" type="password" class="input" onkeyup= "validateConfirmPassword()" placeholder="Confirm Password" >
              </c:when>
              <c:otherwise>
                  <span id="confirm_error" class="notVisible">*</span>
                  <input name = "confirm" type="password" placeholder="Confirm Password" class="input" id="confirmPas" onkeyup= "validateConfirmPassword()" autocomplete="off" >
                  <div id ="invalidConfirm" class ="notVisible"> Error: Password and confirm password do not match! </div>
                  <div id ="errorMessage2" class ="notVisible"> Input has invalid characters! </div><br><br><br>
              </c:otherwise>
                      
          </c:choose>
                  
                <select name="questionNo" class="input" id="SecurityQuestion" value="${user.questionNo}">
                    <option value="SQ" disbled slected>Security Question</option>
                    <option value="1">What is the name of your first pet?</option>
                    <option value="2">What is the make of your first car?</option>
                    <option value="3">What is the name of your first school?</option>
                </select>
                <span id="answer_error" class="notVisible">*</span>
                <br><input name ="answer" value="<c:out value='${user.answer}'/>" type="text" placeholder="Answer" class="inputAnswer" id="Answer" onkeyup="validateSecurity()" autocomplete="off" >
                <div id ="errorMessage3" class ="notVisible"> Input has invalid characters! </div><br>
    
          <c:choose>
              <c:when test="${user != null}">
                  <input type="submit" name="submit" value="Update" class="btn">
              </c:when>
              <c:otherwise>
                  <input type="submit" name="submit" value="Sign Up" class="btn">
              </c:otherwise>
                      
          </c:choose>
        </div>
    </form>
    
    <script src="includes/main.js" ></script>
    <script src="includes/event.js" ></script>
    </div>
        
    
    <%@ include file="footer.jsp" %>
    
    </body>
</html>
