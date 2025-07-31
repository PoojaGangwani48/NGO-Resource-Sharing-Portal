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
@WebServlet("/PostRequirement")
public class PostRequirement extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ngo_id =(Integer)req.getSession().getAttribute("user_id");
        String item_name = req.getParameter("item_name");
        int quantity =Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        
        Connection con;
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into requirements (ngo_id,item_name,quantity,description)values(?,?,?,?)");
            ps.setInt(1,ngo_id);
            ps.setString(2, item_name);
            ps.setInt(3, quantity);
            ps.setString(4, description);
            ps.executeUpdate();
            
            resp.sendRedirect("ngo_dashboard.jsp");
            
            
        } catch (Exception ex) {
            Logger.getLogger(PostRequirement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
}
