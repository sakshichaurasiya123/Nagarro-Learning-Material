package com.nagarro.controller;

import java.io.IOException;
import com.nagarro.dao.UserDao;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.hibernateutil.HibernateUtil;
import com.nagarro.model.User;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	UserDao login = new UserDao();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("UserName");
		String password = request.getParameter("Password");
Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		User q = (User) session.createQuery("from User u where u.username =:v1 " + " AND u.password=:v2")
				.setParameter("v1", name).setParameter("v2", password).uniqueResult();
		if (q==null) {
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		} else {
			HttpSession httpsession =request.getSession();
			httpsession.setAttribute("id",q.getId());
			response.sendRedirect(request.getContextPath() + "/image.jsp");
		}

		session.getTransaction().commit();
		session.close();
		
		

		
		
	}
}
