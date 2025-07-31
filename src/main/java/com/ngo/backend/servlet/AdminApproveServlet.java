package com.ngo.backend.servlet;

import com.ngo.backend.db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
@WebServlet("/AdminApproveServlet")
public class AdminApproveServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int userId=Integer.parseInt(req.getParameter("user_id"));
       try(Connection con = DBConnection.getConnection();
               PreparedStatement ps= con.prepareStatement("update user set status='approved' where user_id=?")){
           ps.setInt(1, userId);
           ps.executeUpdate();
           resp.sendRedirect("approve_users.jsp");
           
       } catch (Exception ex) {
            Logger.getLogger(AdminApproveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
