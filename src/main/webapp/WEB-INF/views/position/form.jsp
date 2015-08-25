<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Adicionando novo cargo</title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createPosition').build()}"
		method="post" commandName="department">
		<div>
			<label for="name">Nome:</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="minWorkload">Carga horária mínima:</label>
			<form:input path="minWorkload" />
			<form:errors path="minWorkload" />
		</div>
		<div>
			<label for="maxWorkload">Carga horária máxima:</label>
			<form:input path="maxWorkload" />
			<form:errors path="maxWorkload" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>