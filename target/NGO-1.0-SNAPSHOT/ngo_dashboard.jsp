<%-- 
    Document   : dashboard.jsp
    Created on : Jul 3, 2025, 6:43:57 PM
    Author     : DELL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.ngo.backend.db.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            if(session.getAttribute("role")==null||!"ngo".equals(session.getAttribute("role")))
            {
            response.sendRedirect("login.jsp");
            return;
            }
            int ngoId=(Integer)session.getAttribute("user_id");
            Connection con =DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from requirements where ngo_id=?");
            ps.setInt(1,ngoId);
            ResultSet rs = ps.executeQuery();
            
         %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NGO Dashboard Page</title>
    </head>
    <body>
                <h1>NGO Dashboard</h1>
        <form action="post_requirement.jsp">
            <input type="submit" value="Post New Requirement"/><br><br>
        </form>
                <form action="login.jsp" method="post">
            <input type="submit" value="Logout"/><br><br>
        </form>
        <h2>Your Requirements</h2>
        <table border="1">
            <tr><th> Item </th><th> Quantity</th><th>Status</th></tr>
           <%while(rs.next()){%>
            <tr>
                <td><%=rs.getString("item_name")%></td>
                <td><%=rs.getInt("quantity")%></td>
                <td><%=rs.getString("status")%></td>
            </tr>
            <%}%>
            
        </table>
        
    </body>
</html>
