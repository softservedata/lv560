package com.softserve.edu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public final String FILE_NOT_FOUND_EXCEPTION = "File %s could not be found";
	public final String FILE_NOT_READ_EXCEPTION = "File %s could not be read";
	public final String FILE_NOT_CLOSE_EXCEPTION = "File %s could not be closed";
	public final String PATH_SEPARATOR = "/";
	private final int MAIN_BY_NUMBER_SHEET = 0;

	public String name;
	public String path;

	public ExcelReader(String name) {
		this.name = name;
		this.path = this.getClass().getResource(PATH_SEPARATOR + name).getPath(); // .substring(1);
		System.out.println("***PATH = " + path);
	}

	public List<List<String>> getAllCells() {
		return getAllCells(path);
	}

	public List<List<String>> getAllCells(String path) {
		List<List<String>> allCells = new ArrayList<List<String>>();
		InputStream inputStream = null;
		DataFormatter formatter = new DataFormatter();
		// For *.xls files
		// HSSFWorkbook workBook;
		// For *.xlsx files
		// XSSFWorkbook workBook = null;
		XSSFWorkbook workBook = null;
		Sheet sheet = null;
		try {
			// System.out.println("filename=" + connection);
			inputStream = new FileInputStream(path);
			// workBook = new HSSFWorkbook(inputStream);
			workBook = new XSSFWorkbook(inputStream);
			// sheet = (new XSSFWorkbook(inputStream)).getSheetAt(0);
			sheet = workBook.getSheetAt(MAIN_BY_NUMBER_SHEET);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(String.format(FILE_NOT_FOUND_EXCEPTION, path));
		} catch (IOException e) {
			throw new RuntimeException(String.format(FILE_NOT_READ_EXCEPTION, path));
		}
		System.out.println("sheet.getLastRowNum() = " + sheet.getLastRowNum());
		//
		Iterator<Row> rowsIterator = sheet.iterator();
		while (rowsIterator.hasNext()) {
			Row row = rowsIterator.next();
			Iterator<Cell> cellsIterator = row.iterator();
			List<String> allRowCells = new ArrayList<String>();
			// System.out.println("\n ***allRowCells = ");
			System.out.println("row.getLastCellNum() = " + row.getLastCellNum());
			while (cellsIterator.hasNext()) {
				// For Old Version of XSSFWorkbook
				//String cell = cellsIterator.next().getStringCellValue();
				// For New Version of XSSFWorkbook
				Cell cell = cellsIterator.next();
				String cellData;
				if (cell.getCellType() == CellType.FORMULA) {
					cellData = String.valueOf(cell.getNumericCellValue());
				} else {
					cellData = formatter.formatCellValue(cell);
				}
				//String cell = formatter.formatCellValue(cellsIterator.next());
//				if ((cell == null) || cell.isEmpty()) {
//					cell = "*";
//				}
				// allCells.add(cellsIterator.next().getStringCellValue());
				// System.out.println("\t\t*** cell = " + cell);
				//allRowCells.add(cell);
				allRowCells.add(cellData);
				// System.out.print(" " + cell);
			}
			allCells.add(allRowCells);
			// System.out.println();
		}
		if (workBook != null) {
			try {
				workBook.close();
			} catch (IOException e) {
				// TODO DEvelop Custom Exceptions
				throw new RuntimeException(String.format(FILE_NOT_CLOSE_EXCEPTION, path), e);
			}
		}
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(String.format(FILE_NOT_CLOSE_EXCEPTION, path), e);
			}
		}
		return allCells;
	}

}
