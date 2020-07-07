package com.revature.app.services;

import java.util.List;

public interface GenericService<T> {
	public Integer add(T t);
	public T getById(Integer id);
	public List<T> getAll();
	public void update(T t);
	public void delete(T t);
}
