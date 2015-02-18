package io.shapeshifter.serializers;

import io.shapeshifter.PropertyAlreadySerializedException;
import io.shapeshifter.Serializer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * User: rafael
 * Date: 16/02/15
 * Time: 22:31
 */
public class ObjectSerializer extends Serializer<Object> {

    @Override
    public void serialize(Object obj, StringBuilder builder) {
        builder.append("{");

        for(Field field :obj.getClass().getDeclaredFields()){
            if(!Modifier.isTransient(field.getModifiers())){
                try {
                    boolean accessibleField = true;
                    if(!field.isAccessible()){
                        accessibleField = false;
                        field.setAccessible(true);
                    }
                    Object fieldValue = field.get(obj);
                    if(!accessibleField){
                        field.setAccessible(false);
                    }
                    if(fieldValue != null) {
                        //for eventual rollback
                        int currentLength = builder.length();
                        builder.append("\"");
                        builder.append(field.getName());
                        builder.append("\"");
                        builder.append(":");
    
                        try {
                            this.getShapeshifterInstance().toJSON(fieldValue, builder);
                            builder.append(",");
                        } catch (PropertyAlreadySerializedException e) {
                            //rollback on that
                            builder.delete(currentLength-1,builder.length());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if(builder.lastIndexOf(",") == builder.length()-1) {
            builder.deleteCharAt(builder.length()-1);
        }

        builder.append("}");
    }
}
