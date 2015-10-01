<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>

<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
			
	<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
			
	<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
	<spring:url value="/resources/css/home.css" var="home" />
	<link href="${home}" rel="stylesheet" />

	<title> <spring:message code="department.form.title" /> </title>
</head>
<body>

	<nav class="navbar navbar-inverse"> </nav>

	<form:form action="${spring:mvcUrl('createDepartment').build()}"
		method="post" commandName="department">
		<div class="wrapper">
			<label for="name" class="col-sm-2 control-label"> <spring:message code="department.form.name" /> </label>
			
			<div class="col-sm-10">
				<form:input path="name" class="form-control" placeholder="Nome"/>
				<form:errors path="name" />
			</div>
			
		</div>
		<div class="wrapper">
			<label for="center" class="col-sm-2 control-label"> <spring:message code="department.form.center" /> </label>
			
			<div class="col-sm-10">
				<form:input path="center" class="form-control" placeholder="Centro"/>
				<form:errors path="center" />
			</div>
			
		</div>
		<div class="wrapper">
			<input type="submit" value="Enviar"  class="btn btn-primary btn-md"/>
		</div>
	</form:form>
</body>
</html>
