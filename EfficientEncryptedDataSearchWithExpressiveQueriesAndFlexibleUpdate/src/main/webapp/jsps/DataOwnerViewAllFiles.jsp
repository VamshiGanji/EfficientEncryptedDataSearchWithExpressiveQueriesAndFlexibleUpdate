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
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"> -->

<!-- jQuery and JS bundle w/ Popper.js -->
<script src="jquery/jquery-3.5.1.slim.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/css_file.css">

<!-- css/boostrap4.css -->
<link rel="stylesheet" href="css/boostrap4.css">

<script type="text/javascript" src="javascript.js"></script>

<script type="text/javascript">
	function recLoad() {
		var fileKey = document.getElementById("fileKey").value;
		
		if (fileKey.length==16 && fileKey.trim()!='') {
			document.getElementById("recLoad").style.display = "block";
			document.getElementById("recBtn").style.display = "none";
			return true;
		} else
			return false;
	}
</script>
<style type="text/css">
#recLoad{
	display: none;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		
		$('table .updateKey').on('click', function() {
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
<jsp:include page="DataOwnerNavbar.jsp"></jsp:include>
<!-- Navbar -->

<!--Main layout-->
<main>
  <div class="container my-5 py-5">
  	<div class="pt-4">
		
		<div class="card">
			<div class="card-body">
  	
		  		<marquee behavior="alternate" scrollamount="2" direction="ri">
			 		<h4 class="my-4">
						<strong><i>View All Files &#38; Update Keys</i></strong>
					</h4>
		  		</marquee>
			
		<!-- foreach table  -->	
		<c:if test="${not empty uploadFiles}">
		<div class="row pt-4 ">
			<div class="col-sm-0"></div>
			<div class="col-sm-12">
				<div class="table-responsive">
				<table class="table table-border table-hover">
					<thead>
						<tr>
							<td>File ID</td>
							<td>File Name</td>
							<td>File Description</td>
							<td>Key</td>
							<td>View</td>
							<td>Update</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${uploadFiles}" var="uploadFile">
						<tr>
							<td>${uploadFile.fileId}<input type="hidden" name="id" id="id" value="${uploadFile.fileId}"></td>
							<td>${uploadFile.fileName}</td>
							<td>${uploadFile.fileDesc}</td>
							<td>${uploadFile.fileKey}</td>
							<td class="d-none">${uploadFile.oldKey}<input id="oldkey" name="oldkey" type="hidden" value="${uploadFile.oldKey}"/></td>
							<td><a href="./viewFile?fileId=${uploadFile.fileId}"  target="_blank" class="btn btn-info">View</a></td>
							<td><a href="#updateFileKey" class="updateKey btn btn-primary btn btn-info" data-toggle="modal" data-id="${uploadFile.fileId}" data-oldkey="${uploadFile.oldKey}" data-target="#myModal">Update</a>
									<!-- The Modal -->
									<div class="modal fade mt-5 pt-5" id="myModal">
									  <div class="modal-dialog">
									    <div class="modal-content">
									
									      <!-- Modal Header -->
									     <div class="modal-header">
									       <h4 class="modal-title">Update Key</h4>
									       <button type="button" class="close" data-dismiss="modal">&times;</button>
									     </div>
									
									     <!-- Modal body -->
								       <form action="viewAllFiles" method="post" id="formId">
									     <div class="modal-body">
											<!-- fileId -->
											<div class="row">
												<div class="col-sm-3 float-sm-left"><label for="fileId">File Id:</label></div>
											</div>
											<div class="row mb-4">
												<div class="col-sm-10"><input id="formFileId" type="text" name="fileId" value=""  class="form-control" required="required" readonly="readonly"/></div>
											</div> 
											
											<!-- old Key -->
											<div class="row">
												<div class="col-sm-3 float-sm-left"><label for="formOldKey">Old Key:</label></div>
											</div>
											<div class="row mb-4">
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
									     	<div id="recLoad" class="float-right">
												<button class="btn btn-primary" type="button" disabled>
												  <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
												  Updating...
												</button>
											</div>
											<input id="recBtn" type="submit" class="btn btn-info" value="Update Key" onclick="recLoad();">
									        <button type="button" class="btn btn-danger ml-2" data-dismiss="modal">Close</button>
									     </div>
									      </div>
										</form>
									
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
			</div>
			<div class="col-sm-0"></div>
		</div>
		</c:if>
			<div class="text-center text-info pt-4 text-danger">${msg}</div>
		</div>
		</div>	
		</div>
  </div>
</main>
<!--Main layout-->

<!-- footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
<!--/.Footer-->


</body>
</html>