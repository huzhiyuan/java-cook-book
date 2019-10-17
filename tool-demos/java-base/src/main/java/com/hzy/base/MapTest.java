package com.hzy.base;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        HashMap<String,String> test = new HashMap<>();
        test.put("a","1");
        test.put("b","2");

        ImmutableMap<String,String> test2 = ImmutableMap.copyOf(test);
        for(String key:test2.keySet()){
            System.out.println(key+"->"+test2.get(key));
        }

    }
}
