<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title> <spring:message code="teacher.list.title" /> </title>
</head>
<body>
	${sucess}
	<spring:hasBindErrors name="teacher">
		<ul>
			<c:forEach var="error" items="${errors.allErrors }">
				<li>${error.code }-${error.field}</li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<table>
		<tr>
			<td> <spring:message code="teacher.list.name" /> </td>
			<td> <spring:message code="teacher.list.login" /> </td>
			<td> <spring:message code="teacher.list.admissionDate" /> </td>
		</tr>
		<c:forEach items="${teachers}" var="teacher">
			<tr>
				<td>${teacher.name}</td>
				<td>${teacher.login}</td>
				<td>${teacher.admissionDate}</td>
			</tr>
		</c:forEach>
	</table>
	<footer>
		<a href="${spring:mvcUrl("addTeacher").build()}"> <spring:message code="teacher.list.footer"/></a>
	</footer>
</body>
</html>
