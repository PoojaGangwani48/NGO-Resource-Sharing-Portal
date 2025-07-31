<%-- 
    Document   : post_requirement
    Created on : Jul 3, 2025, 6:57:45 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Requirement Page</title>
    </head>
    <body>
        <h1>Post Requirement</h1>
        <form action="PostRequirement" method="post">
            <label>Item:</label><input type="text" name="item_name"/><br><br>
            <label>Quantity:</label><input type="number" name="quantity"/><br><br>
            <label>Description:</label><br><textarea type="description" name="description"></textarea><br><br><br>
            <input type ="submit" value="Post Requirement"/><br><br>
        </form>
    </body>
</html>
