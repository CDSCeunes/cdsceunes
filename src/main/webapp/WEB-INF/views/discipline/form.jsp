<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	
	<title><spring:message code="discipline.form.title" /></title>

</head>

<header>

	<%@ include file="/WEB-INF/views/home/header.jsp" %>

</header>

<body>

	<form:form action="${spring:mvcUrl('createDiscipline').build()}"
		method="post" commandName="discipline">
		
		<br/><br/><br/><br/><br/><br/><br/>
		<div class="container-fluid">
			<label for="name" class="col-sm-2"> <spring:message code="discipline.form.name" />
			</label>
			<div class="col-sm-10">
				<form:input path="name" class="form-control" placeholder="Nome"/>
				<form:errors path="name" />
			</div>
		</div>
		<br/>
		<div class="container-fluid">
			<label for="course" class="col-sm-2"> <spring:message
					code="discipline.form.course" />
			</label>
			<div class="col-sm-10">
				<form:input path="course" class="form-control" placeholder="Curso"/>
				<form:errors path="course" />
			</div>
		</div>
	    <br/>
	    <div class="container-fluid">
				<label for="semesters" class="col-sm-2"> <spring:message
						code="discipline.form.semesters" />
				</label>
				<div class="col-sm-10">
					<form:input path="semesters" class="form-control" placeholder="Semestres"/>
					<form:errors path="semesters" />
				</div>
		</div>
		<br/>
	    <div class="container-fluid">
			<label for="teoricLoad" class="col-sm-2"> <spring:message
					code="discipline.form.teoricLoad" />
			</label>
			<div class="col-sm-10">
				<form:input path="teoricLoad" class="form-control" placeholder="Carga horária Teórica"/>
				<form:errors path="teoricLoad" />
			</div>
		</div>
		<br/>
	    <div class="container-fluid">
				<label for="exerciseLoad" class="col-sm-2"> <spring:message
						code="discipline.form.exerciseLoad" />
				</label>
				<div class="col-sm-10">
					<form:input path="exerciseLoad" class="form-control" placeholder="Carga Horária de Exercícios"/>
					<form:errors path="exerciseLoad" />
				</div>
		</div>
	    
	    <div class="container-fluid">
			<label for="labLoad" class="col-sm-2"> <spring:message
					code="discipline.form.labLoad" />
			</label>
			<div class="col-sm-10">
				<form:input path="labLoad" class="form-control" placeholder="Carga Horária dos laboratórios"/>
				<form:errors path="labLoad" />
			</div>
		</div>
		
		<div id="center-bottom2">
			<input type="submit" value="Enviar" class="btn btn-primary btn-md"/>
		</div> 
	</form:form>
	<br><br><br><br><br><br><br><br>
	<%@ include file="/WEB-INF/views/home/footer.jsp" %>
	
</body>
</html>
