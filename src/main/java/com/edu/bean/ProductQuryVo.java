package com.edu.bean;

import java.util.List;

public class ProductQuryVo {
	
	private ProductCustomer productCustomer;
	private int  startPage  =1;
	private int  pageSize  =4;
	
	private List<ProductCustomer> productListCustomer;
	
	

	public List<ProductCustomer> getProductListCustomer() {
		return productListCustomer;
	}

	public void setProductListCustomer(List<ProductCustomer> productListCustomer) {
		this.productListCustomer = productListCustomer;
	}

	private String[]  ids ;
	

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public ProductCustomer getProductCustomer() {
		return productCustomer;
	}

	public void setProductCustomer(ProductCustomer productCustomer) {
		this.productCustomer = productCustomer;
	}
	

}
