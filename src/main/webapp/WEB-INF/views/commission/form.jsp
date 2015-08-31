<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title><spring:message code="commission.form.title" /></title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createCommission').build()}"
		method="post" commandName="commission">
		<div>
			<label for="name"> <spring:message code="commission.form.name" />
			</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="scope"> <spring:message code="commission.form.scope" />
			</label>
			<form:input path="scope" />
			<form:errors path="scope" />
		</div>
    <div>
      <label for="minNumber"> <spring:message code="commission.form.minNumber" />
      </label>
      <form:input path="minNumber" />
      <form:errors path="minNumber" />
    </div>
    <div>
      <label for="maxNumber"> <spring:message code="commission.form.maxNumber" />
      </label>
      <form:input path="maxNumber" />
      <form:errors path="maxNumber" />
    </div>
		<div>
			<label for="creationDate"> <spring:message
					code="commission.form.creationDate" />
			</label>
			<form:input type="date" path="creationDate" />
			<form:errors path="creationDate" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>
