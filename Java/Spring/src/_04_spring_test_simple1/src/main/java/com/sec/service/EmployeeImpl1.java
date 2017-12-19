package com.sec.service;

import org.springframework.stereotype.Service;

@Service("employeeImpl1")
public class EmployeeImpl1 implements Employee{

	@Override
	public void work() {
		System.out.println("hello world 1");
	}

}