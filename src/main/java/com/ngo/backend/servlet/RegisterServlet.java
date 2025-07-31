/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author DELL
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            
            
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into user(username,password,role,status)values(?,?,?,'pending')");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            
            ps.executeUpdate();
       
            resp.sendRedirect("login.jsp");
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
            
    }           
}
