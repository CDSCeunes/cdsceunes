package br.ufes.cdsceunes.util.deserializers;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class LocalDateDeserializer extends JsonDeserializer {

	@Override
	public Object deserialize(JsonParser jpar, DeserializationContext ctx) throws IOException, JsonProcessingException {
		System.out.println(jpar.getValueAsString());
		String[] str = jpar.getValueAsString().split("-");
		if (str.length == 3) {
			return LocalDate.of(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
		}
		System.out.println("failed conversion");
		return null;
	}

}
