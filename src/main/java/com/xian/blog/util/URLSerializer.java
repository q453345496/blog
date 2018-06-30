package com.xian.blog.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class URLSerializer extends JsonSerializer<String> {

	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if (StringUtils.isBlank(value)) {
			gen.writeString(value);
		} else {
			gen.writeString("http://192.168.15.165:80/xian_test/" + value);
		}
	}

}
