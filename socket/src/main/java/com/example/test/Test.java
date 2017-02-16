package com.example.test;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class Test {

    public static void main(String[] args){
//        int i=5;
//        工作月份/12+(工作月份%12>0.5?1:0.5)
//        Double A=i/12+(i%12>0.5?1:0.5);
//        System.out.println("值："+A);
        int[] numbers={1,5,2,6,3,5,1};
        bubbleSort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }

    public static void bubbleSort(int[] numbers) {
        int temp; // 记录临时中间值
        int size = numbers.length; // 数组大小
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (numbers[i] > numbers[j]) { // 交换两数的位置
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

}
