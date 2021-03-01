package com.softserve.edu;

import java.util.List;

public class ApplExcel {
	public static void main(String[] args) {
		ExcelReader er = new ExcelReader("user.xlsx");
		List<List<String>> res = er.getAllCells();
		for (List<String> row : res) {
			for (String cell : row) {
				if (cell.isEmpty() ) {
					System.out.print("*" + "  ");
				} else {
					System.out.print(cell + "  ");
				}
			}
			System.out.println();
		}
	}

}
