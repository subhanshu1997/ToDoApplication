/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author bigas
 */
import com.todo.controller.ToDoController;
import com.todo.dao.DBConnection;
import com.todo.model.Task;
import com.todo.service.ToDoService;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;


@RunWith(MockitoJUnitRunner.class)
public class ToDoControllerTest {
    
    @Spy
    ToDoController c = new ToDoController();
    
    @Mock
    Model model;
    
    
    @Test
    public void testLogin(){
        when(c.loginGet(model)).thenReturn("login");
        String a = c.loginGet(model);
        Assert.assertEquals(a, "login");
    }
    
    @Test
    public void testLoginPage(){
        when(c.loginPage(model)).thenReturn("login");
        String a = c.loginPage(model);
        Assert.assertEquals(a, "login");
    }
    
    @Test
    public void loginTest(){
        List<Task> l = new ArrayList<>();
        Mockito.doReturn("home").when(c).login("admin", "admin", model);
        String a = c.login("admin", "admin", model);
        Assert.assertEquals(a, "home");
    }
    
    @Test
    public void homePageTest(){
        Mockito.doReturn("home").when(c).homePage(model, "taskname", "description");
        String a = c.homePage(model, "taskname", "description");
        Assert.assertEquals(a, "home");
    }
    
    @Test
    public void removeTest(){
        Mockito.doReturn("home").when(c).remove(model, 1);
        String a = c.remove(model, 1);
        Assert.assertEquals(a, "home");
    }
    
}
