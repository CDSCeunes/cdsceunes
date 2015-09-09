<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title><spring:message code="discipline.list.title" /></title>
</head>
<body>
	${sucess}
	<spring:hasBindErrors name="discipline">
		<ul>
			<c:forEach var="error" items="${errors.allErrors }">
				<li>${error.code }-${error.field}</li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<table>
		<tr>
			<td><spring:message code="discipline.list.name" /></td>
			<td><spring:message code="discipline.list.course" /></td>
			<td><spring:message code="discipline.list.semesters" /></td>
			<td><spring:message code="discipline.list.teoricLoad" /></td>
			<td><spring:message code="discipline.list.exerciseLoad" /></td>
			<td><spring:message code="discipline.list.labLoad" /></td>
		</tr>
		<c:forEach items="${disciplines}" var="discipline">
			<tr>
				<td>${discipline.name}</td>
				<td>${discipline.course}</td>
				<c:forEach items="${discipline.semesters}" var="semester">
					<td>${semester}</td>
				</c:forEach>
				<td>${discipline.teoricLoad}</td>
				<td>${discipline.exerciseLoad}</td>
				<td>${discipline.labLoad}</td>
			</tr>
		</c:forEach>
	</table>
	<footer>
		<a href="${spring:mvcUrl("addDiscipline").build()}"> <spring:message
				code="discipline.list.footer" /></a>
	</footer>
</body>
</html>
