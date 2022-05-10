/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.dao;

/**
 *
 * @author bigas
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    public Connection createConnection() {
      Connection con = null;
      
      try {
         //Registering the HSQLDB JDBC driver
         Class.forName("org.hsqldb.jdbc.JDBCDriver");
         //Creating the connection with HSQLDB
         con = DriverManager.getConnection("jdbc:hsqldb:file/DB12", "SA", "");
         if (con!= null){
            System.out.println("Connection created successfully");
         }else{
            System.out.println("Problem with creating connection");
         }
      }  catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace(System.out);
      }
      return con;
   } 
}
