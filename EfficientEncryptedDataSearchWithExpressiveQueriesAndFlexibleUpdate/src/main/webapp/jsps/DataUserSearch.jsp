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
<title>user search</title>

<!-- CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- jQuery and JS bundle w/ Popper.js -->
<script src="jquery/jquery-3.5.1.slim.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/css_file.css">

<!-- css/boostrap4.css -->
<link rel="stylesheet" href="css/boostrap4.css">
<!-- CSS -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"> -->


<script type="text/javascript" src="javascript.js"></script>

<!-- progress bar -->
<script type="text/javascript">
	function decryptBar()
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
				currentProgress.innerHTML=width+"% Implementing PKSE technology...";
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

<script type="text/javascript">
	function form_Action(action){
		document.getElementById("formId").action = action;
		//alert(action);
	}
</script>

<!-- loading spinner -->
<script type="text/javascript">
	function loading(action) {
		if (action == 'submit')
			document.getElementById("loader").style.display = "block";
		else if (action == 'close')
			document.getElementById("loader").style.display = "none";
	}
</script>
<style type="text/css">
#loader{
	display: none;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		$('table .viewFileClass').on('click', function() {
			var id = $(this).data('id');
			var oldKey = $(this).data('oldkey');

			$('#formFileId').val(id);
			$('#formOldKey').val(oldKey);
			//alert( 'id'+ id + ',  oldkey'+oldKey);
			//$('#myModal').modal('show');
		});

		$('table .downloadFileClass').on('click', function() {
			var id = $(this).data('id');
			var oldKey = $(this).data('oldkey');

			$('#formFileId').val(id);
			$('#formOldKey').val(oldKey);
			//alert( 'id'+ id + ',  oldkey'+oldKey);
			//$('#myModal').modal('show');
		});
	});
</script>
</head>
<body>
<!-- Navbar -->
<jsp:include page="DataUserNavbar.jsp"></jsp:include>
<!-- Navbar -->

<!--Main layout-->
<main>
  <div class="container py-5">
  	<div class="pt-4">
  		<!-- card-body body  -->
		<div class="row border border-primary rounded bg-light pt-5 mt-5 pb-5 mb-5 ">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			
			<div class="row"><!-- row -->
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<h4 class="mb-4">
			           <strong><i>Search File</i></strong>
			         </h4>
			
				<form action="search" method="post">
					<!-- search file -->
					<div class="row">
						<div class="col-sm-3 float-sm-left"><label for="searchFile">Search File:</label></div>
					</div>
					<div class="row mb-4">
							<input id="searchFile" type="text" name="searchFile" placeholder="Search Uploaded File" class="form-control w-75 mr-1" required="required">
							<input type="submit" class="btn btn-success" value="Search"  onclick="decryptBar();">
					</div> 
				</form>
				<!-- progress bar -->
				<div id="progressBar" class="pt-5 mt-3">
					<div class="progress">
					  <div id="dynamic" class="progress-bar progress-bar-success progress-bar-striped progress-bar-animated active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
					    <span id="current-progress"></span>
					  </div>
					</div>
				</div>
				
				<div class="text-center text-info pt-4 text-danger">${msg}</div>
					</div>
					<div class="col-sm-2"></div>
				</div><!-- row -->
		<!-- View files related to search -->
		<div class="col-sm-2"></div>
        </div><!-- /card-body body   -->
	</div>
			<!-- foreach table  -->	
		<c:if test="${not empty uploadFiles}">
		<div class="row border border-primary rounded bg-light pt-5 mt-5 pb-5 mb-5">
			<div class="col-sm-0"></div>
			<div class="col-sm-12">
				<table class="table table-border table-hover">
					<thead>
						<tr>
							<td>File ID</td>
							<td>File Name</td>
							<td>File Description</td>
							<td class="d-none">Key</td>
							<td class="px-5">Actions</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${uploadFiles}" var="uploadFile">
						<tr>
							<td>${uploadFile.fileId}</td>
							<td>${uploadFile.fileName}</td>
							<td>${uploadFile.fileDesc}</td>
							<td class="d-none">${uploadFile.fileKey}</td>
							<td>
								<button id="#viewFile" class="viewFileClass btn btn-info" data-toggle="modal" onclick="form_Action('viewFile');" data-id="${uploadFile.fileId}" data-oldkey="${uploadFile.oldKey}" data-target="#myModal">View</button>
								<button id="#downloadFile" class="downloadFileClass btn btn-info mx-1 px-1" data-toggle="modal"  onclick="form_Action('downloadFile');" data-id="${uploadFile.fileId}" data-oldkey="${uploadFile.oldKey}" data-target="#myModal">Download</button>
								<!-- The Modal -->
									<div class="modal fade mt-5 pt-5" id="myModal">
									  <div class="modal-dialog">
									    <div class="modal-content">
									
									      <!-- Modal Header -->
									     <div class="modal-header">
									       <h4 class="modal-title">Verify the Key</h4>
									       <button type="button" class="close" data-dismiss="modal" onclick="loading('close');">&times;</button>
									     </div>
									
									     <!-- Modal body -->
								       <form action="#" method="post" id="formId">
									     <div class="modal-body">
											<!-- fileId -->
											<div class="row">
												<div class="col-sm-3 float-sm-left"><label for="fileId">File Id:</label></div>
											</div>
											<div class="row mb-4">
												<div class="col-sm-10"><input id="formFileId" type="text" name="fileId" value=""  class="form-control" required="required" readonly="readonly"/></div>
											</div> 
											
											<!-- old Key -->
											<div class="row d-none">
												<div class="col-sm-3 float-sm-left"><label for="formOldKey">Old Key:</label></div>
											</div>
											<div class="row mb-4 d-none">
												<div class="col-sm-10"><input id="formOldKey" type="text" name="oldKey" value="" class="form-control"  readonly="readonly"/></div>
											</div> 
											
											<!-- new key -->
											<div class="row">
												<div class="col-sm-3 float-sm-left"><label for="fileKey">File Key:</label></div>
											</div>	
											<div class="row mb-3">
												<div class="col-sm-10"><input id="fileKey" type="text" name="fileKey" placeholder="File Key [must & should 16 words] " pattern="[a-z0-9A-Z]{16}" maxlength="16" class="form-control" required="required"></div>
											</div>
											
									
									     <!-- Modal footer -->
									     <div class="modal-footer">
											<input id="recBtn" type="submit" class="btn btn-info" value="Verify Key" onclick="loading('submit');">
									        <button type="button" class="btn btn-danger ml-2" data-dismiss="modal" onclick="loading('close');">Close</button>
									     </div>
									      </div>
										</form>
										
										<!-- loading spinner -->
										<div id="loader" class="py-3">
											<div class="text-center">
									  			<div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
												  <span class="sr-only">Loading...</span>
												</div>
											</div>
							    			<div class="text-center font-weight-bold">Verifying the key...</div>
										</div>
										
									    </div>
									  </div>
									</div>
									<!-- /The Modal -->
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-0"></div>
		</div>
		</c:if>
		</div>
  </div>
</main>
<!--Main layout-->


<!-- footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
<!--/.Footer-->


</body>
</html>