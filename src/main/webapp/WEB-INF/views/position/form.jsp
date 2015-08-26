<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Adicionando novo cargo</title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createPosition').build()}"
		method="post" commandName="position">
		<div>
			<label for="name">Nome do cargo:</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="minWorkload">Carga hor�ria m�nima:</label>
			<form:input path="minWorkload" />
			<form:errors path="minWorkload" />
		</div>
		<div>
			<label for="maxWorkload">Carga hor�ria m�xima:</label>
			<form:input path="maxWorkload" />
			<form:errors path="maxWorkload" />
		</div>
		<div>
			<label for="commission">Comiss�o pertencente:</label>
			<form:input path="commission" />
			<form:errors path="commission" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>