package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Shape;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BodySerializerTest {
    @Test
    public void canSerializeBody() throws IOException {
        SerializerProvider serializerProvider = null;
        JsonGenerator jsonGenerator = Mockito.mock(JsonGenerator.class);

        BodySerializer serializer = new BodySerializer();
        Body body = new Body();
        body.addFixture(Geometry.createCircle(100.0));

        serializer.serialize(body, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, Mockito.times(3)).writeStartObject();
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeFieldName(
                Mockito.eq("transform"));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeArrayFieldStart(
                Mockito.eq("fixtures"));
        Mockito.verify(jsonGenerator, Mockito.times(3)).writeEndObject();
    }
}