package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class Rectangle2DSerializer extends JsonSerializer<Rectangle2D> {
    @Override
    public void serialize(final Rectangle2D value,
                          final JsonGenerator gen,
                          final SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeObjectField("x", value.getX());
        gen.writeObjectField("y", value.getY());
        gen.writeObjectField("w", value.getWidth());
        gen.writeObjectField("h", value.getHeight());
        gen.writeEndObject();
    }
}
