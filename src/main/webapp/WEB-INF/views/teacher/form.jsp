<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Adicionando novo professor</title>
</head>
<body>
	<form:form action="${spring:mvcUrl('create').build()}" method="post"
		commandName="teacher">
		<div>
			<label for="name">Nome:</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="age">Idade:</label>
			<form:input path="age" />
			<form:errors path="age" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>