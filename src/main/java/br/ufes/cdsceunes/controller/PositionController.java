package br.ufes.cdsceunes.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.converter.CommissionEditor;
import br.ufes.cdsceunes.dao.CommissionDAO;
import br.ufes.cdsceunes.dao.PositionDAO;
import br.ufes.cdsceunes.model.Commission;
import br.ufes.cdsceunes.model.Position;

@Controller
@Transactional
@RequestMapping("/positions")
@RestController
public class PositionController extends AbstractController {

	@Autowired
	private PositionDAO positions;

	@Autowired
	private CommissionDAO commissions;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Commission.class, new CommissionEditor(commissions));
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Position>> listAllPositions() {
		List<Position> list = positions.list();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Position>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Position>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Position> updatePosition(@PathVariable Long id, @RequestBody Position position) {
		positions.update(position);
		return new ResponseEntity<Position>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
		Position p = positions.findById(id);
		if (p != null) {
			return new ResponseEntity<Position>(p, HttpStatus.OK);
		}
		return new ResponseEntity<Position>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Position> save(@RequestBody Position position) {
		positions.save(position);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Position> delete(@PathVariable("id") Long id) {
		Position position = positions.findById(id);
		if (position != null) {
			positions.delete(position);
			return new ResponseEntity<Position>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Position>(HttpStatus.BAD_REQUEST);
	}
	/*
	 * @RequestMapping("/") public ModelAndView list() { ModelAndView mad = new
	 * ModelAndView("position/list");
	 * mad.addObject("positions",positions.list()); return mad; }
	 * 
	 * @RequestMapping(value = "/form", name = "addPosition") public
	 * ModelAndView form(@ModelAttribute Position position) { ModelAndView mad =
	 * new ModelAndView("position/form");
	 * mad.addObject("commissionList",commissions.list()); return mad; }
	 * 
	 * @RequestMapping(method = RequestMethod.POST, name="createPosition",
	 * value="save") public ModelAndView save(@ModelAttribute("position") @Valid
	 * Position position, BindingResult binding, RedirectAttributes
	 * redirectAttributes) { if (binding.hasErrors()) { return form(position); }
	 * positions.save(position); redirectAttributes.addFlashAttribute("sucess",
	 * "Cargo cadastrado com sucesso!"); return new ModelAndView("redirect:"); }
	 */
}
