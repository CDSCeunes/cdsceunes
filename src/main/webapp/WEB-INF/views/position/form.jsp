<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<title><spring:message code="position.form.title" /></title>
	
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<form:form  action="${spring:mvcUrl('createPosition').build()}"
		method="post" commandName="position" class="form-horizontal" role="form" >
		<div class="form-group">
			<label for="name"> <spring:message code="position.form.name" />
			</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="minWorkload"> <spring:message
					code="position.form.minWorkLoad" />
			</label>
			<form:input path="minWorkload" />
			<form:errors path="minWorkload" />
		</div>
		<div>
			<label for="maxWorkload"><spring:message
					code="position.form.maxWorkLoad" /> </label>
			<form:input path="maxWorkload" />
			<form:errors path="maxWorkload" />
		</div>
		<div>
			<label for="commission"> <spring:message
					code="position.form.comission" />
			</label>
			<form:select path="commission">
				<form:option label="-- Selecione --" value="${null}" />
				<form:options items="${commissionList}" itemLabel="name"
					itemValue="id" />
			</form:select>
			<form:errors path="commission" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>
