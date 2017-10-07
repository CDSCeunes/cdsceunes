package br.ufes.cdsceunes.controller.api.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.controller.AbstractController;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.repository.PreferencesRepository;

@RequestMapping("/api/v1/preferences")
@RestController
public class PreferencesController extends AbstractController<Preferences, PreferencesRepository> {


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Preferences> updatePreference(@PathVariable Long id, @RequestBody Preferences preference) {
		Preferences p = repository.findOne(id);
		if (p == null || p.getId() != preference.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.saveAndFlush(preference);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Preferences> save(@RequestBody Preferences preference) {
		repository.save(preference);
		return new ResponseEntity<Preferences>(preference, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Preferences> delete(@PathVariable("id") Long id) {
		Preferences p = repository.findOne(id);
		if (p != null) {
			repository.delete(p);
			return new ResponseEntity<Preferences>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Preferences>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/class/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Preferences>> findPreferencesByClasses(@PathVariable("id") Long id) {
		List<Preferences> prefs = repository.findByClass(id);
		if (prefs.isEmpty()) {
			return new ResponseEntity<List<Preferences>>(prefs, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Preferences>>(prefs, HttpStatus.OK);
	}
}
