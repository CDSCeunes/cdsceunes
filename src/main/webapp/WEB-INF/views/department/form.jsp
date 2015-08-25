<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Adicionando novo departamento</title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createDepartment').build()}"
		method="post" commandName="department">
		<div>
			<label for="name">Nome:</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="center">Centro:</label>
			<form:input path="center" />
			<form:errors path="center" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>