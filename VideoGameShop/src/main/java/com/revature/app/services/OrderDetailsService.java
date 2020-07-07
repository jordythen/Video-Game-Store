package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.OrderDetails;
import com.revature.app.data.OrderDetailsDAO;

public class OrderDetailsService implements GenericService<OrderDetails> {

	OrderDetailsDAO oDao;
	
	@Autowired
	public OrderDetailsService(OrderDetailsDAO o) {
		oDao = o;
	}
	
	@Override
	public Integer add(OrderDetails t) {
		// TODO Auto-generated method stub
		return oDao.save(t).getId();
	}

	@Override
	public OrderDetails getById(Integer id) {
		// TODO Auto-generated method stub
		return oDao.findById(id).get();
	}

	@Override
	public List<OrderDetails> getAll() {
		// TODO Auto-generated method stub
		return oDao.findAll();
	}

	@Override
	public void update(OrderDetails t) {
		// TODO Auto-generated method stub
		Optional<OrderDetails> orderDetails = oDao.findById(t.getId());
		if(orderDetails.isPresent()) {
			try {
				oDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(OrderDetails t) {
		// TODO Auto-generated method stub
		oDao.delete(t);
	}

}
