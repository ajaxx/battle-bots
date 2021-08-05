package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Transform;

import java.io.IOException;
import java.util.List;

public class TransformSerializer extends JsonSerializer<Transform> {
    @Override
    public void serialize(final Transform value,
                          final JsonGenerator gen,
                          final SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeObjectField("x", value.getTranslationX());
        gen.writeObjectField("y", value.getTranslationY());
        gen.writeEndObject();
    }
}
