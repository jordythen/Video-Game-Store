package com.revature.app.data;

import com.revature.app.beans.Order;

public interface OrderDAO extends GenericDAO<Order>{
	
	public Order getByUsername(String name);

}
