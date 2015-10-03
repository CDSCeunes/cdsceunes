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
		<div class="container">
			<br><br><br>
			<div class="row" style="text-align:center">
		        <img src="resources/img/logos-ufes.png" >
		        <br><br>
		    </div>
			
			<form action="#" class="form-signin" method="post">
		        <h2 class="col-md-4 col-md-offset-4">Login</h2>
		        <div class="row">
			        <div class="col-md-4 col-md-offset-4">
				        <input id="login" name="login" class="form-control" placeholder="login único ufes" required autofocus type="text">
				        <input id="senha" name="senha" class="form-control" placeholder="senha única ufes" required type="password">
			        	<br><br>
			        	<button class="btn btn-info btn-block" type="submit">Entrar</button>
			        </div>
		        </div>
		    </form>
		    
		    <br><br>		
			<hr />
		    <div class="row">
		        <footer style="text-align:center">
		            <span style="font-size:small">
		                &copy; Coordenação de Tecnologia da Informação do CEUNES - 2015
		            </span>
		        </footer>
		    </div>
		</div>
</body>

</html>