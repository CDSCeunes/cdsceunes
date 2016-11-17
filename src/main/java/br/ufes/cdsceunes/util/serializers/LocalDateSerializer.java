package br.ufes.cdsceunes.util.serializers;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {
	@Override
	public void serialize(LocalDate date, JsonGenerator jgen, SerializerProvider prov)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		String str = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
		jgen.writeStringField("hue", str);
		System.out.println("calling it " + str);
		jgen.writeEndObject();

	}

}
