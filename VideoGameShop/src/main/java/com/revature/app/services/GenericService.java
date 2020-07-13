package com.revature.app.services;

import java.util.List;

public interface GenericService<T> {

	public Integer add(T t);
	public T findById(Integer id);
	public List<T> findAll();
	public Boolean update(T t);
	public Boolean delete(T t);
}
