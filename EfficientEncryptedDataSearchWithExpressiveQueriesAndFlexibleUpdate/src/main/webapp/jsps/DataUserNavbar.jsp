<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/css_file.css">

<!-- css/boostrap4.css -->
<link rel="stylesheet" href="css/boostrap4.css">

<style type="text/css">
body{
background-image: url(images/BG1.jpg) ;
-webkit-background-size: cover;
  -moz-background-size: cover;
  background-size: cover;
  -o-background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: 100%;
  background-size: 100%;
  
}
</style>
</head>
<body>
<%
	if (session.getAttribute("role") == null) {
		request.setAttribute("msg", "Please Enter UserId & Password !!");
		request.getRequestDispatcher("/jsps/Login.jsp").forward(request, response);
	}
%>

<!-- Navbar -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-primary scrolling-navbar">
  <div class="container">

    <!-- Brand -->
    <a class="navbar-brand" href="login">
      <strong class="d-block">Efficient Encrypted Data Search with Expressive Queries and Flexible Update</strong>
      <strong class="d-none">Efficient Encrypted Data Search</strong>
    </a>

    <!-- Collapse -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Links -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">

      <!-- Right -->
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="login">Home
            <span class="sr-only">(current)</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="search">Search File</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="getKeys">Get Keys</a>
        </li>
      </ul>

      <!-- Right -->
      <ul class="navbar-nav nav-flex-icons">
        <li class="nav-item">
          <a href="logout" class="nav-link border border-light rounded">
            <i class="fab fa-github mr-2"></i>Logout
          </a>
        </li>
      </ul>

    </div>

  </div>
</nav>
<!-- Navbar -->
</body>
</html>