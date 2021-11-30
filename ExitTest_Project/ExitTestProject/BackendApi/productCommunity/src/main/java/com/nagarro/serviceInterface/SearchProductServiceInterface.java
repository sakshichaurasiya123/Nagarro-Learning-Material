package com.nagarro.serviceInterface;

import java.util.List;

import com.nagarro.entity.Product;

public interface SearchProductServiceInterface {

public List<Product> getProductListBySearch(String search);	
}
