<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<div class="container-fluid">
			<div class="navbar-header">
				<img src="<spring:url value="ufes_logo_barra_superior.png" />" alt="TestDisplay"/>
			</div>
		</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-3 margin-img">
					<img id="img-profile" class="img-thumbnail img-center img-rounded"
						src="http://placehold.it/200x232&text=Foto+Profilo">
				</div>
		
				<div class="col-xs-12 col-md-8 well well-lg margin-well">
					<strong>DADOS DO USUÁRIO</strong>
					<p>
						<i class="glyphicon glyphicon-user"></i> Nome Completo: <br><br><i
							class="glyphicon glyphicon-briefcase"></i> Formação <br><br><i
							class="glyphicon glyphicon-calendar"></i> Data de Aniversário: <br><br> <i
							class="glyphicon glyphicon-envelope"></i> E-Mail: <br><br>
					</p>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="col-md-4  well well-lg margin-well">
					
					<p><strong>Listagem e consultas</strong></p>
					<i>Selecione o professor: </i>
					<select path="teachers" class="form-control">
					<option label="-- Selecione --" value="${null}" />
					<options items="${teachersList}" itemLabel="name" itemValue="id"/>
					</select>
					<errors path="teachers" />
				</div>
	
				<div class="col-md-7 col-md-offset-1  well well-lg margin-well">
					<p><strong>Consultas possíveis</strong></p>
					<br><br>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class=" col-sm-12">
				<div class="col-sm-12 well well-lg margin-well">
					<p><strong>Distribuição de Atividades</strong></p>
					<br><br><br><br><br><br><br><br><br><br>
				</div>
			</div>
		
		</div>
	</div>

	<!--  Importing Footer -->
	<br><br><br><br><br><br><br><br>
	<%@ include file="/WEB-INF/views/home/footer.jsp" %>
	</body>
</html>