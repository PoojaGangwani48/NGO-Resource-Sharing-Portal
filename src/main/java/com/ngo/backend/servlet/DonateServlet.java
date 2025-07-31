package com.ngo.backend.servlet;


import com.ngo.backend.db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/DonateServlet")
public class DonateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int donor_id = (Integer) session.getAttribute("user_id");
        int reqId = Integer.parseInt(req.getParameter("req_id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String message = req.getParameter("message");

        try {
            Connection con = DBConnection.getConnection();

            // ✅ Step 1: Insert the new donation FIRST
            PreparedStatement insertDonation = con.prepareStatement(
                "INSERT INTO donations (donor_id, req_id, quantity, message, status) VALUES (?, ?, ?, ?, ?)"
            );
            insertDonation.setInt(1, donor_id);
            insertDonation.setInt(2, reqId);
            insertDonation.setInt(3, quantity);
            insertDonation.setString(4, message);
            insertDonation.setString(5, "pending");
            insertDonation.executeUpdate();

            // ✅ Step 2: Get original quantity
            PreparedStatement ps2 = con.prepareStatement("SELECT quantity FROM requirements WHERE req_Id = ?");
            ps2.setInt(1, reqId);
            ResultSet rs = ps2.executeQuery();

            int originalQty = 0;
            if (rs.next()) {
                originalQty = rs.getInt("quantity");
            }

            // ✅ Step 3: Get total donated quantity now
            PreparedStatement ps3 = con.prepareStatement("SELECT SUM(quantity) AS total_donated FROM donations WHERE req_id = ?");
            ps3.setInt(1, reqId);
            ResultSet rs2 = ps3.executeQuery();

            int totalDonated = 0;
            if (rs2.next()) {
                totalDonated = rs2.getInt("total_donated");
            }

            int remainingQty = originalQty - totalDonated;
            String newStatus = (remainingQty <= 0) ? "fulfilled" : "pending"; // or "pending"

            // ✅ Step 4: Update requirements
            PreparedStatement ps4 = con.prepareStatement("UPDATE requirements SET quantity = ?, status = ? WHERE req_id = ?");
            ps4.setInt(1, Math.max(0, remainingQty));
            ps4.setString(2, newStatus);
            ps4.setInt(3, reqId);
            ps4.executeUpdate();
            if (remainingQty <= 0) {
            // Update all pending donations for this req_id to fulfilled
            PreparedStatement ps5 = con.prepareStatement("UPDATE donations SET status = 'fulfilled' WHERE req_id = ? AND status = 'pending'");
            ps5.setInt(1, reqId);
            ps5.executeUpdate();
}


            resp.sendRedirect("my_donations.jsp");

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.getWriter().println("Error Occurred: " + ex.getMessage());
        }
    }
}
