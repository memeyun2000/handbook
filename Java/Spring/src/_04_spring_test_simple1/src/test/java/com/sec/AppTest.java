package com.sec;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sec.service.Employee;

/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class AppTest {

    @Autowired
    @Qualifier("employeeImpl1")
    Employee employee;
    
    @Test
    public void sayHello(){
        employee.work();
    }
}
