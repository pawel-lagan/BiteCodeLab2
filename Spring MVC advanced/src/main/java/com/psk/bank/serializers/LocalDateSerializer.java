package com.psk.bank.serializers;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateSerializer extends JsonSerializer<LocalDateTime> {

	 public static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	 
	    @Override
	    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	        gen.writeString(value.format(FORMATTER));
	    }
	
	
}
