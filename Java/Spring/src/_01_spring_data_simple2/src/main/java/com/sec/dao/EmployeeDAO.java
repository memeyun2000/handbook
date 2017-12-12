package com.sec.dao;

import com.sec.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDAO extends CrudRepository<Employee,Integer> {
}