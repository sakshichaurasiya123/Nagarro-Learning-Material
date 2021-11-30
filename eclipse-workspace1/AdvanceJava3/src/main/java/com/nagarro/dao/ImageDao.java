package com.nagarro.dao;

import java.util.ArrayList;
import java.util.List;

 

import org.hibernate.Query;
import org.hibernate.Session;

import com.nagarro.hibernateutil.HibernateUtil;
import com.nagarro.model.ImageManagement;
import com.nagarro.model.User;

 
/*
 * @ author : saumyaawasthi
 * 
 * This class will perform all the database related operations on the image
 * 
 */
public class ImageDao {
    
    public static void saveImage(ImageManagement image, User user) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
        user = (User) session.get(User.class, user.getId());
        image.setUsername(user);
        System.out.println("save");
        List<ImageManagement> imageList = new ArrayList<ImageManagement>();
        imageList.add(image);
        user.setPreview(imageList);        
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
    
    @SuppressWarnings("unchecked")
    public static List<ImageManagement> showImages(User user) {
        List<ImageManagement> imageList = new ArrayList<ImageManagement>();
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
        Query query = session.createQuery("FROM ImageManagement WHERE username_id = :id")
                .setParameter("id", user.getId());
        imageList = query.list();
        return imageList;
    }
    
    public static void updateImage(ImageManagement image, boolean check) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
        System.out.println("update Method");
        if (check) {
            System.out.println(check);
            Query query = session.createQuery("UPDATE ImageManagement SET name = :imageName "
                    + ", preview = :image , size = :imageSize"
                    + " WHERE id = :imageId");
            query.setParameter("imageName", image.getName());
            query.setParameter("imageId", image.getId());
            query.setParameter("image", image.getPreview());
            query.setParameter("imageSize", image.getSize());
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            
        } else {
            Query query = session.createQuery("UPDATE ImageManagement SET name = :imageName"
                    + " WHERE id = :imageId");
            query.setParameter("imageName", image.getName());
            query.setParameter("imageId", image.getId());
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
    
    public static void deleteImage(User user, ImageManagement image) {
    
    	Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
        Query query = session.createQuery("DELETE FROM ImageManagement WHERE username_id= :userName AND id = :imageId");
        query.setParameter("userName", user.getId());
        query.setParameter("imageId", image.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        
    }

 

}
