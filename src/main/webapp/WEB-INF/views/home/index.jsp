<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
		<spring:url value="/resources/css/home.css" var="home" />
		<link href="${home}" rel="stylesheet" />
	    <title>Login CDS Ceunes</title>
	</head>
	
	<body>
		
		<nav class="navbar navbar-inverse">
  
		</nav>
		
		<div>
		    <div class="col-md-offset-4 col-md-4">
			    <div class="form-login">
					<h4>Welcome</h4>
					<input type="text" id="userName" class="form-control input-sm chat-input" placeholder="username" />
					</br>
					<input type="text" id="userPassword" class="form-control input-sm chat-input" placeholder="password" />
					</br>
					<div class="wrapper">
						<span class="group-btn">     
						<a href="#" class="btn btn-primary btn-md">login <i class="fa fa-sign-in"></i></a>
					</span>
					</div>
			    </div>
		    </div>
		</div>
		
	</body>
</html>