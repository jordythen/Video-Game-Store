package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.Employee;
import com.revature.app.data.EmployeeDAO;

@Service
public class EmployeeService implements GenericService<Employee> {
	
	EmployeeDAO eDao;

	@Autowired
	public EmployeeService(EmployeeDAO e) {
		eDao = e;
	}

	@Override
	public Integer add(Employee t) {
		// TODO Auto-generated method stub
		return eDao.save(t).getId();
	}

	@Override
	public Employee getById(Integer id) {
		// TODO Auto-generated method stub
		return eDao.findById(id).get();
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return eDao.findAll();
	}

	@Override
	public void update(Employee t) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = eDao.findById(t.getId());
		if(employee.isPresent()) {
			try {
				eDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Employee t) {
		// TODO Auto-generated method stub
		eDao.delete(t);
	}
	
	
}
