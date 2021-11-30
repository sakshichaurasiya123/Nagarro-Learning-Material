package com.nagarro.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Product;
import com.nagarro.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
@Autowired
private ProductService service;	

/*
 * This API is used to accept the request from client side and store product 
 * @Param Product
 * @Return nothing
 */
 @PostMapping("/insertProduct")
 public void insertProduct(@RequestBody Product product) throws Exception
 {
	String subject=product.getSubject();
	Product productObj=service.getProduct(subject);
    
	if(productObj!=null)
	{
	throw new Exception("This productCode is already present please check on product page");
    
	}
	else
	{  
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		product.setDate(dateFormat.format(date));
		service.saveProduct(product);	
	}
	
 }

 /*
  * This API is used to get the product with help of productId
  * @Param productId
  * @Return Product
  */
 @GetMapping("/getProductById/{id}")
 public Product getProductById(@PathVariable("id") String subject) throws Exception
 {
	try {
	return this.service.getProduct(subject);
	}
	catch(Exception e)
	{
	throw new Exception("FETCHING_FAILED");	
	}
 }

}
