/**
 * This class is used to give product details
 * 
 * @author 764432
 *
 */
package com.cts.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.model.Product;
import com.cts.repository.iface.IProductDetailsRepository;
import com.cts.util.ProductExcelFile;
import com.cts.util.RWExcelProduct;

@Repository("productDetailsRepository")

public class ProductDetailsRepoImpl implements IProductDetailsRepository {

	@Autowired
	private ProductExcelFile productExcelFile;

	@Autowired
	private RWExcelProduct rWExcelProduct;
	

	/**
	 * It will add a product details
	 * 
	 * @param pro
	 * @return
	 */
	@Override
	public String addItem(final Product prouct, String filePath) {
		return productExcelFile.addItemInExcel(filePath, prouct);
	}

	/**
	 * Removed the product by using the product Id
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String removeItem(final String prodId, String filePath) {
		return productExcelFile.removeItemFromExcel(filePath, prodId);
	}

	/**
	 * retrieve product list.
	 * 
	 * @param
	 * @return list of product
	 */
	@Override
	public List<Product> getAllProducts(String filePath) {
		return rWExcelProduct.getAllProducts(filePath);
	}

	/**
	 * Retrieving product by using id
	 * 
	 * @param prodId
	 * @return Product
	 */
	@Override
	public Product getProductById(final String prodId, String filePath) {
		return rWExcelProduct.getProductById(prodId, filePath);
	}

}
