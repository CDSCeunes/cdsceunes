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
	<title><spring:message code="teacher.form.title" /></title>
</head>
<body>

		<nav class="navbar navbar-inverse"> </nav>

	<form:form action="${spring:mvcUrl('createTeacher').build()}"
		method="post" commandName="teacher">
		<div class="wrapper">
			<label for="name" class="col-sm-2 control-label"> <spring:message code="teacher.form.name" />
			</label>
			<div class="col-sm-10">
				<form:input path="name" class="form-control" placeholder="Nome"/>
				<form:errors path="name" />
			</div>
		</div>
		
		<div class="wrapper">
			<label for="login" class="col-sm-2 control-label"> <spring:message code="teacher.form.login" />
			</label>
			<div class="col-sm-10">
				<form:input path="login" class="form-control" placeholder="Login"/>
				<form:errors path="login" />
			</div>
		</div>
		
		<div class="wrapper">
			<label for="admissionDate" class="col-sm-2 control-label"> <spring:message
					code="teacher.form.admissionDate" />
			</label>
			<div class="col-sm-10">
				<form:input type="date" path="admissionDate" class="form-control" placeholder="Data"/>
				<form:errors path="admissionDate" />
			</div>
		</div>
		
		<div class="wrapper">
			<label for="department" class="col-sm-2 control-label"><spring:message code="teacher.form.department" /></label>
			<div class="col-sm-10">
				<form:select path="department" class="form-control">
					<form:option label="-- Selecione --" value="${null}" />
					<form:options items="${departmentList}" itemLabel="name"
						itemValue="id" />
				</form:select>
				<form:errors path="department" />
			</div>	
		</div>
		
		<div class="wrapper">
			<input type="submit" value="Enviar" class="btn btn-primary btn-md"/>
		</div>
	</form:form>
</body>
</html>
