<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CDS Ceunes</title>
  <spring:url value="resources/css/vendor/bootstrap.css" var="bootstrap" />
  <link rel="stylesheet" href="${bootstrap}">
</head>
<body>
	<div id="app-container">
		<div id="header-container"></div>
		<div id="main-container">
			<p>CDS Ceunes</p>
		</div>
		<div id="footer-container"></div>
	</div>
  <spring:url value="resources/js/vendor/require.js" var="require" /><spring:url value="resources/js/main.js" var="main" />
  <script type="text/javascript" data-main="${main}" src="${require}"></script>
</body>
</html>