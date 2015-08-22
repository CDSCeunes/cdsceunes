package br.ufes.cdsceunes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDAO<T, ID> {

	@PersistenceContext
	protected EntityManager manager;
	
	public abstract T findById(ID id);
	
	public abstract void save(T object);
	
	public abstract List<T> list();
	
}
