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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cts.model.Product;

@Component
public class ProductExcelFile {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private int rownum;
	private int cellnum;
	File file;

	/**
	 * This method is used for adding the product details into excel.
	 * 
	 * @param fileName
	 * @param pro
	 * @return
	 */
	@SuppressWarnings("resource")
	public String addItemInExcel(String fileName, Product pro) {
		String response = null;
		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
			Sheet sheet = workbook.getSheetAt(0);
			rownum = sheet.getLastRowNum();

			cellnum = 0;

			Row row = sheet.createRow(++rownum);

			Cell cell = row.createCell(cellnum++);

			cell.setCellValue(pro.getProdId());

			Cell cell2 = row.createCell(cellnum++);
			cell2.setCellValue(pro.getProdName());

			Cell cell3 = row.createCell(cellnum++);
			cell3.setCellValue(pro.getPrice());

			FileOutputStream out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			out.close();
			response = "Product Added Successfully,Product Id:  " + pro.getProdId();

		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.getMessage());
			response = "NoSuchFileException";

		}
		return response;
	}

	/**
	 * This method is used for deleting a product item from excel.
	 * @param inputFilePath
	 * @param id
	 * @return
	 */
	@SuppressWarnings("resource")
	public String removeItemFromExcel(String inputFilePath, String prodId) {
		String removedProdId = null;
		int removedRowIndex = 0;
		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(inputFilePath)));
			Sheet dataSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = dataSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();

					int columnIndex = currentCell.getColumnIndex();
					int rowIndex = currentCell.getRowIndex();
					if (rowIndex >= 0) {
						if (columnIndex == 0 && currentCell.getStringCellValue().equals(prodId)) {
							removedRowIndex = rowIndex;
							removedProdId = prodId;
						}
					}
				}
			}
			removeRow(dataSheet, removedRowIndex);
			File file = new File(inputFilePath);
			workbook.write(Files.newOutputStream((Paths.get(inputFilePath))));
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
			removedProdId = "NoSuchFileException";
		}
		return removedProdId;
	}

	public String removeRow(Sheet sheet, int rowIndex) {

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
		return "Deleted successfully";
	}
}
