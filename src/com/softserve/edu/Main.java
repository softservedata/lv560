package com.softserve.edu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(
//                new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello. What is your name?");
        //String name = br.readLine();
        String name = sc.nextLine();
        System.out.println("How old are you?");
        //int age = Integer.parseInt(br.readLine());
        int age = sc.nextInt();

        System.out.println("Hello " + name);
        System.out.println("You are " + age);
        //
        //br.close();
    }
}
