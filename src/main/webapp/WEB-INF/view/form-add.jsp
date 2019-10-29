<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Zapisz Pracownik</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style-form-add.css">
			
			<style><%@include file="/WEB-INF/css/style-form-add.css"%></style>
			<style><%@include file="/WEB-INF/css/add-customer-style.css"%></style>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>Zapisz Pracownika</h2>
		</div>
	</div>

	<div id="container">
		<h3>Zapisz Pracownika</h3>
	
		<form:form action="${pageContext.request.contextPath}/ksiegowosc/saveWorkers" modelAttribute="Workers" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="name" /></td>
					</tr>
				
					<tr>
						<td><label>Position:</label></td>
						<td><form:input path="Position" /></td>
					</tr>

					<tr>
						<td><label>Office:</label></td>
						<td><form:input path="office" /></td>
					</tr>
					<tr>
						<td><label>Age:</label></td>
						<td><form:input path="age" /></td>
					</tr>
					<tr>
						<td><label>Start :</label></td>
						<td><form:input path="start" /></td>
					</tr>
					<tr>
						<td><label>Salary:</label></td>
						<td><form:input path="salary" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/ksiegowosc">Back to List</a>
		</p>
	
	</div>

</body>

</html>


