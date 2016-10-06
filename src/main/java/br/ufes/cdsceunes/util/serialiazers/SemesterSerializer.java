package br.ufes.cdsceunes.util.serialiazers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.ufes.cdsceunes.model.Semester;

public class SemesterSerializer extends JsonSerializer<Semester> {

	@Override
	public void serialize(Semester model, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeStringField("year", model.getId().getYear());
		jgen.writeStringField("semester", model.getId().getSemester());
		jgen.writeEndObject();

	}

}