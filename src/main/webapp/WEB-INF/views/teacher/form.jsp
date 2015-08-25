<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Adicionando novo professor</title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createTeacher').build()}"
		method="post" commandName="teacher">
		<div>
			<label for="name">Nome:</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="login">Login:</label>
			<form:input path="login" />
			<form:errors path="login" />
		</div>
		<div>
			<label for="department">Departamento:</label>
			<form:input path="department" />
			<form:errors path="department" />
		</div>
		
		<div>
			<label for="name">Data de admissão:</label>
			<form:input type="date" path="admissionDate" />
			<form:errors path="admissionDate" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>