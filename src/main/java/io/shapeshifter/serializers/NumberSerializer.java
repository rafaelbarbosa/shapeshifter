package io.shapeshifter.serializers;

import io.shapeshifter.Serializer;

/**
 * User: rafael
 * Date: 16/02/15
 * Time: 23:30
 */
public class NumberSerializer extends Serializer<Number> {
    @Override
    public void serialize(Number obj, StringBuilder builder) {
        builder.append(obj.toString());
    }
}
