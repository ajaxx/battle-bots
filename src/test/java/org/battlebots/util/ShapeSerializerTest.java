package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Shape;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ShapeSerializerTest {
    @Test
    public void canSerializeCircle() throws IOException {
        SerializerProvider serializerProvider = null;
        JsonGenerator jsonGenerator = Mockito.mock(JsonGenerator.class);

        ShapeSerializer serializer = new ShapeSerializer();
        Shape shape = Geometry.createCircle(100.0);
        shape.translate(5.0d, 10.0d);

        serializer.serialize(shape, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, Mockito.times(1)).writeStartObject();
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObjectField(
                Mockito.eq("type"), Mockito.eq("circle"));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObjectField(
                Mockito.eq("radius"), Mockito.eq(100.0d));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObjectField(
                Mockito.eq("x"), Mockito.eq(5.0d));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObjectField(
                Mockito.eq("y"), Mockito.eq(10.0d));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeEndObject();
    }

    @Test
    public void canSerializePolygon() throws IOException {
        SerializerProvider serializerProvider = null;
        JsonGenerator jsonGenerator = Mockito.mock(JsonGenerator.class);

        ShapeSerializer serializer = new ShapeSerializer();
        Shape shape = Geometry.createPolygon(
                new Vector2(0.0,  0.0),
                new Vector2(10.0, 10.0),
                new Vector2(0.0, 20));
        shape.translate(5.0d, 10.0d);

        serializer.serialize(shape, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, Mockito.times(4)).writeStartObject();
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObjectField(
                Mockito.eq("type"), Mockito.eq("polygon"));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeStringField(
                Mockito.eq("points"), Mockito.eq("5.0,10.0 15.0,20.0 5.0,30.0"));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeArrayFieldStart(
                Mockito.eq("vertices"));
        Mockito.verify(jsonGenerator, Mockito.times(4)).writeEndObject();
    }
}