<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title> <spring:message code="position.form.title" /> </title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createPosition').build()}"
		method="post" commandName="position">
		<div>
			<label for="name"> <spring:message code="position.form.name" /> </label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="minWorkload"> <spring:message code="position.form.minWorkLoad" /> </label>
			<form:input path="minWorkload" />
			<form:errors path="minWorkload" />
		</div>
		<div>
			<label for="maxWorkload"><spring:message code="position.form.maxWorkLoad" /> </label>
			<form:input path="maxWorkload" />
			<form:errors path="maxWorkload" />
		</div>
		<div>
			<label for="commission"> <spring:message code="position.form.comission" /> </label>
			<form:input path="commission" />
			<form:errors path="commission" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>
