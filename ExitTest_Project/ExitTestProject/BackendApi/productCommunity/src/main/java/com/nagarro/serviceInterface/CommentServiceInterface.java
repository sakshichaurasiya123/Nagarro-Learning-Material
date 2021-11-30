package com.nagarro.serviceInterface;

import java.util.List;

import com.nagarro.entity.Product;
import com.nagarro.entity.Comments;

public interface CommentServiceInterface {

public List<Comments> getAllComment(Product product);

public Long countAllComment();

public Double getAverage(Product product);
}
