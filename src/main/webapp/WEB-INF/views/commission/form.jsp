<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	
	<title><spring:message code="commission.form.title" /></title>

</head>

<header>

	<%@ include file="/WEB-INF/views/home/header.jsp" %>

</header>

<body>

	<form:form action="${spring:mvcUrl('createCommission').build()}"
		method="post" commandName="commission">
			<br/><br/><br/><br/><br/><br/><br/>
			<div class = "container-fluid">
				<label for="name" class="col-sm-2"> <spring:message code="commission.form.name" />
				</label>
				<div class="col-sm-10">
					<form:input path="name" class="form-control" placeholder="Nome"/>
					<form:errors path="name" />	
				</div>
			</div>
			<br/>
			<div class="container-fluid">
				<label for="scope" class="col-sm-2"> <spring:message code="commission.form.scope" />
				</label>
				<div class="col-sm-10">
					<form:input path="scope" class="form-control" placeholder="0"/>
					<form:errors path="scope" />
				</div>
			</div>
			<br/>
		    <div class="container-fluid">
		      <label for="minNumber" class="col-sm-2"> <spring:message code="commission.form.minNumber" />
		      </label>
				<div class="col-sm-10">
			        <form:input path="minNumber" class="form-control" placeholder="0"/>
			        <form:errors path="minNumber" />
			    </div>
		    </div>
	    	<br/>
		    <div class="container-fluid">
		      <label for="maxNumber" class="col-sm-2"> <spring:message code="commission.form.maxNumber" />
		      </label>
			      <div class="col-sm-10">
					    <form:input path="maxNumber" class="form-control" placeholder="0"/>
					    <form:errors path="maxNumber" />
				  </div>
		    </div>
			<br/>
			<div class="container-fluid">
				<label for="creationDate" class="col-sm-2"> <spring:message
						code="commission.form.creationDate" />
				</label>
				<div class="control-label col-sm-10">
					<form:input type="date" path="creationDate" class="form-control" placeholder="0"/>
					<form:errors path="creationDate" />
				</div>
			</div>
			
			<div class="center-bottom2">
				<input type="submit" value="Enviar" class="btn btn-primary btn-md"/>
			</div>
		
	</form:form>
	<br><br><br><br><br><br><br><br>
	<%@ include file="/WEB-INF/views/home/footer.jsp" %>
</body>

</html>
