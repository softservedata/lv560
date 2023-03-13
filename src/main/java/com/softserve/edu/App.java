package com.softserve.edu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

class Work {
    private int i = 0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    private void dec() {
        i--;
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Start Reflection");
        //
        Work work = new Work();
        Field f = work.getClass().getDeclaredField("i");
        f.setAccessible(true);
        //
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            f.setInt(work, ((int) f.get(work)) + 1);
            //f.set(work, ((Integer) f.get(work)) + 1);
        }
        System.out.println("Update field worktime = " + (System.currentTimeMillis() - startTime) / 1000.0);
        //System.out.println("work.getI() = " + work.getI());
        //
        Class<?>[] paramTypes = new Class[]{};
        Method geti = work.getClass().getDeclaredMethod("getI", paramTypes);
        geti.setAccessible(true);
        paramTypes = new Class[]{int.class};
        Method seti = work.getClass().getDeclaredMethod("setI", paramTypes);
        seti.setAccessible(true);
        //
        /*
        Object[] ar = new Object[] { };
        Object[] ar2;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ar2 = new Object[] { (int) geti.invoke(work, ar) - 1 };
            seti.invoke(work, ar2);
        }
        System.out.println("Update by methods worktime = " + (System.currentTimeMillis() - startTime)/1000.0);
        System.out.println("work.getI() = " + geti.invoke(work, ar));
        */
        /*
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            work.setI(work.getI() - 1);
        }
        System.out.println("Update by methods worktime = " + (System.currentTimeMillis() - startTime) / 1000.0);
        System.out.println("work.getI() = " + work.getI());
        */
        //
        paramTypes = new Class[]{};
        Method dec = work.getClass().getDeclaredMethod("dec", paramTypes);
        dec.setAccessible(true);
        Object[] ar = new Object[] { };
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            dec.invoke(work, ar);
        }
        System.out.println("Update by dec worktime = " + (System.currentTimeMillis() - startTime)/1000.0);
        System.out.println("work.getI() = " + work.getI());
    }
}
