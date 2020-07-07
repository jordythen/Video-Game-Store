package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.app.beans.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

	public Category findByName(String name);
}
