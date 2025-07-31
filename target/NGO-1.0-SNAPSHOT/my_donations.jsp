<%-- 
    Document   : my_donations
    Created on : Jul 5, 2025, 6:04:31 PM
    Author     : DELL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.ngo.backend.db.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
     if(session.getAttribute("role")==null||!"donor".equals(session.getAttribute("role"))){
        response.sendRedirect("login.jsp");
        return;
 }
            int donorId = (Integer)session.getAttribute("user_id");
            Connection con = DBConnection.getConnection();
            String sql="select d.*,r.item_name from donations d join requirements r on d.req_id =r.req_id where  donor_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,donorId);
            ResultSet rs = ps.executeQuery();
            %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Donation Page</title>
        
    </head>
    <body>
        <h2>My Donations</h2>
        <table border ='1'>
            <tr><th>Item</th><th>Quantity</th><th>Status</th></tr>
            <% while (rs.next()){ %>
            <tr>
                <td><%= rs.getString("item_name")%></td>
                <td><%= rs.getInt("quantity")%></td>
                <td><%= rs.getString("status")%></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>


