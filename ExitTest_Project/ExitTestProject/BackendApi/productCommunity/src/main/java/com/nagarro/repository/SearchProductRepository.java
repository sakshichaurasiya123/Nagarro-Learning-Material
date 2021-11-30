package com.nagarro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.entity.Product;

@Repository
public interface SearchProductRepository extends JpaRepository<Product,Integer> {

	@Query("SELECT p from Product p Where INSTR(Concat(p.subject,p.question,p.messageBody),:search)>0 or INSTR(Concat(p.subject,p.messageBody,p.question),:search)>0 or INSTR(Concat(p.question,p.subject,p.messageBody),:search)>0 or INSTR(Concat(p.question,p.messageBody,p.subject),:search)>0 or INSTR(Concat(p.messageBody,p.subject,p.question),:search)>0 or INSTR(Concat(p.messageBody,p.question,p.subject),:search)>0 ")
    public List<Product> findBySearch(@Param("search")String search);

	//For showing all questions
	@Query("select p from Product p where p.userName=:userName")
	public List<Product> findByUserName(@Param("userName") String userName);
}
