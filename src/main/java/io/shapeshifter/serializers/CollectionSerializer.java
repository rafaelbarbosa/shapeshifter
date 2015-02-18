package io.shapeshifter.serializers;

import io.shapeshifter.PropertyAlreadySerializedException;
import io.shapeshifter.Serializer;

import java.util.Collection;
import java.util.Iterator;

/**
 * User: rafael
 * Date: 16/02/15
 * Time: 23:37
 */
public class CollectionSerializer extends Serializer<Collection> {
    @Override
    public void serialize(Collection obj, StringBuilder builder) {
        builder.append("[");

        Iterator iterator = obj.iterator();


        while(iterator.hasNext()){
            try {
                this.getShapeshifterInstance().toJSON(iterator.next(),builder);
                builder.append(",");
            } catch (PropertyAlreadySerializedException e) {
                //ignore this, i dont really care.
            }
        }
        //ugly but i dont really care
        if(builder.lastIndexOf(",") == builder.length()-1) {
            builder.deleteCharAt(builder.length()-1);
        }
        builder.append("]");
    }
}
