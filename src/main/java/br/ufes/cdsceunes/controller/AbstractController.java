package br.ufes.cdsceunes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;

public abstract class AbstractController<T, T_Repository extends JpaRepository> {
	
	@Autowired
	protected T_Repository repository;
	
	@JsonView(View.Summary.class)
	@RequestMapping(method=RequestMethod.GET, value="")
	public List<T> listAll() {
		return repository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<T> findOne(@PathVariable("id") Long id) {
		T var = (T) repository.findOne(id);
		if (var != null) {
			return new ResponseEntity<T>(var, HttpStatus.OK);
		}
		return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
	}
	

}
