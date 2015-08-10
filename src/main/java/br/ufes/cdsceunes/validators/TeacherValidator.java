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
		Teacher teacher = (Teacher) object;
		if (teacher.getAge() == 0) {
			errors.rejectValue("age", "field.required");
		}
	}

}
