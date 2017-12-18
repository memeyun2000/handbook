package com.sec.service.impl;

import org.springframework.stereotype.Service;

import com.sec.service.EmployService;

@Service("employService2")
public class EmployServiceImpl2 implements EmployService{
    
	public void startWork() {
		System.out.println("Employee2 worked.");
	}
}