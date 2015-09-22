<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title><spring:message code="class.form.title" /></title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createClasses').build()}"
		method="post" commandName="offeredClass">
		<div>
			<label for="year"> <spring:message code="class.form.year" />
			</label>
			
			<form:input path="year" />
			<form:errors path="year" />
		</div>
		<div>
			<label for="semester"> <spring:message
					code="class.form.semester" />
			</label>
			<form:input path="semester" />
			<form:errors path="semester" />
		</div>
		<div>
			<label for="discipline"> <spring:message
					code="class.form.discipline" />
			</label>
			<form:select path="discipline">
				<form:option label="-- Selecione --" value="${null}" />
				<form:options items="${disciplineList}" itemLabel="name"
					itemValue="id" />
			</form:select>
			<form:errors path="discipline" />
		</div>
		<div>
			<label for="teachers"> <spring:message
					code="class.form.teachers" />
			</label>
			<form:select path="teachers">
				<form:option label="-- Selecione --" value="${null}" />
				<form:options items="${teachersList}" itemLabel="name"
					itemValue="id" />
			</form:select>
			<form:errors path="teachers" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>
