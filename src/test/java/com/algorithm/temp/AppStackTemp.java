package com.algorithm.temp;

import java.util.Stack;

public class AppStackTemp {

    public static void main(String[] args) {
        int[] arr = {12, 9, 8, 10, 13, 15, 10, 8, 14, 16};
        int[] num = new int[arr.length];
        //
        for (int i = 0; i < num.length; i++) {
            num[i] = 0;
        }
        //
        Stack<Element> stackTemp = new Stack<>();
        Element element = null;
        stackTemp.push(element);
        for (int i = arr.length - 1; i >= 0; i--) {
            while ((element = stackTemp.pop()) != null) {
                if (arr[i] < element.getData()) {
                    num[i] = element.getIndex() - i;
                    stackTemp.push(element);
                    stackTemp.push(new Element(arr[i], i));
                    break;
                }
            }
            if (element == null) {
                num[i] = 400;
                stackTemp.push(element);
                stackTemp.push(new Element(arr[i], i));
            }
        }
        //
        System.out.println("\narray:");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%4d", arr[i]);
        }
        //
        System.out.println("\nnumbers:");
        for (int i = 0; i < num.length; i++) {
            System.out.printf("%4d", num[i]);
        }
    }
}
