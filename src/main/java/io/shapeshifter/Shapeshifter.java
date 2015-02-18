package io.shapeshifter;

import io.shapeshifter.serializers.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * User: rafael
 * Date: 16/02/15
 * Time: 22:20
 */
public class Shapeshifter{

    Map<Class,Serializer> serializers;
    Map<Class,Set<Integer>> walkedProperties = new HashMap<Class,Set<Integer>>();

    public Shapeshifter(){
        serializers = new HashMap<Class, Serializer>();
        serializers.put(String.class, new StringSerializer());
        serializers.put(Number.class, new NumberSerializer());
        serializers.put(Collection.class, new CollectionSerializer());
        serializers.put(Map.class,new MapSerializer());
    }
    public String toJSON(Object obj) throws PropertyAlreadySerializedException {
        StringBuilder builder = new StringBuilder();
        this.toJSON(obj,builder);
        return builder.toString();
    }

    public void toJSON(Object obj,StringBuilder builder) throws PropertyAlreadySerializedException{

        if(walkedProperties.get(obj.getClass()) == null){
            walkedProperties.put(obj.getClass(),new HashSet<Integer>());
        }

        if(walkedProperties.get(obj.getClass()).contains(System.identityHashCode(obj))){
            throw new PropertyAlreadySerializedException();
        }
        else{
            walkedProperties.get(obj.getClass()).add(System.identityHashCode(obj));
        }

        Serializer serializer = null;
        if(serializers.containsKey(obj.getClass())) {
            serializer = serializers.get(obj.getClass());
        }
        else{
            for(Class clazz :serializers.keySet()){
                if(clazz.isAssignableFrom(obj.getClass())){
                    serializer = serializers.get(clazz);
                    //we add it here so the next time we dont incur in this performance penalty
                    serializers.put(obj.getClass(),serializer);
                    break;
                }
            }
            if(serializer == null) {
                serializer = new ObjectSerializer();
            }
        }
        serializer.setShapeshifterInstance(this);
        serializer.serialize(obj,builder);



    }
    public Map fromJSON(String json){
        return fromJSON(json,Map.class);
    }
    public  <T> T fromJSON(String json,Class<T> clazz){
        throw new NotImplementedException();
    }
}
