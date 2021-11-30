
package com.nagarro.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.nagarro.model.FlightModel;

@Repository
public class FlightDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional
	public void createFlight(FlightModel flight) {

		this.hibernateTemplate.saveOrUpdate(flight);
	}

	public List<FlightModel> getFlightProducts() {
		List<FlightModel> flights = this.hibernateTemplate.loadAll(FlightModel.class);
		return flights;
	}

	@Transactional
	public void deleteFlightProduct() {
		this.hibernateTemplate.getSessionFactory().openSession().createQuery("Delete from flight");

	}

	public FlightModel getFlightProduct(int pid) {
		return this.hibernateTemplate.get(FlightModel.class, pid);
	}

}
