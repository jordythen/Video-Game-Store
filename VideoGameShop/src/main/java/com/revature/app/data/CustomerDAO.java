package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.app.beans.Customer;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {

}
