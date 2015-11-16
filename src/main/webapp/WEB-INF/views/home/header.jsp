<!-- Carregando Bootstrap -->
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		
<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		
<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
<!-- Carregando CSS-->
	<spring:url value="/resources/css/home.css" var="home" />
	<link href="${home}" rel="stylesheet" />
	<spring:url value="/resources/img/ufes_logo_barra_superior.png" var="logo" />
<!-- Barra Superior -->
<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
			
				<a class="navbar-brand" href="#"> <img alt="Brand" src="${logo}">
				</a>
			</div>
		</div>
</nav>

		

		