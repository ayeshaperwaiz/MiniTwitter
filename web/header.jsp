<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/nav.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        
    </head>
    <body>
    <div class="header">
        <c:if test="${user != null && success ==true}">
       
                <!-- Header -->
                <nav>
                  <ul>
                    <li><a href="home.jsp">Home</a></li>
                    <li><a href="membership?action=notifications">Notifications</a></li>
                    <li><a href ="signup.jsp">Profile</a></li>
                   
                    <a style="color: white" href="membership?action=logout">Logout</a> 
                  </ul>
                </nav>
            </div>
        </c:if>
    </body>
</html>