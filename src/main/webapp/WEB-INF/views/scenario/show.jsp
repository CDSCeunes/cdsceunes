<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Scenario:  ${scenario.semester}</h1>
<c:forEach items="${scenario.distributionList}" var="distribution">
	${distribution.teacher.name} - ${distribution.discipline.name }<br>
</c:forEach>
