package br.ufes.cdsceunes.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.ufes.cdsceunes.model.Preferences;

public class PreferencesSerializer extends JsonSerializer<Preferences> {

	@Override
	public void serialize(Preferences prefs, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeNumberField("id", prefs.getId());
		jgen.writeStringField("preference", prefs.getValue().toString());

		// Teacher info
		jgen.writeObjectFieldStart("teacher");
		jgen.writeNumberField("id", prefs.getTeacher().getId());
		jgen.writeStringField("name", prefs.getTeacher().getName());
		jgen.writeEndObject();

		// Discipline info

		jgen.writeObjectFieldStart("discipline");
		jgen.writeNumberField("id", prefs.getDiscipline().getId());
		jgen.writeStringField("name", prefs.getDiscipline().getName());
		jgen.writeEndObject();

		jgen.writeEndObject();
	}

}
