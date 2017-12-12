package com.sec;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import com.sec.dao.EmployeeDAO;
import com.sec.model.Employee;
import java.util.Iterator;
import java.util.Random;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class AppTest /*extends AbstractTransactionalJUnit4SpringContextTests */{
    @Autowired
    private EmployeeDAO employeeDAO;
 
    @Test
    public void testSave(){
        Employee emp = new Employee();
        int rand = new Random().nextInt(1000);
        emp.setId(rand);
        emp.setName("Pankaj");
        emp.setRole("Java Developer");
        employeeDAO.save(emp);
        emp.setName(emp.getName() + "_update");
        employeeDAO.save(emp);
 
        Iterable<Employee> employees = employeeDAO.findAll();
        Iterator<Employee> iterator = employees.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
 
    }
}