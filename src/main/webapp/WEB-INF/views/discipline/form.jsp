<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title><spring:message code="discipline.form.title" /></title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createDiscipline').build()}"
		method="post" commandName="discipline">
		<div>
			<label for="name"> <spring:message code="discipline.form.name" />
			</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="course"> <spring:message
					code="discipline.form.course" />
			</label>
			<form:input path="course" />
			<form:errors path="course" />
		</div>
    <div>
			<label for="semesters"> <spring:message
					code="discipline.form.semesters" />
			</label>
			<form:input path="semesters" />
			<form:errors path="semesters" />
		</div>
    <div>
			<label for="teoricLoad"> <spring:message
					code="discipline.form.teoricLoad" />
			</label>
			<form:input path="teoricLoad" />
			<form:errors path="teoricLoad" />
		</div>
    <div>
			<label for="exerciseLoad"> <spring:message
					code="discipline.form.exerciseLoad" />
			</label>
			<form:input path="exerciseLoad" />
			<form:errors path="exerciseLoad" />
		</div>
    <div>
			<label for="labLoad"> <spring:message
					code="discipline.form.labLoad" />
			</label>
			<form:input path="labLoad" />
			<form:errors path="labLoad" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>
