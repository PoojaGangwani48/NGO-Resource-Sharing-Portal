<%-- 
    Document   : view_requrirements.jsp
    Created on : Jul 4, 2025, 11:39:57 AM
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
            Connection con = DBConnection.getConnection();
            String sqlqur ="Select* from requirements where status='open'";
            PreparedStatement ps = con.prepareStatement(sqlqur);
            ResultSet rs = ps.executeQuery();
            
          %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Available Requrirements Page</title>
    </head>
    <body>
        <h1>Open Requirements</h1>
        <table border="1">

            <tr><th>NGO-ID</th><th>Item</th><th>Quantity</th><th>Description</th><th>Action</th></tr>
            <% while(rs.next()){%>
            <tr>
                <td><%=rs.getString("ngo_id")%></td>
                <td><%= rs.getString("item_name")%></td>
                <td><%=rs.getInt("quantity")%></td>
                <td><%=rs.getString("description")%></td>
                <td>
                    <form action ="DonateServlet" method="post">
                    <input type="hidden" name="req_id" value="<%=rs.getInt("req_id")%>"/>
                    <input type="number" name="quantity" min="1" required/>
                    <input type="submit" value="Donate"/>
                    </form>
                </td>
            </tr>
            <%}%>
        </table>
    </body>
</html>