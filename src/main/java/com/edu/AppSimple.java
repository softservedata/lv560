package com.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppSimple {
    private static List<Byte[]> objects = new ArrayList<>();
//    private static List<Byte[]> objects;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        //
        System.out.println("Welcome to Memory Tool!");
        do {
            System.out.println("\n\nI have " + objects.size() + " objects in use, about "
                    + (objects.size() * 10) + " MB."
                    + "\nWhat would you like me to do?\n"
                    + "1. Create some objects\n"
                    + "2. Remove some objects\n"
                    + "0. Quit");
            input = sc.nextLine();
            if ((input != null) && (input.length() > 0)) {
                if (input.startsWith("1")) {
                    createObjects();
                }
                if (input.startsWith("2")) {
                    removeObjects();
                }
            }
        } while (!input.startsWith("0"));
    }

    private static void createObjects() {
        System.out.println("Creating objects...");
        for (int i = 0; i < 2; i++) {
            objects.add(new Byte[10 * 1024 * 1024]);
        }
    }

    private static void removeObjects() {
        System.out.println("Removing objects...");
        int start = objects.size() - 1;
        int end = start - 2;
        for (int i = start; ((i >= 0) && (i > end)); i--) {
            objects.remove(i);
        }
    }
}
