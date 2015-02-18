package io.shapeshifter.serializers;

import io.shapeshifter.Serializer;

/**
 * User: rafael
 * Date: 16/02/15
 * Time: 22:50
 */
public class StringSerializer extends Serializer<String> {
    @Override
    public void serialize(String obj, StringBuilder builder) {
        if(obj != null){
            builder.append("\"");
            builder.append(obj);
            builder.append("\"");
        }
        else{
            builder.append("null");
        }
    }
}
