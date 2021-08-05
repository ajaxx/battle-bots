package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;

import java.io.IOException;
import java.util.List;

public class BodySerializer extends JsonSerializer<Body> {
    @Override
    public void serialize(final Body value,
                          final JsonGenerator gen,
                          final SerializerProvider serializers) throws IOException {

        final ShapeSerializer shapeSerializer = new ShapeSerializer();
        final TransformSerializer transformSerializer = new TransformSerializer();
        final List<BodyFixture> fixtures = value.getFixtures();

        gen.writeStartObject();

        gen.writeFieldName("transform");
        transformSerializer.serialize(value.getTransform(), gen, serializers);

        gen.writeArrayFieldStart("fixtures");
        for (BodyFixture fixture : fixtures) {
            shapeSerializer.serialize(fixture.getShape(), gen, serializers);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
