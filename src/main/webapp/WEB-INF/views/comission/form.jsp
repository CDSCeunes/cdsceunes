<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title><spring:message code="comission.form.title" /></title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createComission').build()}"
		method="post" commandName="comission">
		<div>
			<label for="name"> <spring:message code="comission.form.name" />
			</label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<label for="scope"> <spring:message code="comission.form.scope" />
			</label>
			<form:input path="scope" />
			<form:errors path="scope" />
		</div>
    <div>
      <label for="minNumber"> <spring:message code="comission.form.minNumber" />
      </label>
      <form:input path="minNumber" />
      <form:errors path="minNumber" />
    </div>
    <div>
      <label for="maxNumber"> <spring:message code="comission.form.maxNumber" />
      </label>
      <form:input path="maxNumber" />
      <form:errors path="maxNumber" />
    </div>
		<div>
			<label for="admissionDate"> <spring:message
					code="comission.form.admissionDate" />
			</label>
			<form:input type="date" path="admissionDate" />
			<form:errors path="admissionDate" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>
