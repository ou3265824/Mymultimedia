package com.example.test;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class Test {

    public static void main(String[] args){
        int i=5;
//        工作月份/12+(工作月份%12>0.5?1:0.5)
        Double A=i/12+(i%12>0.5?1:0.5);
        System.out.println("值："+A);
    }

}
