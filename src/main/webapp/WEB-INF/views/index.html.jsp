<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CDS Ceunes</title>
<!--<spring:url value="resources/css/vendor/bootstrap.css" var="bootstrap" />
<link rel="stylesheet" href="${bootstrap}"> -->
<spring:url value="resources/css/vendor/foundation.min.css"
	var="foundation" />
<link rel="stylesheet" href="${foundation}">
<spring:url value="resources/css/app.css" var="appcss" />
<link rel="stylesheet" href="${appcss}">
<spring:url value="resources/css/font-awesome.min.css" var="icons" />
<link rel="stylesheet" href="${icons}">
<spring:url value="resources/css/vendor/jquery-ui.css" var="jqueryui" />
<link rel="stylesheet" href="${jqueryui}">
</head>
<body>
	<div id="app-container" class="container-fluid">
		<div id="header-container"></div>
		<div id="main-container">
			<p>CDS Ceunes</p>
		</div>
		<div id="dialog-container"></div>
		<div id="footer-container"></div>
	</div>
	<spring:url value="resources/js/vendor/require.js" var="require" />
	<spring:url value="resources/js/main.js" var="main" />
	<script type="text/javascript" data-main="${main}" src="${require}"></script>
</body>
</html>