<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	
	<title><spring:message code="position.form.title" /></title>
	
</head>

<header>

	<%@ include file="/WEB-INF/views/home/header.jsp" %>

</header>

<body>
	
	<form:form  action="${spring:mvcUrl('createPosition').build()}"
		method="post" commandName="position" >
		<br/><br/><br/><br/><br/><br/><br/>
		<div class="container-fluid">
			<label for="name" class="col-sm-2 control-label"> <spring:message code="position.form.name" />
			</label>
			<div class="col-sm-10">
				<form:input path="name" class="form-control" placeholder="Nome do cargo" />
				<form:errors path="name" />
			</div>
		</div>
		<br/>
		<div class="container-fluid">
			<label for="minWorkload" class="col-sm-2 control-label"> <spring:message
					code="position.form.minWorkLoad" />
			</label>
			<div class="col-sm-10">
				<form:input path="minWorkload" class="form-control" placeholder="Número de horas"/>
				<form:errors path="minWorkload" />
			</div>
		</div>
		<br/>
		<div class="container-fluid">
			<div class="span8">
				<label for="maxWorkload" class="col-sm-2 control-label"><spring:message
						code="position.form.maxWorkLoad" /> </label>
				<div class="col-sm-10">
					<form:input path="maxWorkload" class="form-control" placeholder="Número de horas"/>
					<form:errors path="maxWorkload" />
				</div>
			</div>
				
		</div>
		<br/>
		<div class="container-fluid">
			<label for="commission" class="col-sm-2 control-label"> <spring:message
					code="position.form.comission" />
			</label>
			<div class="col-sm-10 control-label">
				<form:select path="commission" class="form-control">
					<form:option label="-- Selecione --" value="${null}" />
					<form:options items="${commissionList}" itemLabel="name"
						itemValue="id" />
				</form:select>
				<form:errors path="commission" />
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
