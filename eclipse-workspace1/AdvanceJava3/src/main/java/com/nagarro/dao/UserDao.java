package com.nagarro.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.hibernateutil.HibernateUtil;
import com.nagarro.model.User;

/*
 * This class will validate the entered username and password from the database
 * 
 */

public class UserDao {

	public boolean validate(String userName, String password) {

        Transaction transaction = null;
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            // commit transaction
            transaction.commit();
        
        return false;
    }
	

}
