<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
			
	<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
			
	<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
	<spring:url value="/resources/css/home.css" var="home" />
	<link href="${home}" rel="stylesheet" />
	
	<title><spring:message code="commission.form.title" /></title>
</head>
<body>

	<nav class="navbar navbar-inverse"> </nav>

	<form:form action="${spring:mvcUrl('createCommission').build()}"
		method="post" commandName="commission">
		
			<div class = "wrapper">
				<label for="name" class="col-sm-2 control-label"> <spring:message code="commission.form.name" />
				</label>
				<div class="col-sm-10">
					<form:input path="name" class="form-control" placeholder="Nome"/>
					<form:errors path="name" />	
				</div>
			</div>
		
			<div class="wrapper">
				<label for="scope" class="col-sm-2 control-label"> <spring:message code="commission.form.scope" />
				</label>
				<div class="col-sm-10">
					<form:input path="scope" class="form-control" placeholder="0"/>
					<form:errors path="scope" />
				</div>
			</div>
		
		    <div class="wrapper">
		      <label for="minNumber" class="col-sm-2 control-label"> <spring:message code="commission.form.minNumber" />
		      </label>
				<div class="col-sm-2">
			        <form:input path="minNumber" class="form-control" placeholder="0"/>
			        <form:errors path="minNumber" />
			    </div>
		    </div>
	    
		    <div class="wrapper">
		      <label for="maxNumber" class="col-sm-2 control-label"> <spring:message code="commission.form.maxNumber" />
		      </label>
			      <div class="col-sm-2">
					    <form:input path="maxNumber" class="form-control" placeholder="0"/>
					    <form:errors path="maxNumber" />
				  </div>
		    </div>
		
			<div class="wrapper">
				<label for="creationDate" class="col-sm-2 control-label"> <spring:message
						code="commission.form.creationDate" />
				</label>
				<div class="control-label col-sm-2">
					<form:input type="date" path="creationDate" class="form-control" placeholder="0"/>
					<form:errors path="creationDate" />
				</div>
			</div>
			
			<div class="wrapper">
				<input type="submit" value="Enviar" class="btn btn-primary btn-md"/>
			</div>
		
	</form:form>
</body>
</html>
