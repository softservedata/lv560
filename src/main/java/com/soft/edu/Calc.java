package com.soft.edu;

public class Calc {
	//private int number = 10;

	public Calc(int number) {
		System.out.println("public Calc(int number): number = " + this.number);
		this.number = number;
	}

	private int number = 10;

	{
		System.out.println("Init Block: number = " + number);
		number = 110;
	}

	//private int number = 10;

//	{
//		number = 111;
//	}

	public int sumDigits() {
		int sum = 0;
//		int z = 21;
		while (number != 0) {
			sum = sum + number % 10;
			number = Math.abs(number / 10); // Defect.
		}
		// /*
		try {
			number = 1000 / number;
//			if (number == 0) {
//				throw new RuntimeException("hahaha");
//			}
		} catch (Exception e) {
			System.out.println("ArithmeticException");
		}
		// */
		return sum;
	}

	public int countNonZeroDigits() {
		int count = 0;
		int temp = 0;
		while (number != 0) {
			temp = number % 10;
			if (temp > 0) {
				count++;
			}
			number = number / 10;
		}
		return count;
	}
}

