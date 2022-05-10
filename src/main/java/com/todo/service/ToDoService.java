/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.service;

import com.todo.dao.DBConnection;
import com.todo.model.Customer;
import com.todo.model.Task;
import com.todo.util.AES;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author bigas
 */
@Service
public class ToDoService {
    Connection con=null;
   
    public ToDoService(){
        con = new DBConnection().createConnection();
    }
    
    //Function to check if tthe username and corresponding password exist in database
    public boolean authenticateUser(String username, String password){
        PreparedStatement statement=null;
        ResultSet result = null;
        try {
            statement = con.prepareStatement("SELECT username from user WHERE username = ? AND password = ?");
            statement.setString(1, username); 
            statement.setString(2, AES.encrypt(password, "basicKey"));
            result = statement.executeQuery();
            if(result.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ToDoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Function to create user table in HSQLDB
    public void createUserTable(){
        String createUser = "CREATE TABLE user " +
                   "(id INTEGER not NULL, " +
                   " username VARCHAR(255), " + 
                   " password VARCHAR(255), " +  
                   " PRIMARY KEY ( id ))";
        String insertUser = "INSERT INTO user" +
        "  (id, username, password) VALUES " +
        " (?, ?, ?);";

        try{Statement s=con.createStatement();
        s.execute(createUser);
        }catch(SQLException e){
            Logger.getLogger(ToDoService.class.getName()).log(Level.SEVERE, null, e);
        }
        try{
            Customer c = new Customer();
            c.setUsername("admin");
            c.setPassword(AES.encrypt("admin", "basicKey"));
        PreparedStatement p=con.prepareStatement(insertUser);
            p.setInt(1, c.getId());
            p.setString(2, c.getUsername());
            p.setString(3, c.getPassword());
            p.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ToDoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Function to create task table in HSQLDB
    public void createTaskTable(){
        String createTask ="CREATE TABLE task " +
                   "(id INTEGER not NULL, " +
                   " username VARCHAR(255), " + 
                   " taskname VARCHAR(255), " +
                   " description VARCHAR(255), " +
                   " lastupdate DATETIME, " +
                   " completed INTEGER, " +
                   " PRIMARY KEY ( id ))";
        try{Statement s=con.createStatement();
        s.execute(createTask);
        }catch(SQLException e){
            Logger.getLogger(ToDoService.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //Function to retrieve task of a particular user
    public List<Task> getTasks(String username){
        PreparedStatement statement=null;
        ResultSet result = null;
        List<Task> l= new ArrayList<>();
        try {
            statement = con.prepareStatement("SELECT * FROM task WHERE username = ?");
            statement.setString(1, username); 
            result = statement.executeQuery();
            while(result.next()){
                Task t = new Task();
                t.setId(result.getInt(1));
                t.setUsername(result.getString(2));
                t.setTaskname(result.getString(3));
                t.setDescription(result.getString(4));
                t.setLastUpdate(result.getDate(5));
                t.setCheck(result.getInt(6));
                l.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("Exception in getTasks");
            Logger.getLogger(ToDoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    //Function to create a task
    public void createTask(Task t ){
        String insertTask = "INSERT INTO task" +
        "  (id, username, taskname, description, lastupdate, completed) VALUES " +
        " (?, ?, ?, ?, ?, ?);";
        try{
        PreparedStatement p=con.prepareStatement(insertTask);
            p.setInt(1, t.getId());
            p.setString(2, t.getUsername());
            p.setString(3, t.getTaskname());
            p.setString(4, t.getDescription());
            p.setDate(5, t.getLastUpdate());
            p.setInt(6, t.getCheck());
            p.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Exception in create task");
            Logger.getLogger(ToDoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Function to remove a particular task
    public void removeTask(int id){
        String removeTask = "DELETE FROM task where id = ?";
        try{
        PreparedStatement p=con.prepareStatement(removeTask);
            p.setInt(1, id);
            p.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Exception in create task");
            Logger.getLogger(ToDoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
