package com.edu.mapper;

import java.util.List;



import com.edu.bean.ProductCustomer;
import com.edu.bean.ProductQuryVo;

public interface ProductCustomerMapper {
	//名字模糊查询
		

		public List<ProductCustomer> getByCondition(ProductQuryVo productQuryVo);
     //批量删除
		public void bathDelete(String[] ids);
		//批量修改跳页面
		public List<ProductCustomer> getByIds(String[] ids);
		//修改操作
		public void batchEdit(List<ProductCustomer> productListCustomer);
}
