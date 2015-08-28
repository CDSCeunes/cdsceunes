<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title> <spring:message code="teacher.form.title" /> </title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createTeacher').build()}"
		method="post" commandName="teacher">
		<div>
			<label for="name"> <spring:message code="teacher.form.name" /> </label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="login"> <spring:message code="teacher.form.login" /> </label>
			<form:input path="login" />
			<form:errors path="login" />
		</div>
		<div>
			<label for="admissionDate"> <spring:message code="teacher.form.admissionDate" /> </label>
			<form:input type="date" path="admissionDate" />
			<form:errors path="admissionDate" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>
