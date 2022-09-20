package com.softserve.edu;

public class AppCarBuilder {
	public static void main(String[] args) {
		Car car2 = Car.builder()
				.setMazdaCX()
				.setBlack()
				.setPetrol()
				.setAutomat()
				.setLed()
				.build();
		System.out.println("car2 = " + car2);
		//
		Car car = Car.builder()
				.setBMWx5()
				.setRed()
				.setPetrol()
				.setAutomat()
				.setHalogen()
				.build();
		System.out.println("car = " + car);
	}
}
