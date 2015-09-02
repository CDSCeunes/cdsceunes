<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title> <spring:message code="department.list.title" /> </title>
</head>
<body>
	${sucess}
	<spring:hasBindErrors name="department">
		<ul>
			<c:forEach var="error" items="${errors.allErrors }">
				<li>${error.code }-${error.field}</li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<table>
		<tr>
			<td> <spring:message code="department.list.name" /> </td>
			<td> <spring:message code="department.list.center" /> </td>
		</tr>
	</table>
	<footer>
		<a href="${spring:mvcUrl("addDepartment").build()}"> <spring:message code="department.list.footer"/></a>
	</footer>
</body>
</html>
