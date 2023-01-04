package com.edu;

import java.util.Arrays;
import java.util.Random;

public class AppSum {

    protected int sum1(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

    protected int sum2rec(int[] arr, int n) {
        if (n == 0) {
            return 0;
        }
        return arr[n - 1] + sum2rec(arr, n - 1);
    }

    int sum3merge(int[] arr, int p, int r)
    {
        if (p == r)
        {
            return arr[p];
        }
        int q = (p + r) / 2;
        return sum3merge(arr, p, q) + sum3merge(arr, q + 1, r);
    }

    public static void main(String[] args) {
        AppSum app = new AppSum();
        // Generate Random
        int[] arr = new Random().ints().limit(1000000).toArray();
        //System.out.println("Array = " + Arrays.toString(arr));
        long startTime = System.currentTimeMillis(); // from 1.01.1970 00:00.0
        //app.sum1(arr);
        //app.sum2rec(arr, arr.length);
        app.sum3merge(arr, 0, arr.length - 1);
        long endTime = System.currentTimeMillis();
        //System.out.println("time = " + ((endTime - startTime) / 1000.0));
        System.out.println("time = " + (endTime - startTime));
    }
}
