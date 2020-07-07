package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.app.beans.Order;

public interface OrderDAO extends JpaRepository<Order ,Integer> {

}
