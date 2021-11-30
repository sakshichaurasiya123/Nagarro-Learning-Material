package com.nagarro.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.dao.FlightDao;
import com.nagarro.dao.UserDao;
import com.nagarro.model.FlightModel;
import com.nagarro.model.User;
import com.nagarro.operation.FlightOperation;
import com.nagarro.readfiles.FileReadUsingThread;
import com.nagarro.service.FlightService;

import flightsearch.usethread.UseThread;

@Controller
public class FlightController {
	@Autowired
	private UserDao userDao;

	@Autowired
	private FlightDao flightDao;

	@Autowired
	private FlightService flightService;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@RequestMapping("/home")
	public String home(User user) {
		return "home";
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String searchForm(@ModelAttribute User user, @ModelAttribute FlightModel flight,
			@RequestParam("userName") String userName, @RequestParam("password") String password) {
		List<User> users = userDao.getProducts();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserName().equals(userName) && users.get(i).getPassword().equals(password)) {

				(new UseThread()).startThread(flightService);

				return "search";
			}
		}
		return "error";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/processForm", method = RequestMethod.POST)
	public ModelAndView processForm(HttpServletRequest request, Model m) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String depLoc = request.getParameter("depLoc");
		String arrLoc = request.getParameter("arrLoc");

		Date validTill = null;
		try {
			validTill = format.parse(request.getParameter("validTill"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String choice = request.getParameter("sort");
		String CLASS = request.getParameter("CLASS");
		System.out.println(validTill);

		List<FlightModel> lists = null;

		if (choice.equals("fareDuration")) {

			

			DetachedCriteria criteria = DetachedCriteria.forClass(FlightModel.class);
			criteria.add(Restrictions.eq("depLoc", depLoc));
			criteria.add(Restrictions.eq("arrLoc", arrLoc));
			criteria.add(Restrictions.ge("validTill", validTill));
			criteria.add(Restrictions.eq("CLASS", CLASS));
			criteria.addOrder(Order.asc("fare"));
			criteria.addOrder(Order.asc("flightDur"));

			System.out.println(criteria + "hiiii");

			lists = (List<FlightModel>) hibernateTemplate.findByCriteria(criteria);
		} else {

			lists = (List<FlightModel>) hibernateTemplate
					.findByCriteria(DetachedCriteria.forClass(FlightModel.class).add(Restrictions.eq("depLoc", depLoc))
							.add(Restrictions.eq("arrLoc", arrLoc)).add(Restrictions.ge("validTill", validTill))
							.add(Restrictions.eq("CLASS", CLASS)).addOrder(Order.asc("fare")));
		}
	
		if (CLASS.equals("EB")) {
			double fare;
			for (FlightModel f : lists) {
				fare = f.getFare() + 0.4 * f.getFare();
				f.setFare(fare);
			}
		}

		if (lists.isEmpty()) {

			return new ModelAndView("search.jsp?msg=No Data Found");
		}
		m.addAttribute("findFlights", lists);
		return new ModelAndView("search");
	}

}
