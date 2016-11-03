package br.ufes.cdsceunes.util.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.ufes.cdsceunes.model.Semester;

public class SemesterDeserializer extends JsonDeserializer<Semester> {

	@Override
	public Semester deserialize(JsonParser jpar, DeserializationContext ctx)
			throws IOException, JsonProcessingException {
		ObjectCodec codec = jpar.getCodec();
		JsonNode node = codec.readTree(jpar);
		return new Semester(node.get("year").asText(), node.get("semester").asText());
	}

}
