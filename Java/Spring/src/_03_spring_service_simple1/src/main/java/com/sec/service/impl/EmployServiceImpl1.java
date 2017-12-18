package com.sec.service.impl;

import org.springframework.stereotype.Service;

import com.sec.service.EmployService;

@Service("employService1")
public class EmployServiceImpl1 implements EmployService{
    
	public void startWork() {
		System.out.println("Employee1 worked.");
	}
}