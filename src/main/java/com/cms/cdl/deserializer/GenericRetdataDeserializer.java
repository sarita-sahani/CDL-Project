package com.cms.cdl.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;

public class GenericRetdataDeserializer<T> extends JsonDeserializer<T> {

    private final Class<T> targetClass;

    public GenericRetdataDeserializer(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode rootNode = p.getCodec().readTree(p);

        if (rootNode.isTextual()) {
            return null;
        }

        JsonNode retdataNode = rootNode.get("retdata");
        if (retdataNode == null || retdataNode.isNull()) {
            return null;
        }

        return p.getCodec().treeToValue(retdataNode, targetClass);
    }
}
