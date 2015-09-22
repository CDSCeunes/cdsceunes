<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	    <title>Login CDS Ceunes</title>
		<style type="text/css">
			<%@include file="style.css" %>
		</style>
	</head>
	
	<body>
		<div class="container">
		    <div class="row">
		        <div class="col-md-offset-5 col-md-3">
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
		</div>
	</body>
</html>