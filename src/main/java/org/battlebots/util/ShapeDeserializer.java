package org.battlebots.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.dyn4j.geometry.Shape;

import java.io.IOException;

public class ShapeDeserializer extends JsonDeserializer<Shape> {
    @Override
    public Shape deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}
