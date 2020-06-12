package com.edu.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


import com.edu.bean.Product;
import com.edu.bean.ProductCustomer;
import com.edu.bean.ProductQuryVo;
import com.edu.group.VidataGroup1;
import com.edu.group.VidataGroup2;
import com.edu.servcie.ProductService;
import com.edu.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
//@RequestMapping("/product") //对请求进行分类 炸化请求
public class ProductController {
	
//	@Autowired
//	  private  ProductService productService;
//	  @RequestMapping("/list")
//	  public ModelAndView list(){
//		  ModelAndView model= new  ModelAndView();
//		  List<Product> productlist = productService.getAll();
//		  model.addObject("productlist", productlist);
//		  model.setViewName("product/list");
//		  return model;
//	  }
	  
//
//		@Autowired
//		  private  ProductService productService;
//		  @RequestMapping(value="/list",method = {RequestMethod.GET,RequestMethod.POST})
//		  public String list(ProductQuryVo productQuryVo ,Model model){
//			  
//			
//			 
//			  List<ProductCustomer> productlist = productService.getByCondition(productQuryVo);
//			  model.addAttribute("productlist",productlist);
//			  
//			  return "product/list";
//		  }
	
	@Autowired
	  private  ProductService productService;
	  @RequestMapping(value="/list",method = {RequestMethod.GET,RequestMethod.POST})
	  public String list(ProductQuryVo productQuryVo ,Model model){
		//获取第1页，10条内容，默认查询总数count
		  PageHelper.startPage(productQuryVo.getStartPage(), productQuryVo.getPageSize());
		  PageInfo pageinfo = productService.getPageCondition(productQuryVo);
		 
		  model.addAttribute("pageinfo",pageinfo);
		  
		  return "product/list";
	  }
	  
		

		
		  @RequestMapping(value="/list2",method = {RequestMethod.GET,RequestMethod.POST})
		  public Void list2(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			  
			  List<Product> productlist = productService.getAll();
			return null;
			 
			 
		  }
		  
		  

	  /**
		 * 跳转新增页面
		 * @return
		 */
		@RequestMapping("/addUI")
		public String addUI() {
			
			return "product/editUIAll" ;
			
		}
		
		/**
		 * 进行删除
		 * @return
		 */
		@RequestMapping(value = "/delete",method = RequestMethod.GET)
		public String delete(HttpServletRequest request) {
			
			String id =request.getParameter("id");
			
			
			productService.delete(id);
			
			return "redirect:list.action" ;
			
		}
		
		/**
		 * 进行删除
		 * @return
		 */
		@RequestMapping(value = "/bathDelete",method = RequestMethod.POST)
		public String bathDelete(ProductQuryVo productQuryVo) {
			System.out.println("33333333333");
			productService.bathDelete(productQuryVo.getIds());
			return "redirect:list.action?startPage="+productQuryVo.getStartPage()
			+ "&productCustomer.title="+productQuryVo.getProductCustomer().getTitle();
			
		}
		
		/**
		 * 跳转页面批量修改
		 * @return
		 */
		@RequestMapping(value = "/btnBathEditUI",method = RequestMethod.POST)
		public String btnBathEditUI(ProductQuryVo productQuryVo ,Model model) {
			System.out.println("44444444444444");
			List<ProductCustomer> productlist = productService.getByIds(productQuryVo.getIds());
			model.addAttribute("productlist", productlist);
			return "product/btnBathEditUI" ;
			
		}
		

		/**
		 *批量修改
		 */
		
		@RequestMapping(value = "/batchEdit",method = RequestMethod.POST)
		public String batchEdit(ProductQuryVo productQuryVo) {
			System.out.println("666");
			productService.batchEdit(productQuryVo.getProductListCustomer());
			return "redirect:list.action?startPage="+productQuryVo.getStartPage()
			+ "&productCustomer.title="+productQuryVo.getProductCustomer().getTitle();
		}
		
		/**
		 * 进行新增操作
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		@RequestMapping(value = "/add",method = RequestMethod.POST)
		public String add(HttpServletRequest request,ProductCustomer product) throws UnsupportedEncodingException {
			request.setCharacterEncoding("utf-8");
		/*
		 * String id =request.getParameter("id"); String title =
		 * request.getParameter("title") ; String descs = request.getParameter("descs")
		 * ;
		 */
			// 封装成一个对象
			productService.insert(product);
			// 调用业务层进行新增
			// 保存成功之后，重定向显示页面
			return "redirect:list.action" ;
			
		}
		

		/**
		 * 跳转新增页面
		 * @return
		 */
		
		@RequestMapping("/editUI")
		public String editUI(HttpServletRequest request) {
			
			String id =request.getParameter("id");
			// 根据id去查询
			Product product = productService.getById(id);
			
			request.setAttribute("product", product);
			// 修改要回显示数据
			return "product/editUIAll" ;
		}
		
		/**
		 * 进行修改操作
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/edit",method = RequestMethod.POST)
		public String edit(@ModelAttribute("product") @Validated(value=VidataGroup2.class) ProductCustomer product ,BindingResult result,
				MultipartFile imgFile ,HttpServletRequest request) {
			if (result.hasErrors()) {
				List<ObjectError> allErrors = result.getAllErrors();
				allErrors.forEach((t) ->{
					System.out.println(t.getDefaultMessage());
				});
				request.setAttribute("allErrors", allErrors);
				return "product/editUIAll" ;
			}
			if (null!=imgFile) {
				//String upload= request.getSession().getServletContext().getRealPath("/upload");
				String upload= "E:\\upload";
				String oldName = imgFile.getOriginalFilename();
				if (null != oldName && oldName.length() >0) {
					String newName = UUIDUtil.getCode() + oldName.substring(oldName.lastIndexOf("."));
					
					SimpleDateFormat formater = new SimpleDateFormat("/yyyy/MM/dd/");
					String dateStr = formater.format(new Date());
					File baseFile = new File(upload+dateStr);
					if (!baseFile.exists()) {
						baseFile.mkdirs();
					}
					try {
						imgFile.transferTo(new File(baseFile, newName));
						product.setImg("/pic"+dateStr+newName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		
		productService.updat(product);
			
			// 调用service进行修改操作
			// 重定向到list
			return "redirect:list.action" ;
		}
		
//		@RequestMapping(value="/list2",method = {RequestMethod.POST,RequestMethod.GET})
//		public String list2(HttpServletRequest request) {
//			List<Product> productList = productService.getAll();
//			request.setAttribute("productList", productList);
//			// 返回视图的名字 = 前缀  + 视图的名字 + 后缀
//			//  这叫做转发 "foward:product/list" ;
//			// 这叫做重定向"redirect:路径"
//			return "foward:product/list" ;
//		}
	//	
	//	
//		@RequestMapping(value="/list3",method = {RequestMethod.POST,RequestMethod.GET})
//		public List<Product> list3(HttpServletRequest request) {
//			List<Product> productList = productService.getAll();
//			
//			return productList ;
//		}
	//	
//		@RequestMapping(value="/list4",method = {RequestMethod.POST,RequestMethod.GET})
//		public void list4(HttpServletRequest request,HttpServletResponse response) 
//				throws Exception {
//			List<Product> productList = productService.getAll();
//			PrintWriter out = response.getWriter() ;
//			
//			out.println("");
//			out.flush();
//			out.close();
//			
//		}
	  
	  

}
