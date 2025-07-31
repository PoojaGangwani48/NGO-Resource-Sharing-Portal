
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%-- 
    Document   : approve_users
    Created on : Jul 7, 2025, 2:57:01 PM
    Author     : DELL
--%>
<%@page import="com.ngo.backend.db.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("role")==null||!"admin".equals(session.getAttribute("role")))
    {
        response.sendRedirect("login.jsp");
        return;
}
Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement("select *from user where status='pending'");
ResultSet rs = ps.executeQuery();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Approve Users Page</title>
    </head>
    <body>
        <h1>Pending Users</h1>
            <table border="1">
                <tr><th>Username</th><th>Role</th><th>Action</th></tr>
                    <% while(rs.next()){%>
        
            <tr>
                <td><%= rs.getString("username")%></td>
                <td><%= rs.getString("role")%></td>
                <td>
                    <form action="AdminApproveServlet" method="post">
                        <input type="hidden" name="user_id" value="<%= rs.getInt("user_id")%>"/>
                        <input type="submit" value="Approve"/> 
                    </form>
                </td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
