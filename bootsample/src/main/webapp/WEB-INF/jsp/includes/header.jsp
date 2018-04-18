<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cihat Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.modal-header, h4, .close {
	background-color: #5cb85c;
	color: white !important;
	text-align: center;
	font-size: 30px;
}

.modal-footer {
	background-color: #f9f9f9;
}

form .error {
	color: #A94442;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Cihat.com</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="/">Home</a></li>
			<c:choose>
				<c:when test="${role == 'ROLE_USER' || role == 'ROLE_ADMIN'}">
					<li><a href="/app-logout">Logout</a></li>				
				</c:when>
				<c:when test="${role == 'ROLE_ANONYMOUS'}">
					<li><a href="/login">Login</a></li>				
				</c:when>
			</c:choose>
			
			<li><a href="/task/">Task</a></li>
			<li><a href="/task/all-task">All task</a></li>
		</ul>
	</div>
	</nav>
	<div class="container-fluid" style="margin-top: 80px">
		<h3>Top Fixed Navbar</h3>
		<p>A fixed navigation bar stays visible in a fixed position (top
			or bottom) independent of the page scroll.</p>
		<h1>Scroll this page to see the effect</h1>
	</div>
	<form:form modelAttribute="user">

		<div class="container">
			<!-- Trigger the modal with a button -->
			<!--   <button type="button" class="btn btn-default btn-lg" id="myBtn">Login</button> -->

			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="padding: 35px 50px;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4>
								<span class="glyphicon glyphicon-lock"></span> Login
							</h4>
						</div>
						<div class="modal-body" style="padding: 40px 50px;">
							<form role="form">
								<div class="form-group">
									<form:label path="name">
										<span class="glyphicon glyphicon-user"></span> Username</form:label>
									<form:input path="name" type="email" class="form-control"
										id="usrname" placeholder="Enter email"/>
									<div class="error">${errorEmail}</div>
								</div>
								<div class="form-group">
									<form:label path="password">
										<span class="glyphicon glyphicon-eye-open"></span> Password</form:label>
									<form:input path="password" type="password" class="form-control"
										id="psw" placeholder="Enter password"/>
										<div class="error">${errorPassword}</div>
								</div>
								<div class="checkbox">
									<label><input type="checkbox" value="" checked>Remember
										me</label>
								</div>
								<button type="submit" class="btn btn-success btn-block">
									<span class="glyphicon glyphicon-off"></span> Login
								</button>
							</form>
						</div>
						<div class="modal-footer">
							<button type="submit"
								class="btn btn-danger btn-default pull-left"
								data-dismiss="modal">
								<span class="glyphicon glyphicon-remove"></span> Cancel
							</button>
							<p>
								Not a member? <a href="#">Sign Up</a>
							</p>
							<p>
								Forgot <a href="#">Password?</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<script>
		$(document).ready(function() {
			$("#myBtn").click(function() {
				$("#myModal").modal();
			});
		});
	</script>