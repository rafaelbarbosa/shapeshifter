package io.shapeshifter.serializers;

import io.shapeshifter.PropertyAlreadySerializedException;
import io.shapeshifter.Serializer;

import java.util.Map;
import java.util.Set;

/**
 * User: rafael
 * Date: 16/02/15
 * Time: 23:42
 */
public class MapSerializer extends Serializer<Map> {
    @Override
    public void serialize(Map obj, StringBuilder builder) {
        builder.append("{");

        Set<Map.Entry> entrySet = obj.entrySet();

        for(Map.Entry entry:entrySet){
            int currentLength = builder.length();
            try {
                this.getShapeshifterInstance().toJSON(entry.getKey(),builder);
                builder.append(":");
                this.getShapeshifterInstance().toJSON(entry.getValue(),builder);
                builder.append(",");
            } catch (PropertyAlreadySerializedException e) {
                //rollback whatever
                builder.delete(currentLength-1, builder.length());
            }
        }
        //ugly but i dont really care
        if(builder.lastIndexOf(",") == builder.length()-1) {
            builder.deleteCharAt(builder.length()-1);
        }

        builder.append("}");

    }
}
