<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	
	<title><spring:message code="teacher.form.title" /></title>

</head>

<header>

	<%@ include file="/WEB-INF/views/home/header.jsp" %>

</header>

<body>

	<form:form action="${spring:mvcUrl('createTeacher').build()}"
		method="post" commandName="teacher">
		<br/><br/><br/><br/><br/><br/><br/>
		<div class="container-fluid">
			<label for="name" class="col-sm-2"> <spring:message code="teacher.form.name" />
			</label>
			<div class="col-sm-10">
				<form:input path="name" class="form-control" placeholder="Nome"/>
				<form:errors path="name" />
			</div>
		</div>
		<br/>
		<div class="container-fluid">
			<label for="login" class="col-sm-2"> <spring:message code="teacher.form.login" />
			</label>
			<div class="col-sm-10">
				<form:input path="login" class="form-control" placeholder="Login"/>
				<form:errors path="login" />
			</div>
		</div>
		<br/>
		<div class="container-fluid">
			<label for="admissionDate" class="col-sm-2"> <spring:message
					code="teacher.form.admissionDate" />
			</label>
			<div class="col-sm-10">
				<form:input type="date" path="admissionDate" class="form-control" placeholder="Data"/>
				<form:errors path="admissionDate" />
			</div>
		</div>
		<br/>
		<div class="container-fluid">
			<label for="department" class="col-sm-2"><spring:message code="teacher.form.department" /></label>
			<div class="col-sm-10">
				<form:select path="department" class="form-control">
					<form:option label="-- Selecione --" value="${null}" />
					<form:options items="${departmentList}" itemLabel="name"
						itemValue="id" />
				</form:select>
				<form:errors path="department" />
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
