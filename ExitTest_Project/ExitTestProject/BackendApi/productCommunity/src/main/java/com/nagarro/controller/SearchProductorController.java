package com.nagarro.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Product;
import com.nagarro.service.SearchProductService;

@RestController
@CrossOrigin
public class SearchProductorController {
@Autowired
private SearchProductService service;	

/*
 * This API is used to get the product list with help of productSearch
 * @Param productSearch
 * @Return List<Product>
 */
 @GetMapping("/getProductBySearch/{productSearch}")
 public List<Product> getProductListBySearch(@PathVariable("productSearch")String productSearch) throws Exception {
	productSearch=productSearch.replaceAll(" ","");
	List<Product> productList;
	try {
	productList=service.getProductListBySearch(productSearch);
	}
	catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	return productList;
 }

}
