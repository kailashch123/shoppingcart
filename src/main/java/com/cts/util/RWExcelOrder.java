/**
 * This class is used to get the order information.
 * 
 * @author 764432
 *
 */
package com.cts.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cts.model.Order;

@Component
public class RWExcelOrder {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * This method is used for reading the excel value.
	 * 
	 * @return
	 */
	public List<Order> readExcel(String inputFilePath) {
		ArrayList<Order> orderList = null;
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(inputFilePath)));
			Sheet sheet = workbook.getSheetAt(0);
			orderList = new ArrayList<>();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				Order order = new Order();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						String orderId = ce.getStringCellValue();
						order.setOrderId(orderId);
					} else if (j == 1) {
						order.setProdId(ce.getStringCellValue());
					} else if (j == 2) {
						order.setUserID(ce.getStringCellValue());
					} else if (j == 3) {
						order.setOrderDate(ce.getStringCellValue());
					}
				}
				orderList.add(order);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * This method is used for write the order item into excel.
	 * 
	 * @param order
	 * @return
	 */
	public Order writeExcel(Order order, String filePath) {
		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet sheet = workbook.getSheetAt(0);
			int rownum = sheet.getLastRowNum();
			int cellnum = 0;
			Row row = sheet.createRow(++rownum);
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(order.getOrderId());

			Cell cell2 = row.createCell(cellnum++);
			cell2.setCellValue(order.getUserID());

			Cell cell3 = row.createCell(cellnum++);
			cell3.setCellValue(order.getProdId());

			Cell cell4 = row.createCell(cellnum++);
			cell4.setCellValue(order.getOrderDate());

			workbook.write(Files.newOutputStream(Paths.get(filePath)));
			workbook.close();
			return order;
		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used for canceling the order.
	 * 
	 * @param orderId
	 * @return
	 */
	public String cancelOrder(String orderId, String filePath) {
		int removeRowIndex = 0;
		String cancelledOrderId = null;
		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					int columnIndex = currentCell.getColumnIndex();
					int rowIndex = currentCell.getRowIndex();
					if (rowIndex >= 0) {
						if (columnIndex == 0 && currentCell.getStringCellValue().equalsIgnoreCase(orderId)) {
							removeRowIndex = rowIndex;
							cancelledOrderId = orderId;
						}
					}
				}
			}
			removeOrder(sheet, removeRowIndex);
			workbook.write(Files.newOutputStream(Paths.get(filePath)));
			workbook.close();
		} catch (IOException e) {
			LOGGER.log(Level.INFO, e.getMessage());
		}
		return cancelledOrderId;
	}

	/**
	 * This method is used for removing an order.
	 * 
	 * @param sheet
	 * @param rowIndex
	 */
	private void removeOrder(Sheet sheet, int rowIndex) {
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
	 * This method is used for getting an order.
	 * 
	 * @param orderId
	 * @return
	 */
	public Order getOrderById(String orderId, String filePath) {
		List<Order> orders = readExcel(filePath);
		Order order = null;
		for (Order o : orders) {
			if (o.getOrderId().equalsIgnoreCase(orderId)) {
				order = o;
			}
		}
		return order;
	}

	/**
	 * This method is used for placing an order.
	 * 
	 * @param placeOrder
	 * @return
	 */
	public String writeOrderExcel(Order placeOrder, String filePath) {
		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet sheet = workbook.getSheetAt(0);
			int rownum = sheet.getLastRowNum();
			int cellnum = 0;
			Row row = sheet.createRow(rownum++);
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(placeOrder.getOrderId());
			Cell cell2 = row.createCell(cellnum++);
			cell2.setCellValue(placeOrder.getProdId());
			Cell cell3 = row.createCell(cellnum++);
			cell3.setCellValue(placeOrder.getUserID());
			Cell cell4 = row.createCell(cellnum++);
			cell4.setCellValue(placeOrder.getOrderDate());
			workbook.write(Files.newOutputStream((Paths.get(filePath))));
			workbook.close();
			return "User Order Placed Successfully!!!The user with ID => " + placeOrder.getUserID()
					+ " has placed the Order with ID =>" + placeOrder.getOrderId() + " having the prodcut with ID => "
					+ placeOrder.getProdId();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Internal Server Error");
			return "NoSuchFileException";
		}
	}
}
