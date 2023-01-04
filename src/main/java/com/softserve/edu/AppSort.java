package com.edu;

import java.util.Arrays;
import java.util.Random;

public class AppSort {

    public int[] sort1(int[] arr) {
        boolean inProgress = true;
        int tmp;
        for (int i = 0; inProgress && i < arr.length - 1; i++) { // i= 0, 1, 2
            inProgress = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    inProgress = true;;
                }
            }
        }
        return arr;
    }

    private void merge(int[] arr, int p, int q, int r)
    {
        int n = r - p + 1;
        int[] arrtemp = new int[n];
        //
        int ip = p;
        int iq = q + 1;
        int current = 0;
        //
        for (int i = 0; i < n; i++) {
            if ((ip <= q) && (iq <= r))
            {
                if (arr[ip] < arr[iq])
                {
                    current = arr[ip];
                    ip++;
                }
                else
                {
                    current = arr[iq];
                    iq++;
                }
            }
            else if (ip <= q)
            {
                current = arr[ip];
                ip++;
            }
            else
            {
                current = arr[iq];
                iq++;
            }
            //
            arrtemp[i] = current;	 // O(n)
        }
        //
        for (int i = 0; i < n; i++)
        {
            arr[p + i] = arrtemp[i]; // O(n)
        }
    }

    // Overload
    private void sortByMerge(int[] arr, int p, int r) {
        if (p < r)
        {
            int q = (p + r) / 2;
            sortByMerge(arr, p, q);
            sortByMerge(arr, q + 1, r);
            //
            merge(arr, p, q, r);
        }
    }

    // Overload
    public void sortByMerge(int[] arr)
    {
        sortByMerge(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        AppSort app = new AppSort();
        // Generate Random
        int[] arr = new Random().ints().limit(100000).toArray();
        //System.out.println("Array = " + Arrays.toString(arr));
        long startTime = System.currentTimeMillis(); // from 1.01.1970 00:00.0
        //app.sort1(arr);
        //app.sortByMerge(arr);
        Arrays.sort(arr);
        long endTime = System.currentTimeMillis();
        //System.out.println("time = " + ((endTime - startTime) / 1000.0));
        System.out.println("time = " + (endTime - startTime));
    }

}
