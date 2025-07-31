package com.ngo.backend.servlet;







/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.ngo.backend.db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        try {
            Connection conn = DBConnection.getConnection();
            String query ="select * from user where username=? and password=? and status='approved'";
            PreparedStatement ps =conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                String role = rs.getString("role");
                HttpSession session = req.getSession();
                session.setAttribute("user_id",rs.getInt("user_id"));
                session.setAttribute("role", role);
                
                if(role.equalsIgnoreCase("ngo"))
                {
                    resp.sendRedirect("ngo_dashboard.jsp");
                }
                else if(role.equalsIgnoreCase("donor"))
                {
                    resp.sendRedirect("donor_dashboard.jsp");
                }
                else if(role.equalsIgnoreCase("admin"))
                {
                    resp.sendRedirect("admin_dashboard.jsp");
                }
                else
                {
                    req.setAttribute("error", "Invalid user role.");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
                
            }
            else
            {
                req.setAttribute("error", "Invalid username/password or not approved.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
           
            
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
}
