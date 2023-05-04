package com.softserve.edu;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class MyThread implements Runnable {
    CyclicBarrier cbar;
    String name;
    MyThread(CyclicBarrier c, String n) {
        cbar = c;
        name = n;
        new Thread(this).start();
    }
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Started: " + name);
            Thread.sleep(3000);
            cbar.await();
            System.out.println("\tContinue ... " + name);
            Thread.sleep(1000);
            System.out.println("\t\tDone ... " + name);
        } catch (BrokenBarrierException exc) {
            System.out.println(exc);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
    }
}

class BarAction implements Runnable {
    public void run() {
        System.out.println("Барьер достигнут");
    }
}

public class AppBarrier {

    public static void main(String args[]) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
        System.out.println("Запуск потоков");
        new MyThread(cb, "A");
        new MyThread(cb, "B");
        new MyThread(cb, "C");
        //
        new MyThread(cb, "X");
        new MyThread(cb, "Y");
        new MyThread(cb, "Z");
    }

}
