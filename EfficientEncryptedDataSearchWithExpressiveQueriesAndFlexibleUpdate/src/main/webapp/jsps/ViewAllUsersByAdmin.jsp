<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="in.nareshit.cva.model.UploadFile"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>view all users by admin</title>

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
<jsp:include page="AdminNavbar.jsp"></jsp:include>
<!-- Navbar -->

<!--Main layout-->
<main>
  <div class="container py-5">
  	<div class="pt-4">
  		<!-- card-body body  -->
		<div class="row border border-primary rounded bg-light pt-5 mt-5 pb-5 mb-5 ">
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<h4 class="mb-4">
				<strong><i>Data Users Details</i></strong>
			</h4>
			
		<!-- foreach table  -->	
		<div class="row pt-3">
			<div class="col-sm-0"></div>
			<div class="col-sm-12">
				<table class="table table-border table-hover">
					<thead>
						<tr>
							<td>User ID</td>
							<td>First Name</td>
							<td>Last Name</td>
							<td>Login ID</td>
							<td>Mobile</td>
							<td>Status</td>
							<td>Operation</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${viewAllUsers}" var="viewAllUser">
						<tr>
							<td>${viewAllUser.userId}</td>
							<td>${viewAllUser.firstName}</td>
							<td>${viewAllUser.lastName}</td>
							<td>${viewAllUser.loginId}</td>
							<td>${viewAllUser.mobile}</td>
							<td class="d-none">${viewAllUser.status}</td>
							<td>Active</td>
							<td><form action="inActiveUsers" method="post">
								<input type="hidden" name="userId" value="${viewAllUser.userId}">
								<input type="hidden" name="status" value="0">
								<input class="btn btn-danger"  type="submit" value="De-activate">
								</form>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				
				<div class="text-center text-info pt-4 text-danger">${msg}</div>
			</div>
			<div class="col-sm-0"></div>
		</div>
		</div>
		
		<!-- View files related to search -->
		<div class="col-sm-1"></div>
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