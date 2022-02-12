<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>dataOwner home</title>

<!-- CSS -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"> -->

<!-- jQuery and JS bundle w/ Popper.js -->
<script src="jquery/jquery-3.5.1.slim.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/css_file.css">

<!-- css/boostrap4.css -->
<link rel="stylesheet" href="boostrap4.css">

</head>
<body>
<!-- Navbar -->
<jsp:include page="DataOwnerNavbar.jsp"></jsp:include>
<!-- Navbar -->

<main>
  <div class="container pt-5  pb-5 mb-5 text-white">
  	<div class="pt-5">
  			<h1 class="mb-4 pt-5 text-center">
           		<strong><i>Welcome to <%=session.getAttribute("loginId") %></i></strong>
        	 </h1>

			<p>Outsourcing encrypted data to cloud servers that has become a
				prevalent trend among Internet users to date. There is along list of
				advantages on data outsourcing, such as the reduction cost of local
				data management.
			</p>
			<p>How to securely operate encrypted data (remotely), however, is
				the top-rank concern over data owner. Liang et al. proposed a novel
				encrypted cloud-based data shareand search system without loss of
				privacy.
			</p>
			<p>How The system allows users to flexibly search and share
				encrypted data as well as updatingkeyword field. However, the search
				complexity of the system is of extreme inefficiency, O(nd), where d
				is the total number of systemfiles and n is the size of query
				formula.
			</p>
			<p>HowThis paper, for the first time, leverages the "oblivious
				cross search" technology in public keysearchable encryption context
				to reduce the search complexity to only O(nf(w)), where f(w) is the
				number of files embedded withthe "least frequent keyword" w.
			</p>
			<p>HowThe new scheme maintains efficient encrypted data share and
				keyword field update as well. This paperfurther revisits the
				security models for payload security, keyword privacy and search
				token privacy (i.e. search pattern privacy) andmeanwhile, presents
				security and efficiency analysis for the new scheme.
			</p>
		</div>
  </div>
</main>
<!--Main layout-->

<!-- footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
<!--/.Footer-->


</body>
</html>