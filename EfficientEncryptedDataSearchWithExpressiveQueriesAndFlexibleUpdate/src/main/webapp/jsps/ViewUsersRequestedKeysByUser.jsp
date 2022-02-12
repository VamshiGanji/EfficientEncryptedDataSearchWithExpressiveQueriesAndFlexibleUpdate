<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="in.nareshit.cva.model.UploadFile"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>get keys</title>

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
			$('#fileKey').val(oldKey);
			//alert( 'id'+ id + ',  oldkey'+oldKey);
			//$('#myModal').modal('show');
		});

		$('table .downloadFileClass').on('click', function() {
			var id = $(this).data('id');
			var oldKey = $(this).data('oldkey');

			$('#formFileId').val(id);
			$('#formOldKey').val(oldKey);
			$('#fileKey').val(oldKey);
			//alert( 'id'+ id + ',  oldkey'+oldKey);
			//$('#myModal').modal('show');
		});
		
		$('table #fileKey').on('click', function() {
			this.select();
			document.execCommand('copy');
			alert('key copied');
		});
		
	});
</script>

<script type="text/javascript">
	function decodeKey(x) {
		document.getElementById("copy-text"+x).onclick = function() {
			this.select();
			document.execCommand('copy');
			alert('key copied');
		}
	}
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
  	
		<!-- foreach table  -->	
		<div class="row border border-primary rounded bg-light pt-5 mt-5 pb-5 mb-5">
			<div class="col-sm-0"></div>
			<div class="col-sm-12">
				<h4 class="mb-4">
			        <strong><i>Your Requested Keys</i></strong>
			     </h4>
			      <div class="text-danger mb-3">
			      	Note: This keys are visible until your request is active status, once admin is reject your request keys disappear in the blow table.
			      </div>
				<table class="table table-border table-hover">
					<thead>
						<tr>
					
							<td>File ID</td>
							<td>File Name</td>
							<td>File Description</td>
							<td>Decoded Key</td>
							<td class="text-center">Actions</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${getKeys}" var="getKey">
						<tr>
							<td>${getKey.fileId}</td>
							<td>${getKey.fileName}</td>
							<td>${getKey.fileDesc}</td>
							<td><input id="copy-text${getKey.fileId}" class="form-control" type="text" value="${getKey.fileKey}" onclick="decodeKey('${getKey.fileId}');" readonly="readonly"></td>
							<td>
								<button id="#viewFile" class="viewFileClass btn btn-info" data-toggle="modal" onclick="form_Action('viewFile');" data-id="${getKey.fileId}" data-oldkey="${getKey.fileKey}" data-target="#myModal">View</button>
								<button id="#downloadFile" class="downloadFileClass btn btn-info mx-1 px-1" data-toggle="modal"  onclick="form_Action('downloadFile');" data-id="${getKey.fileId}" data-oldkey="${getKey.fileKey}" data-target="#myModal">Download</button>
								<!-- The Modal -->
									<div class="modal fade mt-5 pt-5" id="myModal">
									  <div class="modal-dialog">
									    <div class="modal-content">
									
									      <!-- Modal Header -->
									     <div class="modal-header">
									       <h4 class="modal-title">Verify the key</h4>
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
				<div class="text-center text-info pt-4 h5 text-danger">${msg}</div>
			</div>
			<div class="col-sm-0"></div>
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