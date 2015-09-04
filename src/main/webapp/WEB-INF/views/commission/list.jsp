<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title><spring:message code="commission.list.title" /></title>
</head>
<body>
	${sucess}
	<spring:hasBindErrors name="commission">
		<ul>
			<c:forEach var="error" items="${errors.allErrors }">
				<li>${error.code }-${error.field}</li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<table>
		<tr>
			<td><spring:message code="commission.list.name" /></td>
			<td><spring:message code="commission.list.scope" /></td>
			<td><spring:message code="commission.list.creationDate" /></td>
			<td><spring:message code="commission.list.minNumber" /></td>
			<td><spring:message code="commission.list.maxNumber" /></td>
		</tr>
		<c:forEach items="${commissions}" var="commission">
			<tr>
				<td>${commission.name}</td>
				<td>${commission.scope}</td>
				<td>${commission.creationDate}</td>
				<td>${commission.minNumber}</td>
				<td>${commission.maxNumber}</td>
			</tr>
		</c:forEach>
	</table>
	<footer>
		<a href="${spring:mvcUrl("addCommission").build()}"> <spring:message
				code="commission.list.footer" /></a>
	</footer>
</body>
</html>
