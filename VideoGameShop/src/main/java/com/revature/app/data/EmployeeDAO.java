package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Employee;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Integer>{

}
