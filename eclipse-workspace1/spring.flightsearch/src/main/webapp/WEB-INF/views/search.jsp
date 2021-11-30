<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@page import="com.nagarro.model.FlightModel"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<!-- Bootstrap core css -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- Bootstrap font-awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

</head>
<body>



	<div class="container">
		<div class="row d-flex justify-content-center mt-2">
			<div
				class="col-lg-12 d-flex justify-content-center shadow mb-3 p-3 rounded"
				style="background-color: blue; color: white;">
				<h3><b>Search Flights</b></h3>
			</div>
			<form action="processForm" method="post"
				class="col-lg-12 shadow mb-3 p-3 rounded">
				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="input">Departure Location</label> <input type="text"
							class="form-control" required name="depLoc">
					</div>
					<div class="form-group col-md-2">
						<label for="input">Arriving Location</label> <input type="text"
							class="form-control" required name="arrLoc">
					</div>
					<div class="form-group col-md-3">
						<label for="inputCity">Flight Date</label> <input type="date"
							class="form-control" required name="validTill">
					</div>
					<div class="form-group col-md-2">
						<label>Flight Class</label> <select class="form-control"
							name="CLASS" required>
							<option selected>E</option>
							<option>EB</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<label class="mb-1">Output Preference</label>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="sort" checked
								value="FARE"> <label class="form-check-label"
								for="flexRadioDefault1"> Fare </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="sort"
								value="fareDuration"> <label class="form-check-label"
								for="flexRadioDefault2"> Fare &amp; Duration </label>
						</div>
					</div>
				</div>
				<div class="form-row justify-content-center">
					<div class="form-group col-lg-2" >
						<input type="submit" class="btn btn-primary form-control" style="background-color: black;"
							name="search" value="Search">
					</div>
				</div>



			</form>
		</div>



		<div class="row shadow mb-3 p-3 rounded">
			<table class="table table-bordered">
				<thead class="thead"
					style="background-color: blue; color: white; font-size: 15px;">
					<tr>
						<th scope="col">S.No</th>
						<th scope="col">Flight Number</th>
						<th scope="col">Departure Location</th>
						<th scope="col">Arriving Location</th>
						<th scope="col">Valid Till</th>
						<th scope="col">Flight Time</th>
						<th scope="col">Flight Duration</th>
						<th scope="col">Fare</th>
						<th scope="col">Seat Availability</th>
						<th scope="col">Class</th>
					</tr>
				</thead>
				<tbody>
					<%!int count = 0;%>
			
              <% count = 0;%>
					<c:forEach items="${findFlights}" var="flight">
					
						<tr>
							<th scope="row"><%=++count%></th>
							<td>${flight.flightNo}</td>
							<td>${flight.depLoc }</td>
							<td>${flight.arrLoc}</td>
							<td>${fn:substring(flight.validTill,0,10)}</td>
							<td>${flight.flightTime}</td>
							<td>${flight.flightDur}</td>
							<td>${flight.fare}</td>
							<td>${flight.seatAvailability}</td>
							<td>${flight.CLASS}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
  <%
  if(request.getParameter("msg")!=null)
  {
	  %>
	  <div class="row d-flex justify-content-center fixed-top">
      <div class="col-lg-6 ">
          <div class="alert alert-primary alert-dismissible fade show d-flex justify-content-center" id="alert">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <strong>Sorry!! </strong>&nbsp;No Flights Availaible !!
          </div>
      </div>       
</div>  
<%
  }
  %>
	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<!-- Bootstrap Core javascript -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>