package org.battlebots.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dyn4j.geometry.Vector2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class Vector2SerializerTest {
    @Test
    public void canSerializeVector2() throws IOException {
        SerializerProvider serializerProvider = null;
        JsonGenerator jsonGenerator = Mockito.mock(JsonGenerator.class);
        Vector2Serializer serializer = new Vector2Serializer();
        serializer.serialize(new Vector2(2.0, 3.0), jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, Mockito.times(1)).writeStartObject();
        Mockito.verify(jsonGenerator, Mockito.times(2)).writeObjectField(
                Mockito.anyString(), Mockito.any());
        Mockito.verify(jsonGenerator, Mockito.times(1)).writeEndObject();
    }
}