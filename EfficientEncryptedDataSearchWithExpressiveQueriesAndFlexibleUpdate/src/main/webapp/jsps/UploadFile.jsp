<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>upload file</title>

<!-- CSS -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"> -->

<!-- jQuery and JS bundle w/ Popper.js -->
<script src="jquery/jquery-3.5.1.slim.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/css_file.css">

<!-- css/boostrap4.css -->
<link rel="stylesheet" href="css/boostrap4.css">

<script type="text/javascript" src="javascript.js"></script>

<!-- progress bar -->
<script type="text/javascript">
	function onc()
	{
		//window.alert("Confirm Accept");
		var val =  document.getElementById("dynamic");
		 document.getElementById("progressBar").style.display = "block";
		var currentProgress =  document.getElementById("current-progress");
		var width = 1;
		var id = setInterval(frame,50);
		
		function frame()
		{
			if(width>=0 && width<=55)
			{
				currentProgress.innerHTML=width+"%";
			}
			if(width>=56 && width<=100)
			{
				currentProgress.innerHTML=width+"% Implementing Efficient Encrypted Data...";
			}
			
			if(width>=100)
			{
				clearInterval(id);
			}
			else
			{
				width++;
				val.style.width = width + "%";
			}
		}
		
	}
</script>
<style type="text/css">
#progressBar{
	display: none;
}
</style>
</head>
<body>
<!-- Navbar -->
<jsp:include page="DataOwnerNavbar.jsp"></jsp:include>
<!-- Navbar -->

<!--Main layout-->
<main>
  <div class="container py-5">
  	<div class="pt-4">
  		<!-- card-body body  -->
		<div class="row border border-primary rounded bg-light pt-5 mt-5 pb-5 mb-5 ">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			
          <h3 class="mb-4">
            <strong><i>Upload File</i></strong>
          </h3>
			
			<form action="uploadFile" method="post" enctype="multipart/form-data">
				<!-- upload file -->
				<div class="row">
					<div class="col-sm-3 float-sm-left"><label for="uploadFile">Upload File:</label></div>
				</div>
				<div class="row mb-4">
					<div class="col-sm-10"><input id="uploadFile" type="file" name="uploadFile" placeholder="Upload File" class="form-control" required="required"></div>
				</div>
				
				<!-- file Key -->
				<div class="row">
					<div class="col-sm-3 float-sm-left"><label for="fileKey">File Key:</label></div>
				</div>
				<div class="row mb-3">
					<div class="col-sm-10"><input id="fileKey" type="text" name="fileKey" placeholder="File Key [must & should 16 words] " pattern="[a-z0-9A-Z]{16}" maxlength="16" class="form-control" required="required"></div>
				</div>
				
				<!-- file Description -->
				<div class="row">
					<div class="col-sm-3 float-sm-left"><label for="fileDesc">File Labels:</label></div>
				</div>
				<div class="row mb-3">
					<div class="col-sm-10"><input id="fileDesc" type="text" name="fileDesc" placeholder="Data User Searchable Content" class="form-control" required="required"></div>
				</div>
				
				
				
				<div class="row text-center">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<input type="submit" class="btn btn-success mt-4 w-50" value="Upload"  onclick="onc();">
					</div>
					<div class="col-sm-3"></div>
				</div>
			</form>
			<!-- progress bar -->
			<div id="progressBar" class="pt-5 mt-3">
				<div class="progress">
				  <div id="dynamic" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
				    <span id="current-progress"></span>
				  </div>
				</div>
			</div>
				
			<div class="text-center text-info pt-4 text-danger">${msg}</div>
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