package com.sec.service;

import org.springframework.stereotype.Service;

@Service("employeeImpl2")
public class EmployeeImpl2 implements Employee{
    @Override
    public void work() {
        System.out.println("hello world 2");
    }
}