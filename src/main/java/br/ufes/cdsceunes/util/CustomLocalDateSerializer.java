package br.ufes.cdsceunes.util;


import java.io.IOException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomLocalDateSerializer extends StdSerializer<LocalDate> {
	
	private final static DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyy-MM-dd");

	@Override
	public void serialize(LocalDate date, JsonGenerator jgen, SerializerProvider sProvider) throws IOException {
		jgen.writeStartObject();
		jgen.writeStringField("date", DATE_FORMAT.print(date));
		jgen.writeEndObject();
	}
	
	public CustomLocalDateSerializer() {
		super(LocalDate.class);
	}
	
	protected CustomLocalDateSerializer(Class<LocalDate> t) {
		super(t);
	}
	

}
