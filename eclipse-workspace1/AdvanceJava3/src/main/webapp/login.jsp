<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 <style>
     p{color: red;}
 </style>
</head>
    <body>
	<div class="container mt-2 mx-auto">
		<div class="row border-primary"
			style="background-color: rgb(203, 237, 241);">
			<h3>Login</h3>
		</div>
		<div class="row border-primary"
			style="background-color: rgb(236, 251, 253);">
			<form action="UserController" method="post" class="mt-4 col-lg-12 mb-0" >
				<div class="form-group row">
					<label for="Name" class="col-lg-3 col-form-label">Username:</label>
					<p>*</p>
					<div class="col-lg-5">
						<input type="text" required class="form-control" name="UserName"
							autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label for="Password" class="col-sm-2 col-lg-3 col-form-label">Password:</label>
					<p>*</p>
					<div class="col-sm-6 col-md-8 col-lg-5">
						<input type="password" required class="form-control"
							name="Password" autocomplete="off">
					</div>
				</div>
				<div class=" row justify-content-center">
					<a href=""><u>Forgotten Your Password?</u></a>
				</div>
               <div class="row border-primary"
			style="background-color: rgb(203, 237, 241);">
			<div class="col-lg-12">
				<!-- < input type="submit"class="btn btn-outline-secondary float-right"/> -->
				<input type="submit" class="btn btn-outline-primary btn btn-light float-right"/>
			</div>
		</div> 
			</form>
		</div>
	 	
	</div>
</body>  

</html>
