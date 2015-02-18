package io.shapeshifter;

import com.google.gson.Gson;
import org.junit.Test;

public class ShapeshifterTest {



    @Test
    public void testToJSON() throws Exception {
        TestClass1 a = new TestClass1();
        TestClass2 b = new TestClass2();
        a.setChild(b);
        b.setParent(a);

        Shapeshifter shapeshifter = new Shapeshifter();

        System.out.println(shapeshifter.toJSON(a));
    }

    @Test
    public void testToJSONSpeed() throws Exception {
        TestClass1 a = new TestClass1();
        TestClass2 b = new TestClass2();
        a.setChild(b);
        //b.setParent(a);

        Long nanoStart = System.nanoTime();
        for(int i = 0; i< 1000000;i++) {
            Shapeshifter shapeshifter = new Shapeshifter();
            shapeshifter.toJSON(a);
        }
        System.out.println("nano seconds taken :" + (System.nanoTime() - nanoStart));

        /*Long nanoStart2 = System.nanoTime();
        for(int i = 0; i< 100000;i++) {
            Gson gson = new Gson();
            gson.toJson(a);
        }
        System.out.println("nano seconds taken :" + (System.nanoTime() - nanoStart2));*/

    }
    @Test
    public void testToJSONCompareWithGson() throws Exception {
        TestClass1 a = new TestClass1();
        TestClass2 b = new TestClass2();
        a.setChild(b);


        Shapeshifter shapeshifter = new Shapeshifter();

        String shapeshifterToJson =  shapeshifter.toJSON(a);

        Gson gson = new Gson();

        String gsonToJson =  gson.toJson(a);

        System.out.println("shapeshifter: " + shapeshifterToJson);
        System.out.println("gson        : " + gsonToJson);

        assert(gsonToJson.equals(shapeshifterToJson));





    }


}