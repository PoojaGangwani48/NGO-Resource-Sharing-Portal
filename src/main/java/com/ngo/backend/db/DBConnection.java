package com.ngo.backend.db;



import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class DBConnection {
    public static Connection getConnection() throws Exception 
    {
        String url ="jdbc:mysql://localhost:3306/ngodb";
        String username="root";
        String password ="admin";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,username,password);
    }
    
}
