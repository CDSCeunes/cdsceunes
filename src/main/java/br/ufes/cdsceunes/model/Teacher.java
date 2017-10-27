package br.ufes.cdsceunes.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.TeacherView;

@Entity
@Table(name = "teacher")
/*@JsonSerialize(using = TeacherSerializer.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)*/
public class Teacher extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(TeacherView.Public.class)
	private Long id;

	@NotBlank
	@JsonView(TeacherView.Public.class)
	private String name;

	@ColumnDefault(value = "true")
	@JsonView(TeacherView.Public.class)
	private Boolean available;

	@OneToOne
	@JsonIgnore
	private UserDetails details;

	@OneToMany(mappedBy = "teacher")
	@JsonView(TeacherView.Public.class)
	private List<OfferedClass> classes;

	// @Temporal(value = TemporalType.DATE)
	@Column(columnDefinition = "DATE")
	@JsonView(TeacherView.Public.class)
	private LocalDate admissionDate;

	// @Temporal(value = TemporalType.DATE)
	@Column(columnDefinition = "DATE")
	@JsonView(TeacherView.Public.class)
	private LocalDate returnFromLastRemoval;

	// @Temporal(value = TemporalType.DATE)
	@Column(columnDefinition = "DATE")
	@JsonView(TeacherView.Public.class)
	private LocalDate returnFromCapacitacion;

	@OneToMany(mappedBy = "teacher")
	@JsonView(TeacherView.Public.class)
	private List<Position> positions;

	@OneToMany(mappedBy = "teacher")
	@JsonView(TeacherView.Public.class)
	private List<Preferences> preferences;

	@ManyToOne
	@JsonView(TeacherView.Public.class)
	private Department department;

	public Teacher() {
		preferences = new ArrayList<>();
		positions = new ArrayList<>();
		
	}

	/* Getters and Setters */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public LocalDate getReturnFromLastRemoval() {
		return returnFromLastRemoval;
	}

	public void setReturnFromLastRemoval(LocalDate returnFromLastRemoval) {
		this.returnFromLastRemoval = returnFromLastRemoval;
	}

	public LocalDate getReturnFromCapacitacion() {
		return returnFromCapacitacion;
	}

	public void setReturnFromCapacitacion(LocalDate returnFromCapacitacion) {
		this.returnFromCapacitacion = returnFromCapacitacion;
	}

	public List<OfferedClass> getClasses() {
		return classes;
	}

	public Boolean isAvailable() {
		return available;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<Preferences> getPreferences() {
		return preferences;
	}

	public Long getId() {
		return id;
	}

	public void setClasses(List<OfferedClass> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return name;
	}

	public UserDetails getDetails() {
		return details;
	}

}
