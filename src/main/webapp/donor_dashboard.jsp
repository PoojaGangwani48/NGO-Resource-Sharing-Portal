<%-- 
    Document   : donor_dashboard
    Created on : Jul 6, 2025, 5:19:50 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Donor Dashboard Page</title>
    </head>
    <body>
        <h1>Donor Dashboard</h1>
        <form action="view_requrirements.jsp" >
            <input type="submit" value="See Requirement"/><br><br>
        </form>
        <form action="my_donations.jsp">
            <input type ="submit" value="My Donations List"/><br><br>
        </form>
        <form action="login.jsp">
            <input type ="submit" value="Logout"/><br><br>
        </form>
    </body>
</html>
