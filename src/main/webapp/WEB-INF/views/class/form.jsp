<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
		
	<title><spring:message code="class.form.title" /></title>
	
</head>

<header>

	<%@ include file="/WEB-INF/views/home/header.jsp" %>

</header>

<body>

	<form:form action="${spring:mvcUrl('createClasses').build()}"
		method="post" commandName="offeredClass">
		<br/><br/><br/><br/><br/><br/><br/>
		
		<div class="container-fluid">
			<label for="year" class="col-sm-2">  <spring:message code="class.form.year" />
			</label>	
			<div class="col-sm-10">
				<form:input path="year" class="form-control" placeholder="Ano"/>
				<form:errors path="year" />
			</div>
		</div>
		<br/>
		<div class="container-fluid">
			<label for="semester" class="col-sm-2"> <spring:message
					code="class.form.semester" />
			</label>	
			<div class="col-sm-10">
				<form:input path="semester" class="form-control" placeholder="Semestre"/>
				<form:errors path="semester" />
			</div>			
		</div>
		<br/>
		<div class="container-fluid">
			<label for="discipline" class="col-sm-2"> <spring:message
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
		<br/>
		<div class="container-fluid">
			<label for="teachers" class="col-sm-2"> <spring:message
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
		
		<div class="center-bottom2">
			<input type="submit" value="Enviar" class="btn btn-primary btn-md" />
		</div>
		
	</form:form>
	<!--  Importing Footer -->
	<br><br><br><br><br><br><br><br>
	<%@ include file="/WEB-INF/views/home/footer.jsp" %>

</body>
</html>
