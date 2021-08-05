package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

import java.io.IOException;

public class Vector2Serializer extends JsonSerializer<Vector2> {
    @Override
    public void serialize(final Vector2 value,
                          final JsonGenerator gen,
                          final SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeObjectField("x", value.x);
        gen.writeObjectField("y", value.y);
        gen.writeEndObject();
    }
}
