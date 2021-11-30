package com.nagarro.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Product;
import com.nagarro.entity.Comments;
import com.nagarro.model.Model;
import com.nagarro.service.ProductService;
import com.nagarro.service.RegistrationService;
import com.nagarro.service.CommentService;;


@RestController
@CrossOrigin
public class CommentController {

@Autowired
private CommentService  commentService;
@Autowired
private ProductService productService;	
@Autowired
private RegistrationService registrationService;

/*
 * This API is used  to get allComment with respect productId
 * @Param productId
 * @Return List<Comments>
 */
 @GetMapping("/getAllComment/{id}")
 public List<Comments> getAllComment(@PathVariable(value="id") String id) throws Exception{
	List<Comments> results;
	try {
        Product productObj=productService.getProduct(id);
         results=commentService.getAllComment(productObj);
	}
	catch(Exception e) {
		throw new Exception(e.getMessage());
	}
    return results;
  }

 /*
  * This API is used to store the comments with respect prodductId
  * @Param peoductId
  * @Return nothing
  */
 @PutMapping("/insertComment/{id}")
 public void insertReview(@RequestBody Comments comment,@PathVariable(value="id") String id) throws Exception
 {
	Product product;
	List<Comments> comment1;
	try {
	   product=productService.getProduct(id);
	   comment1 =product.getComments();
	   comment1.add(comment);
	   comment.setProduct(product);
	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	   Date date = new Date();
	   comment.setDate(dateFormat.format(date));
	   productService.updateProductReview(product);
	}
	catch(Exception e) {
		throw new Exception(e.getMessage());
	}
 }

/*
 * This API is used to get stats details
 * @Param nothing
 * @Return Model
 */
 @GetMapping("/getstats")
 public Model getStats() throws Exception{
	Model model = new Model();
	try {
	model.countUsers=registrationService.countAllRegistrated();
	model.countComments=commentService.countAllComment();
	model.countPosts=productService.countAllProduct();
	}
	catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	return model;	
 }
}
