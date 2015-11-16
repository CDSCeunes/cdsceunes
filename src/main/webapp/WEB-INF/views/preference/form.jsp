<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>

	<title><spring:message code="preference.form.title" /></title>

</head>

<header>

	<%@ include file="/WEB-INF/views/home/header.jsp" %>

</header>

<body>
	<form:form action="${spring:mvcUrl('createPreferences').build()}"
		method="post" commandName="preferences">
		<div>
			<label for="teacher"> <spring:message
					code="preference.form.teacher" />
			</label>
			<form:select path="teacher">
				<form:option label="-- Selecione --" value="${null}" />
				<form:options items="${teacherList}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors path="teacher" />
		</div>
		<div>
			<label for="discipline"> <spring:message
					code="preference.form.discipline" />
			</label>
			<form:select path="discipline">
				<form:option label="-- Selecione --" value="${null}" />
				<form:options items="${disciplineList}" itemLabel="name"
					itemValue="id" />
			</form:select>
			<form:errors path="discipline" />
		</div>
		<div>
			<label for="value"> <spring:message
					code="preference.form.interest" />
			</label>
			<form:select path="value">
				<form:option label="-- Selecione --" value="${null}" />
        <form:options items="${valueList}"/>
			</form:select>
			<form:errors path="value" />
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	</form:form>
	<br><br><br><br><br><br><br><br>
	<%@ include file="/WEB-INF/views/home/footer.jsp" %>
	
</body>
</html>
