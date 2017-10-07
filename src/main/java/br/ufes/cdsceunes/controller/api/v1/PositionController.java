package br.ufes.cdsceunes.controller.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.controller.AbstractController;
import br.ufes.cdsceunes.model.Position;
import br.ufes.cdsceunes.repository.PositionRepository;

@RequestMapping("/api/v1/positions")
@RestController
public class PositionController extends AbstractController<Position, PositionRepository> {

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Position> updatePosition(@PathVariable Long id, @RequestBody Position position) {
		Position p = repository.findOne(id);
		if (p == null || p.getId() != position.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.saveAndFlush(position);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Position getPositionById(@PathVariable Long id) {
		return repository.findOne(id);
	}*/

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Position> save(@RequestBody Position position) {
		repository.save(position);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Position> delete(@PathVariable("id") Long id) {
		Position position = repository.findOne(id);
		if (position != null) {
			repository.delete(position);
			return new ResponseEntity<Position>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Position>(HttpStatus.BAD_REQUEST);
	}
}
