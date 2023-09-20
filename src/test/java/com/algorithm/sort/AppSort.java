package com.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class AppSort {

    public static void main(String[] args) {
        System.out.println("start");
        Sort sort = new Sort();
        //
        //int[] arr = {4, 2, 7, 1, 8, 3, 9, 5}; // new int[] {4, 2, 7, 1, 8, 3, 9, 5};
        //int[] arr = {4, 3, 2, 1};
        //int[] arr = {1, 2, 3, 4};
        //
        int n = 100000;
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(n);
        }
        //
        long timeStart = System.currentTimeMillis();
        //
        //System.out.println("Original: " + Arrays.toString(arr));
        sort.insertInt(arr);
        //sort.bubbleInt(arr);
        //sort.mergeInt(arr);
        //Arrays.sort(arr);
        //System.out.println("\nSorted: " + Arrays.toString(arr));
        //
        long timeEnd = System.currentTimeMillis();
        System.out.println("Duration = " + 1.0 * (timeEnd - timeStart) / 1000.0);
    }
}
