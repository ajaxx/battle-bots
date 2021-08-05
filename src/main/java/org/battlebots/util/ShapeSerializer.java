package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dyn4j.geometry.*;

import java.io.IOException;

public class ShapeSerializer extends JsonSerializer<Shape> {
    @Override
    public void serialize(final Shape value,
                          final JsonGenerator gen,
                          final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        if (value instanceof Circle) {
            final Circle circle = (Circle) value;
            final double radius = circle.getRadius();
            final Vector2 center = circle.getCenter();
            gen.writeObjectField("type", "circle");
            gen.writeObjectField("radius", radius);
            gen.writeObjectField("x", center.x);
            gen.writeObjectField("y", center.y);
        } else if (value instanceof Polygon) {
            final Polygon polygon = (Polygon) value;
            gen.writeObjectField("type", "polygon");
            gen.writeStringField("points", verticesToPoints(polygon.getVertices()));
            gen.writeArrayFieldStart("vertices");
            for (Vector2 vertex : polygon.getVertices()) {
                serialize(vertex, gen, serializers);
            }
            gen.writeEndArray();
        }

        gen.writeEndObject();
    }

    public void serialize(final Vector2 value,
                          final JsonGenerator gen,
                          final SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("x", value.x);
        gen.writeObjectField("y", value.y);
        gen.writeEndObject();
    }

    public String verticesToPoints(final Vector2[] vertices) {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter ="";
        for(int ii = 0 ; ii < vertices.length; ii++) {
            final Vector2 vertex = vertices[ii];
            stringBuilder.append(delimiter);
            stringBuilder.append(vertex.x).append(',').append(vertex.y);
            delimiter = " ";
        }
        return stringBuilder.toString();
    }
}
