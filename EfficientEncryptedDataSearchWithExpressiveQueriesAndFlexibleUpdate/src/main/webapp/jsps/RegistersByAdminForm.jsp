<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>registration by admin</title>

<!-- CSS -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"> -->

<!-- jQuery and JS bundle w/ Popper.js -->
<script src="jquery/jquery-3.5.1.slim.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/css_file.css">

<!-- css/boostrap4.css -->
<link rel="stylesheet" href="css/boostrap4.css">


</head>
<body>
<!-- Navbar -->
<jsp:include page="AdminNavbar.jsp"></jsp:include>
<!-- Navbar -->


<!--Main layout-->
<main>
  <div class="container py-5">
  	<div class="pt-5">
  		<!-- card-body body  -->
		<div class="row pb-5">
		<div class="col-sm-3"></div>
		<div class="col-sm-6 border border-primary rounded bg-light">
          <h4 class="mb-5 pb-3 pt-5 text-center">
            <strong><i>Registration Form</i></strong>
          </h4>
			
			<form action="regUsers" method="post">
				<!-- FirstName -->
				<div class="row">
					<div class="col-sm-3 float-sm-left"><label for="firstName">First Name:</label></div>
				</div>
				<div class="row mb-4">
					<div class="col-sm-10"><input id="firstName" type="text" name="firstName" placeholder="First Name" pattern="[A-Za-z]{1,}" title="Plase Enter First Name" class="form-control" required="required"></div>
				</div>
				
				<!-- lastName -->
				<div class="row">
					<div class="col-sm-3 float-sm-left"><label for="lastName">Last Name:</label></div>
				</div>
				<div class="row mb-4">
					<div class="col-sm-10"><input id="lastName" type="text" name="lastName" placeholder="Last Name:"  pattern="[A-Za-z]{1,}" title="Plase Enter Last Name" class="form-control" required="required"></div>
				</div>
				
				<!-- login Id -->
				<div class="row">
					<div class="col-sm-3 float-sm-left"><label for="loginId">Login ID:</label></div>
				</div>
				<div class="row mb-4">
					<div class="col-sm-10"><input id="loginId" type="text" name="loginId" placeholder="Login ID" pattern="[A-Za-z0-9]{3,10}" class="form-control" required="required"></div>
				</div>
				
				<!-- password -->
				<div class="row">
					<div class="col-sm-3 float-sm-left"><label for="password">Password:</label></div>
				</div>
				<div class="row mb-4">
					<div class="col-sm-10"><input id="password" type="password" name="password" placeholder="Password" pattern=".{3,14}" class="form-control" required="required"></div>
				</div>
				
				<!-- Mobile Number -->
				<div class="row">
					<div class="col-sm-4 float-sm-left"><label for="mobile">Mobile Number:</label></div>
				</div>
				<div class="row mb-4">
					<div class="col-sm-10"><input id="mobile" type="text" name="mobile" placeholder="Mobile Number" pattern="[0-9]{10}" title="Please Enter Numeric Values" maxlength="10" class="form-control" required="required"></div>
				</div>
				
				<!-- Role -->
				<div class="row">
					<div class="col-sm-2 float-sm-left"><label for="role">Role:</label></div>
				</div>
				<div class="row mb-4">
					<div class="col-sm-10">
						<select id="role" name="role" class="form-control" required="required" onchange="roleCheck()">
							<option disabled="disabled" selected="selected">--select--</option>
							<option value="DataOwner">Data Owner</option>
							<option value="DataUser">Data User</option>
						</select>
					</div>
				</div>
				
				<!-- submit -->
				<div class="row text-center">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<input type="submit" class="btn btn-success mt-4 w-50" value="Register">
					</div>
					<div class="col-sm-3"></div>
				</div>
			</form>
			
			<div class="text-center text-danger pt-4 pb-4">${msg}</div>
		</div>
		<div class="col-sm-3"></div>
        </div><!-- /card-body body   -->
	</div>
  </div>
</main>
<!--Main layout-->


<!-- footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
<!--/.Footer-->


</body>
</html>