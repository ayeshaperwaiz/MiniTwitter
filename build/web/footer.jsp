<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Footer</title>
        <link rel="stylesheet" href="styles/main.css">
    </head>
    <body>
        <div class="footer"> 
        <%--
            <jsp:useBean id="date" class="java.util.Date" />
            Today's date: <fmt:formatDate value="${date}"pattern="MM/dd/yyyy"/>
            <%=new java.util.Date() %> 
            --%>
            
            <div class ="footerDate">
                <script class = "footerDate">document.write(new Date());</script>
            </div>
            </div>   
    </body>
</html>