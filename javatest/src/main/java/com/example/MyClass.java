package com.example;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MyClass {

    public static void main(String[] args){
        Map map=new HashMap<>();
        map.put(null,null);
        System.out.println(map.get(null));
        Map map1=new Hashtable<>();
        map1.put(null,null);
        System.out.println(map1.get(null));

    }

}
