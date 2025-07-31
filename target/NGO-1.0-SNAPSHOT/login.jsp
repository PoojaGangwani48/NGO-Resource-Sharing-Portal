<%-- 
    Document   : login
    Created on : Jul 3, 2025, 1:16:16 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
         <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
           %>
           <div style="color:red; font-weight:bold;"><%=error%></div>
         <%
            }
        %>
        <form action="LoginServlet" method="post">
            <h2>Login</h2>
            <label>UserName:</label> <input name="username" type="text" placeholder="Enter User Name"/><br><br>
            <label> Password:</label> <input name ="password" type="password" placeholder="Enter Your Password"/><br><br>
            <input type="submit" value="Login"/>
        </form>
      

    </body>
</html>
