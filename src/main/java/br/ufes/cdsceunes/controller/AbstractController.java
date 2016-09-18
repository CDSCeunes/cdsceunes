package br.ufes.cdsceunes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class AbstractController<T, T_Repository extends JpaRepository> {
	
	@Autowired
	protected T_Repository repository;
	
	@RequestMapping(method=RequestMethod.GET, value="")
	public List<T> listAll() {
		return repository.findAll();
	}

}
