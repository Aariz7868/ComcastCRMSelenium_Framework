package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable {
		FileInputStream fis = new FileInputStream("./testData/testdatacrm.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getCellFormula();
		
		return data;
		
	}
	
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("/ComcastCRMGUIFramework/testData/testdatacrm.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		
		return rowCount;
	}
	
	public void setDataFromExcel(String sheetName, int rowNum, int cellNum, String data) throws Throwable {
		FileInputStream fis = new FileInputStream("/ComcastCRMGUIFramework/testData/testdatacrm.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Cell cel= wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		cel.setCellType(CellType.STRING);
		 cel.setCellValue(data);
		
		 FileOutputStream fos = new FileOutputStream("/ComcastCRMGUIFramework/testData/testdatacrm.xlsx");
		 wb.write(fos);
		 wb.close();
		
		
		
	}

}
