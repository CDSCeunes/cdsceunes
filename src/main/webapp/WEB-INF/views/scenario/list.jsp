<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Lista de Scenarios</h1>
<c:forEach items="${scenarios}" var="scenario" >
	<a href="${spring:mvcUrl('showScenario').build()}${scenario.id}">${scenario.id} -> ${scenario.semester }</a><br>
</c:forEach>

