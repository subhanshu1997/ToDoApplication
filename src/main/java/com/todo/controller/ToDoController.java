/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.controller;

import com.todo.model.Customer;
import com.todo.model.Task;
import com.todo.service.ToDoService;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author bigas
 */
@Controller
public class ToDoController {
    Customer c = null;
    ToDoService s = new ToDoService();
    List<Task> l=null;
    //Function to create user table and task table
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage(Model model){
        s.createUserTable();
        s.createTaskTable();
        return "login";
    }
    
    //Mapping for login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model ){
        return "login";
    }
    
    //Mapping to authenticate user
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, Model model ){
        boolean result = s.authenticateUser(username, password);
        if(result){
            c=new Customer();
            c.setUsername(username);
            l = s.getTasks(c.getUsername());
            model.addAttribute("tasks",l);
            return "home";
        }
        model.addAttribute("error","Invalid username or password");
        System.out.println("Invalid username or password");
        return "login";
    }
    
    
    //Mapping to open homepage
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String homePage(Model model, @RequestParam String taskname, @RequestParam String description){
        Task t = new Task();
        t.setUsername(c.getUsername());
        t.setTaskname(taskname);
        t.setDescription(description);
        t.setLastUpdate(new Date(System.currentTimeMillis()));
        t.setCheck(0);
        s.createTask(t);
        l = s.getTasks(c.getUsername());
        model.addAttribute("tasks",l);
        return "home";
    }
    
    //Mapping to remove a task
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(Model model, @RequestParam Integer remove){
        s.removeTask(remove);
        l = s.getTasks(c.getUsername());
        model.addAttribute("tasks",l);
        return "home";
    }
    
}
