package br.ufes.cdsceunes.repository;

import java.util.List;
import java.util.Map;

public interface RepositoryCustom<T> {
	public List<String> save(Map<String, Object> updates, T object);
	
	public List<String> save(T updates, T object);
}
