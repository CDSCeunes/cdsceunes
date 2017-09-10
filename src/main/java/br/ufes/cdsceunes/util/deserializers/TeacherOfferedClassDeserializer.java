package br.ufes.cdsceunes.util.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.ufes.cdsceunes.model.Discipline;
import br.ufes.cdsceunes.model.OfferedClass;

public class TeacherOfferedClassDeserializer extends JsonDeserializer<OfferedClass> {

	@Override
	public OfferedClass deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

}
