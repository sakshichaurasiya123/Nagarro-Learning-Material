
package com.nagarro.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.model.FlightModel;
import com.nagarro.model.User;

@Component
public class UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public void create(User user) {
		this.hibernateTemplate.save(user);
	}

	public List<User> getProducts() {
		List<User> users = this.hibernateTemplate.loadAll(User.class);
		return users;
	}

	// delete the single product
	@Transactional
	public void deleteProduct(int pid) {
		User p = this.hibernateTemplate.load(User.class, pid);
		this.hibernateTemplate.delete(p);
	}

	// get the single product
	public User getProduct(int pid) {
		return this.hibernateTemplate.get(User.class, pid);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
