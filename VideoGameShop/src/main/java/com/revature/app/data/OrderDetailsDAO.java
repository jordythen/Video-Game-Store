package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.app.beans.OrderDetails;

public interface OrderDetailsDAO extends JpaRepository<OrderDetails,Integer>{

}
