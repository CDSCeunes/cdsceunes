package br.ufes.cdsceunes.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.ufes.cdsceunes.model.Teacher;

public class TeacherValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Teacher.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "admissionDate", "field.required");
		
		Teacher teacher = (Teacher) object;
		if (teacher.getName() == null) {
			errors.rejectValue("name", "field.required");
		}
		if (teacher.getDetails().getLogin() == null) {
			errors.rejectValue("login", "field.required");
		}
	}

}
