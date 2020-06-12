package com.edu.servcie;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.Product;
import com.edu.bean.ProductCustomer;
import com.edu.bean.ProductExample;
import com.edu.bean.ProductQuryVo;
import com.edu.mapper.ProductCustomerMapper;
import com.edu.mapper.ProductMapper;
import com.edu.util.UUIDUtil;
import com.github.pagehelper.PageInfo;

@Service
public class ProductServiceimple implements ProductService {
	@Autowired  //@AutoWired注解这个注解的作用，先根据类型进行装配，如果类型相同则根据名字来进行装配
	public ProductMapper productMapper;
	@Autowired  //@AutoWired注解这个注解的作用，先根据类型进行装配，如果类型相同则根据名字来进行装配
	public ProductCustomerMapper productCustomerMapper;
	@Override
	public List<Product> getAll() {
		
		ProductExample example = new ProductExample();
		return productMapper.selectByExample(example);
	}
	@Override
	public Product getById(String id) {
		
		return productMapper.selectByPrimaryKey(id);
	}
	//添加
//	@Override
//	public void insert(String id, String title, String descs) {
//		
//		Product product = new Product();
//		product.setId(id);
//		product.setTitle(title);
//		product.setDescs(descs);
//		productMapper.insert(product);
//		
//		
//	}
	@Override
	public void delete(String id) {
		
		productMapper.deleteByPrimaryKey(id);
		
	}
//	@Override
//	public void updat(String id, String title, String descs) {
//		
//		Product product = new Product();
//		product.setId(id);
//		product.setTitle(title);
//		product.setDescs(descs);
//		productMapper.updateByPrimaryKey(product);
//		
//	}
	@Override
	public void insert(Product product) {
		/*
		 * Product product2 = new Product(); product2.setId(id);
		 * product2.setTitle(title); product.setDescs(descs);
		 */
		product.setId(UUIDUtil.getCode());
		productMapper.insert(product);
		
		
	}
	@Override
	public void updat(ProductCustomer product) {
		//productMapper.updateByPrimaryKeySelective(product);
		System.out.println("0000");
		productMapper.updateByPrimaryKeySelective(product);
		
	}
	@Override
	public List<ProductCustomer> getByCondition(ProductQuryVo productQuryVo) {
		// TODO Auto-generated method stub
		return productCustomerMapper.getByCondition(productQuryVo);
	}
	
	@Override
	public PageInfo getPageCondition(ProductQuryVo productQuryVo) {
		PageInfo page =new PageInfo(getByCondition(productQuryVo));
		return page;
	}
	@Override
	public void bathDelete(String[] ids) {
		productCustomerMapper.bathDelete(ids);
		
	}
	@Override
	public List<ProductCustomer> getByIds(String[] ids) {
		// TODO Auto-generated method stub
		return productCustomerMapper.getByIds(ids);
	}
	@Override
	public void batchEdit(List<ProductCustomer> productListCustomer) {
		System.out.println("001");
		productCustomerMapper.batchEdit(productListCustomer);
		
	}
	
	
	
	

	
}
