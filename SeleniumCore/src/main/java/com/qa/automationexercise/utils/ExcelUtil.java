package com.qa.automationexercise.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/AutomationExercise.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String SheetName) {
		
		System.out.println("Reading data from excle file!");
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(SheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i = 0; i < sheet.getLastRowNum(); i++) { 
				for(int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) { 
					data[i][j] = sheet.getRow(i+1).getCell(j).toString(); 
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			System.out.println("File is encrypted" + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Some other error" + e);
			e.printStackTrace();
		}
		
		return data;
	}
}
