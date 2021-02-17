package com.softserve.edu;

import java.util.Set;
import java.util.TreeSet;

class Employee implements Comparable<Employee> {
	private String name;

	public Employee(String name) {
		this.name = name;
	}

	public int compareTo(Employee emp) {
		if (emp == null) {
			return 1;
		}
		return name.compareTo(emp.name);
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + "]";
	}
}

public class Appl {
	public static void main(String[] args) {
		/*-
		// int[] arr = { 1, 2, 3, 4 };
		// List<Integer> list = Arrays.asList(arr); // Compile Error
		// List<Integer> list = Arrays.asList(1, 2, 3, 4 ); // Ok
		//List<Integer> list = Arrays.asList(new int[] { 1, 2, 3, 4 }); // Compile Error
		List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3, 4 }); // Ok
		//list.clear(); // Runtime Error
		list.set(0, 5);
		System.out.println(list);
		//new Appl().equals(null);
		*/
		Set<Employee> set = new TreeSet<>((a,b) -> 1);
		set.add(new Employee("ivan"));
		set.add(new Employee("ivan"));
		set.add(null);
		set.add(null);
		System.out.println(set);
	}
}
