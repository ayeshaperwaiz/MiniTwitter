<%-- 
    Document   : hashtag
    Created on : Dec 12, 2018, 4:36:45 PM
    Author     : Nghi Phan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@ include file="header.jsp" %>
        <link rel="stylesheet" href="styles/home.css">
    </head>
    <body>
        <c:if test="${user == null}">
            <c:redirect url = "/login.jsp"/>
        </c:if>
        
        <div class ="wrapper">
            
            <!-- Column 1 -->
            <div class ="col1">
                
                <!--  Block -->
                <div class ="userInfo">
                    <div class="info">
                        <h1><c:out value="${user.fullName}" /></h1>
                        <h2> @<c:out value="${user.userName}" /> </h2>
                    </div>
                </div>
                <br/>
           </div>
                   <!-- Feed -->
                <div class="Feed">
                    <c:forEach items="${hashTweets}" var = "tweet">
                        <div class="tweetBlock">
                            <div class="userPic"></div>
                            <div class="feedUser"><h5>@${tweet.tweetUserID}</h5></div>
                            <div class="feedTweet"><p>${tweet.twit}<p></div>
                            <div class="delLink">
                                <a href="tweet?action=deleteTweet&tweetID=${tweet.tweetID}"> 
                                    <i class="glyphicon glyphicon-remove"></i> 
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                        
                  
                </div>
                    
        
       
    </body>

</html>
            
