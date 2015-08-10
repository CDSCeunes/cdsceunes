package br.ufes.cdsceunes.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<T> {

	@Autowired
	protected EntityManager manager;
	
	public abstract T findById(Integer id);
	
	public abstract void save(T object);
	
	public abstract List<T> list();
	
}
