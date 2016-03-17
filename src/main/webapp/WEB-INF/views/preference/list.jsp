<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title><spring:message code="preference.list.title" /></title>
</head>
<body>
	${sucess}
	<spring:hasBindErrors name="preference">
		<ul>
			<c:forEach var="error" items="${errors.allErrors }">
				<li>${error.code }-${error.field}</li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<table>
		<tr>
			<td><spring:message code="preference.list.teacher" /></td>
			<td><spring:message code="preference.list.discipline" /></td>
			<td><spring:message code="preference.list.interest" /></td>
		</tr>
		<c:forEach items="${preferences}" var="preferences">
			<tr>
				<td>${preferences.teacher}</td>
				<td>${preferences.discipline}</td>
				<td>${preferences.value}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
