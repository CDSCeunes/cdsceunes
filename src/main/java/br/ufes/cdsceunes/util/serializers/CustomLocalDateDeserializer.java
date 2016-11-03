package br.ufes.cdsceunes.util.serializers;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class CustomLocalDateDeserializer extends StdScalarDeserializer<LocalDate> {

	private final static DateTimeFormatter LOCALDATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	@Override
	public LocalDate deserialize(JsonParser jParser, DeserializationContext deCxt)
			throws IOException, JsonProcessingException {
		String dateStr = "";
		String fieldName = "";
		while(jParser.hasCurrentToken()) {
			JsonToken token = jParser.nextToken();
			if (token == JsonToken.FIELD_NAME) {
				fieldName = jParser.getCurrentName();
			} else if (token == JsonToken.VALUE_STRING) {
				if (fieldName.equals("date")) {
					dateStr = jParser.getValueAsString();
				} else {
					throw new JsonParseException("Unexpected fieldname", jParser.getTokenLocation());
				}
			} else if (token == JsonToken.END_OBJECT) {
				break;
			}
		}
		return null;
	}
	
	public CustomLocalDateDeserializer() {
		super(LocalDate.class);
	}

}
