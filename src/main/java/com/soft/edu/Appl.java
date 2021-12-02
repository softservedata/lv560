package com.soft.edu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Appl {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = 1;
		int k = 1;
		System.out.print("num=");
		k = k + 1;
		try {
			num = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println("I/O Error.");
		}
		//
		System.out.println(num);
		Calc calc = new Calc(num);
		System.out.print("Sum Digits=" + calc.sumDigits());
		// Calc calc2 = calc;
		// Delete/Unload Calc.class from JVM
		Calc calc2 = new Calc(num);
		System.out.print("\nCount Digits=" + calc.countNonZeroDigits());
		System.out.print("\nCount Digits=" + calc2.countNonZeroDigits());
	}
}
