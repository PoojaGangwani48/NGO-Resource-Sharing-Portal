<%-- 
    Document   : register.jsp
    Created on : Jul 3, 2025, 6:08:19 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h2>Register</h2>
        <form action="RegisterServlet" method="post">
            <label> Username:</label><input name="username" type="text" placeholder="Enter Your Name"><br><br>
            <label> Password:</label><input name="password" type="password" placeholder="Enter Your Password"/><br><br>
            <label> role:</label><input name="role" type="text" placeholder="Enter Your role"><br><br>
            <input type="submit" value="Register"/>
            
        </form>
    </body>
</html>
