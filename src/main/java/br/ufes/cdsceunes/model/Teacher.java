package br.ufes.cdsceunes.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.ufes.cdsceunes.util.serializers.TeacherSerializer;

@Entity
@Table(name = "teacher")
@JsonSerialize(using = TeacherSerializer.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Teacher extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@ColumnDefault(value = "true")
	private Boolean available;

	@OneToOne
	private UserDetails details;

	@OneToMany(mappedBy = "teacher")
	private List<OfferedClass> classes;

	// @Temporal(value = TemporalType.DATE)
	@Column(columnDefinition = "DATE")
	private LocalDate admissionDate;

	// @Temporal(value = TemporalType.DATE)
	@Column(columnDefinition = "DATE")
	private LocalDate returnFromLastRemoval;

	// @Temporal(value = TemporalType.DATE)
	@Column(columnDefinition = "DATE")
	private LocalDate returnFromCapacitacion;

	@OneToMany(mappedBy = "teacher")
	private List<Position> positions;

	@OneToMany(mappedBy = "teacher")
	private List<Preferences> preferences;

	@ManyToOne
	private Department department;

	public Teacher() {
		preferences = new LinkedList<>();
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

	public List<OfferedClass> getClasses(String year, String semester) {
		Semester semester_ = new Semester(year, semester);
		return this.getClasses().stream().filter(e -> e.getSemester() == semester_).collect(Collectors.toList());
		
	}

	@Override
	public String toString() {
		return name;
	}

	public UserDetails getDetails() {
		return details;
	}

}
