<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>

	<title> <spring:message code="department.form.title" /> </title>

</head>

<header>
	
	<%@ include file="/WEB-INF/views/home/header.jsp" %>
	
</header>

<body>

	<form:form action="${spring:mvcUrl('createDepartment').build()}"
		method="post" commandName="department">
		<br/><br/><br/><br/><br/><br/><br/>
		<div class="container-fluid">
			<label for="name" class="col-sm-2"> <spring:message code="department.form.name" /> </label>
			
			<div class="col-sm-10">
				<form:input path="name" class="form-control" placeholder="Nome"/>
				<form:errors path="name" />
			</div>
			
		</div>
		<br/>
		<div class="container-fluid">
			<label for="center" class="col-sm-2"> <spring:message code="department.form.center" /> </label>
			
			<div class="col-sm-10">
				<form:input path="center" class="form-control" placeholder="Centro"/>
				<form:errors path="center" />
			</div>
		</div>
		
		<div class="center-bottom2">
			<input type="submit" value="Enviar"  class="btn btn-primary btn-md"/>
		</div>
		
	</form:form>
	
	
	<%@ include file="/WEB-INF/views/home/footer.jsp" %>
	
</body>
</html>
