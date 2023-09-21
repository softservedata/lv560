package com.algorithm.sort;

public class Sort {

    public void insertInt(int[] a) {
        int element = 0;
        for (int i = 1; i < a.length; i++) {
            element = a[i];
            int j = 0;
            for (j = i - 1; (j >= 0) && (a[j] > element); j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = element;
        }
    }

    public void bubbleInt(int[] a) {
        int t = 0;
        boolean isContinue = true;
        for (int i = 0; isContinue && i < a.length - 1; i++) {
            isContinue = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                //System.out.print(".");
                if (a[j] > a[j + 1]) {
                    t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    isContinue = true;
                }
            }
        }
    }


    public void mergeInt(int[] a) {
        mergeInt(a, 0, a.length - 1);
    }

    private void mergeInt(int[] a, int idxBegin, int idxEnd) {
        if (idxBegin < idxEnd) {
            int idxAvg = (idxBegin + idxEnd) / 2;
            mergeInt(a, idxBegin, idxAvg);
            mergeInt(a, idxAvg + 1, idxEnd);
            mergeIntArray(a, idxBegin, idxAvg, idxEnd);
        }
    }

    private void mergeIntArray(int[] a, int idxBegin, int idxAvg, int idxEnd) {
        int n = idxEnd - idxBegin + 1;
        int[] temp = new int[n];
        int kBegin = idxBegin;
        int kEnd = idxAvg + 1;
        for (int i = 0; i < n; i++) {
            if ((kBegin <= idxAvg) && (kEnd <= idxEnd)) {
                if (a[kBegin] < a[kEnd]) {
                    temp[i] = a[kBegin];
                    kBegin++;
                } else {
                    temp[i] = a[kEnd];
                    kEnd++;
                }
            } else if (kBegin <= idxAvg) {
                temp[i] = a[kBegin];
                kBegin++;
            } else {
                temp[i] = a[kEnd];
                kEnd++;
            }
        }
        for (int i = 0; i < n; i++) {
            a[idxBegin + i] = temp[i];
        }
    }

}
