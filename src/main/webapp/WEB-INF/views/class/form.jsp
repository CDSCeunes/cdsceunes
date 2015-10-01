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
		

		
	<title><spring:message code="class.form.title" /></title>
</head>
<body>
	
	<nav class="navbar navbar-inverse"> </nav>
	
	<form:form action="${spring:mvcUrl('createClasses').build()}"
		method="post" commandName="offeredClass">
		
		<div class="wrapper">
			<label for="year" class="col-sm-2 control-label">  <spring:message code="class.form.year" />
			</label>	
			<div class="col-sm-10">
				<form:input path="year" class="form-control" placeholder="Ano"/>
				<form:errors path="year" />
			</div>
		</div>
		
		<div class="wrapper">
			<label for="semester" class="col-sm-2 control-label"> <spring:message
					code="class.form.semester" />
			</label>	
			<div class="col-sm-10">
				<form:input path="semester" class="form-control" placeholder="Semestre"/>
				<form:errors path="semester" />
			</div>			
		</div>
		
		<div class="wrapper">
			<label for="discipline" class="col-sm-2 control-label"> <spring:message
					code="class.form.discipline" />
			</label>
			<div class="col-sm-10">
				<form:select path="discipline" class="form-control">
						<form:option label="-- Selecione --" value="${null}" />
						<form:options items="${disciplineList}" itemLabel="name"
							itemValue="id" />
				</form:select>
			</div>
			<form:errors path="discipline"  />
		</div>
		
		<div class="wrapper">
			<label for="teachers" class="col-sm-2 control-label"> <spring:message
					code="class.form.teachers" />
			</label>
			<div class="col-sm-10">
				<form:select path="teachers" class="form-control">
				<form:option label="-- Selecione --" value="${null}" />
				<form:options items="${teachersList}" itemLabel="name"
					itemValue="id" />
				</form:select>
				<form:errors path="teachers" />
			</div>
		</div>
		
		<div class="wrapper">
			<input type="submit" value="Enviar" class="btn btn-primary btn-md"/>
		</div>
		
	</form:form>
</body>
</html>
