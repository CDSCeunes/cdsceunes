package br.ufes.cdsceunes.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ResultScenario extends AbstractModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

}
