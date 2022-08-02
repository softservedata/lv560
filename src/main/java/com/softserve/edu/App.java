package com.softserve.edu;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //String unicode = "\uD83C\uDDFA\uD83C\uDDE6\uD83E\uDDD0";
        String unicode ="\u041F\u0440\u0456\u0437\u0432\u0438\u0449\u0435, \u0456\u043C'\u044F, \u043F\u043E \u0431\u0430\u0442\u044C\u043A\u043E\u0432\u0456 \u043E\u0442\u0440\u0438\u043C\u0443\u0432\u0430\u0447\u0430";
        System.out.println("unicode: " + unicode);
        //
        Scanner sc = new Scanner(System.in);
        System.out.print("a = ");
        int a = sc.nextInt();
        System.out.print("b = ");
        int b = sc.nextInt();
        System.out.println("a + b = " + (a + b)); // Non testability
        sc.close();
    }
}
