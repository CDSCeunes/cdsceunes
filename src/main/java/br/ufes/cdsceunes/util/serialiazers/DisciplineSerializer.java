package br.ufes.cdsceunes.util.serialiazers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.ufes.cdsceunes.model.Discipline;

public class DisciplineSerializer extends JsonSerializer<Discipline>{

	@Override
	public void serialize(Discipline disc, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeNumberField("id", disc.getId());
		jgen.writeStringField("name", disc.getName());
		jgen.writeStringField("course", disc.getCourse());
		jgen.writeNumberField("teoricLoad", disc.getTeoricLoad());
		jgen.writeNumberField("exerciseLoad", disc.getExerciseLoad());
		jgen.writeEndObject();
		
	}

}
