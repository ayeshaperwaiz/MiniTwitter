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
                
                <!-- User Info Block -->
                <div class ="userInfo">
                    <div class="info">
                        <h1><c:out value="${user.fullName}" /></h1>
                        <h2> @<c:out value="${user.userName}" /> </h2>
                    </div>
                </div>
                <br/>
                    
                <!-- Trends Block -->
                <div class ="Trends">
                    Trends Block
                    <c:forEach items="${hashtags}" var="hashtag">
                        <p>
                            
                            ${hashtag.hashtagText}
                            
                        </p>
                        
                    </c:forEach>
                </div>
                
            </div>
            
            
            <!-- Column 2 -->
            <div class="col2">
                
                <!-- Post New Tweet -->
                <div class ="PostTwits">
                    <form action="tweet" method="post">
                        <input type="hidden" name="action" value="postTweet">
                        <textarea name="tweet" class="twitInput" placeholder="What's happening?"> </textarea>
                        <input type="submit" name="submit" value="Tweet" class="btn">
                    </form>
                </div>
                
                <!-- Feed -->
                <div class="Feed">
                    <c:forEach items="${tweets}" var = "tweet">
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
               
            </div>
        
        
            <!-- Column 3 -->
            <div class="col3">
                
                <!-- Who to follow -->
                <div class ="Follow">
                    <div class="wtf"> <h2> Who to Follow </h2> </div>
                    <c:forEach items="${users}" var = "user"> 
                        <div class="followBlock">                      
                            <div class="followName"><h2>${user.fullName}</h2></div>
                            <div class="followUser"><h2>@${user.userName}</h2></div>
                            <div class="followIcon">
                                <i class="fa fa-user"></i>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
           

        </div>
                    
        
       
    </body>

</html>