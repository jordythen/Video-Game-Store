package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Order;
import com.revature.app.data.OrderDAO;

public class OrderService implements GenericService<Order>{

	OrderDAO oDao;
	
	@Autowired
	public OrderService(OrderDAO o) {
		oDao = o;
	}
	
	@Override
	public Integer add(Order t) {
		// TODO Auto-generated method stub
		return oDao.save(t).getId();
	}

	@Override
	public Order getById(Integer id) {
		// TODO Auto-generated method stub
		return oDao.findById(id).get();
	}

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return oDao.findAll();
	}

	@Override
	public void update(Order t) {
		// TODO Auto-generated method stub
		Optional<Order> order = oDao.findById(t.getId());
		if(order.isPresent()) {
			try {
				oDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Order t) {
		// TODO Auto-generated method stub
		oDao.delete(t);
	}

}
