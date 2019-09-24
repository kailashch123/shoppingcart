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
	public String addItem(final Product pro) {
		return productExcelFile.addItemInExcel("./src/main/resources/excel/product.xlsx", pro);
	}

	/**
	 * Removed the product by using the product Id
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String removeItem(final String prodId) {
		return productExcelFile.removeItemFromExcel("./src/main/resources/excel/product.xlsx", prodId);
	}

	/**
	 * retrieve product list.
	 * 
	 * @param
	 * @return list of product
	 */
	@Override
	public List<Product> getAllProducts() {
		return rWExcelProduct.getAllProducts();
	}

	/**
	 * Retrieving product by using id
	 * 
	 * @param prodId
	 * @return Product
	 */
	@Override
	public Product getProductById(final String prodId) {
		return rWExcelProduct.getProductById(prodId);
	}

}
