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
	
	<title><spring:message code="discipline.form.title" /></title>
</head>
<body>

	<nav class="navbar navbar-inverse"> </nav>

	<form:form action="${spring:mvcUrl('createDiscipline').build()}"
		method="post" commandName="discipline">
		<div class="wrapper">
			<label for="name" class="col-sm-2 control-label"> <spring:message code="discipline.form.name" />
			</label>
			<div class="col-sm-10">
				<form:input path="name" class="form-control" placeholder="Nome"/>
				<form:errors path="name" />
			</div>
		</div>
		
		<div class="wrapper">
			<label for="course" class="col-sm-2 control-label"> <spring:message
					code="discipline.form.course" />
			</label>
			<div class="col-sm-10">
				<form:input path="course" class="form-control" placeholder="Curso"/>
				<form:errors path="course" />
			</div>
		</div>
	    
	    <div class="wrapper">
				<label for="semesters" class="col-sm-2 control-label"> <spring:message
						code="discipline.form.semesters" />
				</label>
				<div class="col-sm-10">
					<form:input path="semesters" class="form-control" placeholder="Semestres"/>
					<form:errors path="semesters" />
				</div>
		</div>
		
	    <div class="wrapper">
			<label for="teoricLoad" class="col-sm-2 control-label"> <spring:message
					code="discipline.form.teoricLoad" />
			</label>
			<div class="col-sm-10">
				<form:input path="teoricLoad" class="form-control" placeholder="Carga horária Teórica"/>
				<form:errors path="teoricLoad" />
			</div>
		</div>
		
	    <div class="container-fluid">
				<label for="exerciseLoad" class="col-sm-4 control-label"> <spring:message
						code="discipline.form.exerciseLoad" />
				</label>
				<div class="col-sm-6">
					<form:input path="exerciseLoad" class="form-control" placeholder="Carga Horária de Exercícios"/>
					<form:errors path="exerciseLoad" />
				</div>
		</div>
	    
	    <div class="container-fluid">
			<label for="labLoad" class="col-sm-2 control-label"> <spring:message
					code="discipline.form.labLoad" />
			</label>
			<div class="col-sm-8">
				<form:input path="labLoad" class="form-control" placeholder="Carga Horária dos laboratórios"/>
				<form:errors path="labLoad" />
			</div>
		</div>
		<div class="wrapper">
			<input type="submit" value="Enviar" class="btn btn-primary btn-md"/>
		</div>
	</form:form>
</body>
</html>
