package com.algorithm.temp;

public class AppTemp {
    public static void main(String[] args) {
        int[] arr = {12, 9, 8, 10, 13, 15, 10, 8, 14, 16}; // arr = new int[] {...}
        int[] num = new int[arr.length];
        //
        for (int i = 0; i < num.length; i++) {
            num[i] = 0;
        }
        //
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (arr[i] < arr[j]) {
                    num[i] = j - i;
                    break;
                }
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
