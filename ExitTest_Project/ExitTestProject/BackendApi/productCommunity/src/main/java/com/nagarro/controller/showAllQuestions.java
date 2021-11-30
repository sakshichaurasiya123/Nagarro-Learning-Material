package com.nagarro.controller;
import com.nagarro.entity.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.service.SearchProductService;

@RestController
@CrossOrigin
public class showAllQuestions {
	
	@Autowired
	SearchProductService service;
	@GetMapping("/getAllQuestions/{userName}")
	public List<Product> getAllQuestions(@PathVariable("userName") String userName){
		return service.getAllQuestions(userName);	
	}
}
