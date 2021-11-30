package com.Hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	Alian telusko=new Alian();
    	telusko.setAid(101);
    	telusko.setAname("Sakshi");
    	telusko.setAcolor("Pink");
    	
    	Configuration con=new Configuration().configure().addAnnotatedClass(Alian.class);
    	SessionFactory sf=con.buildSessionFactory();
    	Session session=sf.openSession();
    	Transaction tr=session.beginTransaction();
    	session.save(telusko);
    	tr.commit();
    	 
    }
}
