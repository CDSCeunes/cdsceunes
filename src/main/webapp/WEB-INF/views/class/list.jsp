<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title><spring:message code="class.list.title" /></title>
</head>
<body>
	${sucess}
	<spring:hasBindErrors name="offeredClass">
		<ul>
			<c:forEach var="error" items="${errors.allErrors }">
				<li>${error.code }-${error.field}</li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<table>
		<tr>
			<td><spring:message code="class.list.year" /></td>
			<td><spring:message code="class.list.semester" /></td>
			<td><spring:message code="class.list.discipline" /></td>
      <td><spring:message code="class.list.teachers" /></td>
		</tr>
    <c:forEach items="${teachers}" var="teachers">
			<tr>
				<td>${commission.name}</td>
			</tr>
		</c:forEach>
	</table>
	<footer>
		<a href="${spring:mvcUrl("addClasses").build()}"> <spring:message
				code="class.list.footer" /></a>
	</footer>
</body>
</html>
