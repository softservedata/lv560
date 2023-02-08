package com.softserve.lambda;

interface Vehicle1 {
    static void blowHorn() {
        System.out.println("Blowing horn!!!");
        //print(); // Error
    }

    default void print() {
        System.out.println("I am a vehicle!");
        // blowHorn(); Ok
    }

}


public class Appl implements Vehicle1 {

    public void my() {
        Vehicle1.super.print();
        //print();
        //blowHorn(); // Error
    }

    public static void main(String[] args) {
        //Vehicle1.blowHorn();
        new Appl().my();
        //
        //Vehicle1.super.print(); // Error
        //
        //Appl app = new Appl();
        //app.blowHorn(); // Error
        //app.super. // Error
    }
}
