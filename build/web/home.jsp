<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        
        <!-- Header -->

        <!-- User Info -->
        <div class = "wrapper">
          <div class = "UserInfo">
              <div id="avatar">
                  <img src= "${pageContext.request.contextPath}/tweet?action=get_image">
              </div>
            <div class="info">
              <h1> <c:out value="${user.fullName}" /> </h1>
              <h2> @<c:out value="${user.userName}" /> </h2>
            </div>
          </div>

          <!-- Post Twits -->
          <div class="PostTwits">
            <form action="tweet" method="post">
              <input type="hidden" name="action" value="postTweet">
              <textarea name="tweet" class="twitInput" placeholder="What's Happening"> </textarea>
              <input type="submit" name="submit" value="Tweet" class="btn">
            </form>
          </div>

          <!-- Who to Follow -->
          <div class="Follow">
            Who to Follow Block
          </div>

          <!-- Trends -->
          <div class="Trends">
            Trends Block
          </div>

          <!-- Twits -->
          
              <div class ="Feed">
                  <c:forEach items="${tweets}" var = "tweet"> 
                        <h2>@${tweet.tweetUserID}</h2>
                        <h2>${tweet.twit}</h2>
                        <h2>${tweet.time}</h2>
                        <a href="tweet?action=deleteTweet&tweetID=${tweet.tweetID}"> Delete </a>
                  </c:forEach>
                    
              </div>
             
          
        </div>

    </body>

</html>
