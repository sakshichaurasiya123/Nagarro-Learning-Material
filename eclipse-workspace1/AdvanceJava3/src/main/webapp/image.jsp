<%@page import="org.hibernate.Query"%>
<%@page import="com.nagarro.hibernateutil.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.nagarro.dao.ImageDao"%>
<%@page import="com.nagarro.model.ImageManagement"%>
<%@page import="java.util.List"%>
<%@page import="com.nagarro.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap core css -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
   <%
   request.getSession();
   Integer id;
   int check;
   try{
   check=(Integer)session.getAttribute("id");
   id=check;
   }
   catch(NullPointerException e)
   {
	   id=0;
   }
   if(id!=0 )
   {
	   
  %>
	<div class="container border border-secondary mx-auto mt-5">
		<div class="row border-bottom border-secondary">
  			<div class="col-lg d-flex justify-content-center">
  				<h5 class="p-1 text-dark">Image Management Utility</h5>
  			</div>  			
		</div>
		<form action="ImageController" method="post" class="row" enctype="multipart/form-data">
			<div class="col-lg-8">
			   	<p class="col-sm-12 col-lg-12">Please select an image file 
			   	to upload (Max Size 1 MB)</p>
			   	<div class="col-sm-6 col-md-8 col-lg-6">
			   		<input type="text" required name="name">
			   		<input type="file" id="selectedFile" required name="image" style="display: none;" />
					<input type="button" value="Browse" onclick="document.getElementById('selectedFile').click();" />
			   	</div>
			</div>
			   	<div class="col-md-12 col-lg-4 col-sm-12 mt-3 p-4">
  				<div class="d-flex justify-content-center">
  					<input class="mr-2" type="submit" name="addImage" value="Submit" />
  					<input type="reset" value="Cancel"/>
  				</div>
  				  				
  			</div>			
		</form> 			
		
		<div class="row">
			<div class="col-lg-12">
				<h5>Uploaded Images</h5>
			</div>
			<div class="col-lg-12 p-0">
				<div class="table-wrapper-scroll-y my-custom-scrollbar">
					<table class="table table-bordered" id="studentList">
						<thead class="text-dark">
							<tr>
								<th>S.No</th>
								<th>Name</th>
								<th>Size</th>
								<th>Preview</th>
								<th>Actions</th>
							</tr>
						</thead>
						<%!String name, printSize; double size; int imageId; int count=0; %>
                            <%
                                User user = new User(); 
                                user.setId(id);
                                List<ImageManagement> imageList = ImageDao.showImages(user);
                                Iterator<ImageManagement> it =imageList.iterator();
                                count=0;
                                while(it.hasNext()){
                                    ImageManagement imageObject = (ImageManagement) it.next();
                                    byte[] image =imageObject.getPreview();
                                    String imgDataBase64 = new String(Base64.getEncoder().encode(image));
                                    imageId = imageObject.getId();
                                    name = imageObject.getName();
                                    size = imageObject.getSize()/1000000.00;
                                    printSize = String.format("%.2f", size);
                            %>
                                <tr>
                                    <td><%= ++count %></td>
                                    <td><%=name %></td>
                                    <td><%=printSize %></td>
                                    <td><img alt="image" style="width: 180px; height: 100px" src="data:image/jpg;base64,<%= imgDataBase64 %>"></td>
                                    <td><a href="?updateId=<%=imageId%>"><img alt="updateImage" style="width: 20px; height: 20px" src="update.png"></a>
                                        &nbsp;
                                        <a href="ImageController?imageId=<%=imageId%>"><img alt="deleteImage" style="width: 20px; height: 20px" src="delete.png"></a>
                                    </td>                                   
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%
        if (request.getParameter("updateId") != null) {
        	
            int updateId = Integer.parseInt(request.getParameter("updateId"));           
            Session hsession = HibernateUtil.getSessionFactory().openSession();
    		hsession.beginTransaction();
            Query query = hsession.createQuery("FROM ImageManagement WHERE id = :imageId")
                    .setParameter("imageId", updateId);
            ImageManagement imageObject = (ImageManagement)query.uniqueResult();
           
    %>   
        <script type="text/javascript">
            window.onload = function () {
                $('#exampleModal').modal('show');
            }
        </script>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Update Form</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form action="ImageController" method="Post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Image Id</label>
                        <input type="text" readonly name="imageId" value="<%=updateId %>" class="form-control" id="recipient-name">
                      </div>
                  <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Name</label>
                    <input type="text" class="form-control" required name="imageName" id="recipient-name" value="<%=imageObject.getName()%>">
                  </div>
                  <div class="form-group">
                    <label for="message-text" class="col-form-label">Image</label>
                    <input type="file" class="form-control" name="image" id="message-text">
                  </div>
                  <div class="modal-footer">
                <button class="btn btn-secondary" data-dismiss="modal" onclick="document.location.href='userHome.jsp'">Close</button>
                <input type="submit" class="btn btn-primary" name="update" value="Update">
              </div>
                </form>
               
              </div>
             
            </div>
          </div>
        </div>
         <%
        }
        %>
         <%
        if (request.getParameter("delete") != null) {
           
    %>
    <div class="row d-flex justify-content-center fixed-top">
        <div class="col-lg-6 ">
            <div class="alert alert-success alert-dismissible fade show d-flex justify-content-center" id="alert">
                <button type="button" class="close" onclick="document.location.href='management.jsp'" data-dismiss="alert">&times;</button>
                <strong>Success </strong> Deleted Successfully !!.
            </div>
        </div>       
  </div>  
    <%
        }
    %>
        
        <%
        if (request.getParameter("totalSize") != null) {
           
    %>
    <div class="row d-flex justify-content-center fixed-top">
        <div class="col-lg-6 ">
            <div class="alert alert-danger alert-dismissible fade show d-flex justify-content-center" id="alert">
                <button type="button" class="close" onclick="document.location.href='management.jsp'" data-dismiss="alert">&times;</button>
                <strong>Error! </strong> You Don't Have Enough Space total size of images must be less than 10 mb.
            </div>
        </div>       
  </div>  
    <%
        }
    %>
   
    <%
        if (request.getParameter("size") != null) {
        	System.out.println("hello");
           
    %>
    <div class="row d-flex justify-content-center fixed-top">
        <div class="col-lg-6 ">
            <div class="alert alert-danger alert-dismissible fade show d-flex justify-content-center" id="alert">
                <button type="button" class="close" onclick="document.location.href='management.jsp'" data-dismiss="alert">&times;</button>
                <strong>Error! </strong> Image Size Must Be 1 MB Or Less.
            </div>
        </div>       
  </div>  
    <%
        }
    %>
   
  <%
  }
   else
   {
	   response.sendRedirect("error.jsp");
   }
  %>
   
   
	
</body>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>