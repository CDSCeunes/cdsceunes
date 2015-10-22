<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title><spring:message code="teacher.prefForm.title" /></title>
</head>
<body>
	<form:form action="${spring:mvcUrl('createPreference').build()}"
		method="put" commandName="teacher">
		<div>
			<label for="teacher"><spring:message
					code="teacher.prefForm.name" /></label>
			<form:select path="name">
				<form:option label="-- Selecione --" value="${null}" />
				<form:options items="${teacherList}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors path="name" />
		</div>
		<div>
			<label for="preferences"> <spring:message
					code="teacher.prefForm.preference" /></label>
			<c:set target="${preferences}" property="discipline"
				value="Discipline" />
			<div>
				<label for="discipline"><spring:message
						code="teacher.prefForm.discipline" /></label>
				<form:select path="${preferences}">
					<form:option label="-- Selecione --" value="${null}" />
					<form:options items="${disciplineList}" itemLabel="name"
						itemValue="id" />
				</form:select>
				<form:errors path="${preferences}" />
			</div>
		</div>

		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
</body>
</html>
