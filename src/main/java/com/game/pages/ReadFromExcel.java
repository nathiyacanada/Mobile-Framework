package com.game.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {

	 XSSFSheet sheet;

	private static Logger log = LogManager.getLogger(ReadFromExcel.class
			.getName());

	public Object[][] readData(String testDataLocation, String testDataFileName) {
		log.info("Inside the ReadFromExcel : readData Method");
		String projectName = System.getProperty("user.dir");
		File file = getFileObject(projectName + "/" + testDataLocation,
				testDataFileName);

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException exception) {
			log.error("File Not found at the specfied location : "
					+ projectName + "/" + testDataLocation);
			exception.printStackTrace();
		}

		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);
			wb.close();

		} catch (IOException e) {
			log.error("IOException occured while working with workbook"
					+ e.getMessage());
			e.printStackTrace();
		}
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet
						.getRow(i + 1).getCell(j).toString());
			}
			obj[i][0] = datamap;

		}
		return obj;
	}

	public static File getFileObject(String filePath, String fileName) {
		return new File(filePath + "\\" + fileName);
	}
}
