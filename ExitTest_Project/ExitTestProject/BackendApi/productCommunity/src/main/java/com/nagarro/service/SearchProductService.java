package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.entity.Product;
import com.nagarro.repository.SearchProductRepository;
import com.nagarro.serviceInterface.SearchProductServiceInterface;

@Service
public class SearchProductService implements SearchProductServiceInterface {

@Autowired
private SearchProductRepository repo;

//This method is used to get list of product based on search
@Override
public List<Product> getProductListBySearch(String search)
{
	return repo.findBySearch(search);
}

public List<Product>getAllQuestions(String userName){
	
	return repo.findByUserName(userName);
	
}


}
