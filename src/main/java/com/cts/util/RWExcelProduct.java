/**
 * This class is used to get the product information.
 * 
 * @author 764432
 *
 */
package com.cts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cts.model.Product;

@Component
public class RWExcelProduct {
	/**
	 * This method is used for adding an item into excel.
	 * 
	 * @param pro
	 * @return
	 */
	public String addItemInExcel(Product pro, String filePath) {
		File file = new File(filePath);
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int rownum = 0;
		int cellnum = 0;
		if (file.exists() == false) {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("product");
		} else {
			try {
				workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
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
		cell.setCellValue(pro.getProdId());
		Cell cell2 = row.createCell(cellnum++);
		cell2.setCellValue(pro.getProdName());
		Cell cell3 = row.createCell(cellnum++);
		cell3.setCellValue(pro.getPrice());
		try {
			workbook.write(Files.newOutputStream((Paths.get(filePath))));
			workbook.close();
			return "Item added Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal Server Error";
		}
	}

	/**
	 * This method is used for reeading excel.
	 * 
	 * @return
	 */
	public List<Product> readExcel(String filePath) {
		Workbook workbook = null;
		FileInputStream fileInputStream = null;
		ArrayList<Product> productList = null;
		try {
			workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet sheet = workbook.getSheetAt(0);
			productList = new ArrayList<>();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				Product product = new Product();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						String orderId = ce.getStringCellValue();
						product.setProdId(orderId);
					}
					if (j == 1) {
						product.setProdName(ce.getStringCellValue());
					}
					if (j == 2) {
						product.setPrice(ce.getStringCellValue());
					}
				}
				productList.add(product);
			}
			return productList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

	/**
	 * This method is used for removing an item from excel.
	 * 
	 * @param inputFilePath
	 * @param id
	 */
	public void removeItemFromExcel(String filePath, int id) {
		int removeRowIndex = 0;
		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						int columnIndex = currentCell.getColumnIndex();
						int rowIndex = currentCell.getRowIndex();
						if (rowIndex >= 0) {
							if (columnIndex == 0 && currentCell.getNumericCellValue() == id) {
								removeRowIndex = rowIndex;
							}
						}
					}
				}
			}
			removeRow(datatypeSheet, removeRowIndex);
			workbook.write(Files.newOutputStream((Paths.get(filePath))));
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for removing a row from excel.
	 * 
	 * @param sheet
	 * @param rowIndex
	 */
	public static void removeRow(Sheet sheet, int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
		if (rowIndex == lastRowNum) {
			Row removingRow = sheet.getRow(rowIndex);
			if (removingRow != null) {
				sheet.removeRow(removingRow);
			}
		}
	}

	/**
	 * This method is used for getting all the product.
	 * 
	 * @return
	 */
	public List<Product> getAllProducts(String filePath) {
		Workbook workbook = null;
		FileInputStream fileInputStream = null;
		ArrayList<Product> productList = null;
		try {
			workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet sheet = workbook.getSheetAt(0);
			productList = new ArrayList<>();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				Product product = new Product();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						product.setProdId(ce.getStringCellValue());
					}
					if (j == 1) {
						product.setProdName(ce.getStringCellValue());
					}
					if (j == 2) {
						product.setPrice(ce.getStringCellValue());
					}
				}
				productList.add(product);
			}
			return productList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

	public Product getProductById(String prodId, String filePath) {
		List<Product> products = getAllProducts(filePath);
		Product product = null;
		for (Product p : products) {
			if (p.getProdId().equalsIgnoreCase(prodId)) {
				product = p;
			}
		}
		return product;
	}
}
