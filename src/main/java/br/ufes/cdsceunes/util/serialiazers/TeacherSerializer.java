package br.ufes.cdsceunes.util.serialiazers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.ufes.cdsceunes.model.Department;
import br.ufes.cdsceunes.model.Teacher;

public class TeacherSerializer extends JsonSerializer<Teacher> {

	@Override
	public void serialize(Teacher teacher, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeNumberField("id", teacher.getId());
		jgen.writeStringField("name", teacher.getName());
		jgen.writeStringField("login", teacher.getDetails().getLogin());
		provider.defaultSerializeField("available", teacher.isAvailable(), jgen);
		provider.defaultSerializeField("admissionDate", teacher.getAdmissionDate(), jgen);
		provider.defaultSerializeField("returnFromLastRemoval", teacher.getReturnFromLastRemoval(), jgen);
		provider.defaultSerializeField("returnFromCapacitacion", teacher.getReturnFromCapacitacion(), jgen);

		// Department
		jgen.writeObjectFieldStart("department");
		Department department = teacher.getDepartment();
		jgen.writeNumberField("id", department.getId());
		jgen.writeStringField("name", department.getName());
		jgen.writeStringField("center", department.getCenter());
		jgen.writeEndObject();
		
		jgen.writeArrayFieldStart("classes");
		teacher.getClasses().forEach(c -> {
			try {
				jgen.writeStartObject();
				jgen.writeStringField("name", c.getDiscipline().getName());
				jgen.writeObjectField("semester", c.getSemester());
				jgen.writeEndObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		jgen.writeEndArray();
			
			

		jgen.writeEndObject();

	}

}
