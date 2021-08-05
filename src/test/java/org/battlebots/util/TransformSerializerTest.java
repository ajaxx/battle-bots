package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TransformSerializerTest {
    @Test
    public void canSerializeTransformWithTranslate() throws IOException {
        SerializerProvider serializerProvider = null;
        JsonGenerator jsonGenerator = Mockito.mock(JsonGenerator.class);

        TransformSerializer serializer = new TransformSerializer();
        Transform transform = new Transform();
        transform.translate(2.0, 3.0);

        serializer.serialize(transform, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, Mockito.times(1)).writeStartObject();
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObjectField(
                Mockito.eq("x"), Mockito.eq(2.0d));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObjectField(
                Mockito.eq("y"), Mockito.eq(3.0d));
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeEndObject();
    }
}