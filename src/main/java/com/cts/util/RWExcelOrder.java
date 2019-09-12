package com.cts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cts.model.Order;

public class RWExcelOrder {

	File file;

	public RWExcelOrder() {
		file = new File("./src/main/resources/excel/order.xlsx");
	}

	public List<Order> readExcel(String inputFilePath) {
		FileInputStream fileInputStream = null;
		ArrayList<Order> orderList = null;
		try {
			fileInputStream = new FileInputStream(new File("./src/main/resources/excel/order.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			orderList = new ArrayList<>();
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				Order order = new Order();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						int tempId = (int) ce.getNumericCellValue();
						order.setOrderId(tempId + "");
					}
					if (j == 1) {
						order.setProdId(ce.getStringCellValue());
					}
					if (j == 2) {
						order.setUserID(ce.getStringCellValue());
					}
					if (j == 3) {
						order.setOrderDate(ce.getStringCellValue());
					}
				}
			}
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return orderList;
	}

	public Order writeExcel(Order order) {
		File file = new File("./src/main/resources/excel/order.xlsx");
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int rownum = 0;
		int cellnum = 0;
		if (file.exists() == false) {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("order");
		} else {
			try (InputStream is = new FileInputStream(file)) {
				workbook = new XSSFWorkbook(is);
				sheet = workbook.getSheetAt(0);
				rownum = sheet.getLastRowNum() + 1;
			} catch (FileNotFoundException e) {
				System.err.println("File not found");
			} catch (IOException e) {
				System.err.println("Input/Output exception happened");
			}
		}

		Row row = sheet.createRow(rownum++);

		Cell cell = row.createCell(cellnum++);
		cell.setCellValue(order.getOrderId());

		Cell cell2 = row.createCell(cellnum++);
		cell2.setCellValue(order.getOrderDate());

		Cell cell3 = row.createCell(cellnum++);
		cell3.setCellValue(order.getUserID());

		Cell cell4 = row.createCell(cellnum++);
		cell4.setCellValue(order.getProdId());
		try {
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Order cancelOrder(String orderId) {
		FileInputStream fileInputStream = null;
		Order order = null;
		try {
			fileInputStream = new FileInputStream(new File("./src/main/resources/excel/order.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				order = new Order();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						String tempId = ce.getStringCellValue();
						order.setOrderId(tempId + "");
					}
					if (j == 1) {
						order.setProdId(ce.getStringCellValue());
					}
					if (j == 2) {
						order.setUserID(ce.getStringCellValue());
					}
					if (j == 3) {
						order.setOrderDate(ce.getStringCellValue());
					}
				}
				if (order.getOrderId().equalsIgnoreCase(orderId)) {
					 sheet.removeRow(ro);
					 File outWB = new File("order.xlsx");
				        OutputStream out = new FileOutputStream(outWB);
				        workbook.write(out);
				        out.flush();
				        out.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return order;
	}

}