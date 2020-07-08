package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {

	public Category findByName(String name);
}
