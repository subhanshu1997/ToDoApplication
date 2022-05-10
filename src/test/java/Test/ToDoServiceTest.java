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
import javax.enterprise.inject.Model;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceTest {
    
    @Spy
    ToDoService s = new ToDoService();
    
    
    @Test
    public void authenticateTest(){
        when(s.authenticateUser("admin", "admin")).thenReturn(Boolean.TRUE);
        Boolean b = s.authenticateUser("admin", "admin");
        System.out.println(b);
        Assert.assertEquals(b,Boolean.TRUE);
    }
    
    @Test
    public void getTask(){
        List<Task> l= new ArrayList<>();
        Task t = new Task();
        t.setCheck(0);
        t.setDescription("Hello");
        t.setLastUpdate(new Date(System.currentTimeMillis()));
        t.setUsername("admin");
        t.setTaskname("taskname");
        l.add(t);
        when(s.getTasks("admin")).thenReturn(l);
        List<Task> ll = s.getTasks("admin");
        Assert.assertEquals(l,ll);
    }
    
    
}
