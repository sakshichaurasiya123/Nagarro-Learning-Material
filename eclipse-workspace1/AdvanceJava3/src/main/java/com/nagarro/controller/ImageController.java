package com.nagarro.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.hibernate.Session;
import com.nagarro.dao.ImageDao;
import com.nagarro.hibernateutil.HibernateUtil;
import com.nagarro.model.ImageManagement;
import com.nagarro.model.User;


/**
 * Servlet implementation class ImageController
 */
@MultipartConfig
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("imageId") != null) {
			deleteImage(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("addImage") != null) {

			addImage(request, response);
		} else if (request.getParameter("update") != null) {

			int imageId = Integer.parseInt(request.getParameter("imageId"));
			ImageManagement imageObject = new ImageManagement();
			String imageName = request.getParameter("imageName");
			Part part = request.getPart("image");
			long checkSize = part.getSize();
			if (checkSize != 0) {
				if (checkSize <= 1000000) {
					if (totalByte(checkSize)) {
						byte[] image;
						InputStream fileInputStream = part.getInputStream();
						image = new byte[fileInputStream.available()];
						fileInputStream.read(image);
						imageObject.setId(imageId);
						imageObject.setName(imageName);
						imageObject.setPreview(image);
						imageObject.setSize(checkSize);
						ImageDao.updateImage(imageObject, true);

						response.sendRedirect("image.jsp");
					} else {
						response.sendRedirect("image.jsp?totalSize=1");
					}
				} else {
					response.sendRedirect("image.jsp?size=1");
				}
			} else {
				imageObject.setId(imageId);
				imageObject.setName(imageName);
				ImageDao.updateImage(imageObject, false);
				System.out.println("image not update");
				response.sendRedirect("image.jsp");
			}

		}
	}

	private static void addImage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		User user = new User();
		ImageManagement imageObject = new ImageManagement();
		String name = request.getParameter("name");
		Part part = request.getPart("image");
		long size;
		size = part.getSize();
		if (size <= 1000000) {
			if (totalByte(size)) {

				byte[] image;
				HttpSession session = request.getSession();
				int id = (Integer) session.getAttribute("id");
				InputStream fileInputStream = part.getInputStream();
				image = new byte[fileInputStream.available()];
				fileInputStream.read(image);
				user.setId(id);
				imageObject.setName(name);
				imageObject.setPreview(image);
				imageObject.setSize(size);
				System.out.println("size");
				ImageDao.saveImage(imageObject, user);
				response.sendRedirect("image.jsp");
			} else {
				response.sendRedirect("image.jsp?totalSize=1");
			}
		} else {
			response.sendRedirect("image.jsp?size=1");
		}
	}

	private static boolean totalByte(long size) {
		long checkSize, totalSize, result;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			checkSize = (Long) session.createQuery("SELECT sum(size) FROM ImageManagement").uniqueResult();
			totalSize = checkSize;
		} catch (NullPointerException e) {
			totalSize = 0L;
		}
		result = totalSize + size;
		if ((result) > 10000000L)
			return false;
		else
			return true;
	}

	private static void deleteImage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		User user = new User();
		ImageManagement image = new ImageManagement();
		int imageId = Integer.parseInt(request.getParameter("imageId"));
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		user.setId(id);
		image.setId(imageId);
		ImageDao.deleteImage(user, image);
		response.sendRedirect("image.jsp?delete=suceess");
	}

}
