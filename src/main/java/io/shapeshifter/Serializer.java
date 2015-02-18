package io.shapeshifter;

/**
 * User: rafael
 * Date: 16/02/15
 * Time: 22:26
 */
public abstract class Serializer<T> {


    private Shapeshifter instance;

    public void setShapeshifterInstance(Shapeshifter instance){
        this.instance = instance;
    }

    public Shapeshifter getShapeshifterInstance(){
        return this.instance;
    }
    public abstract void serialize(T obj,StringBuilder builder);


}
