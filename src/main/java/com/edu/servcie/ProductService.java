package com.edu.servcie;

import java.util.List;

import com.edu.bean.Product;
import com.edu.bean.ProductCustomer;
import com.edu.bean.ProductQuryVo;
import com.github.pagehelper.PageInfo;

public interface ProductService {

	List<Product> getAll();

	

	Product getById(String id);



	/* void insert(String id, String title, String descs); */



	void delete(String id);



//	void updat(String id, String title, String descs);



	void insert(Product product);



	void updat(ProductCustomer product);



	


    @Deprecated
	List<ProductCustomer> getByCondition(ProductQuryVo productQuryVo);



	PageInfo getPageCondition(ProductQuryVo productQuryVo);



	



	void bathDelete(String[] ids);



	List<ProductCustomer> getByIds(String[] ids);



	void batchEdit(List<ProductCustomer> productListCustomer);









	

	

}
