<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>

<!-- CSS -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"> -->

<!-- jQuery and JS bundle w/ Popper.js -->
<script src="jquery/jquery-3.5.1.slim.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/css_file.css">

<!-- css/boostrap4.css -->
<link rel="stylesheet" href="css/boostrap4.css">

<script type="text/javascript" src="javascript.js"></script>

</head>
<body>
<!-- Navbar -->
<jsp:include page="LoginNavbar.jsp"></jsp:include>
<!-- Navbar -->


<!--Main layout-->
<main>
  <div class="container pt-5">
  	<div class="pt-5">
  		<!-- card-body body  -->
		<div class="row border border-primary rounded bg-light pt-5 mt-5 pb-5 mb-5 ">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			
          <h4 class="mb-4">
            <strong><i>login</i></strong>
          </h4>
			
			<form action="login" method="post">
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
				<div class="row mb-3">
					<div class="col-sm-10"><input id="password" type="password" name="password" placeholder="Password" pattern=".{3,14}" class="form-control" required="required"></div>
				</div>
				
				
				<div class="row text-center">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<input type="submit" class="btn btn-success mt-4 w-50" value="Login">
					</div>
					<div class="col-sm-3"></div>
				</div>
			</form>
			
			<div class="text-center text-white pt-4 text-danger">${msg}</div>
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