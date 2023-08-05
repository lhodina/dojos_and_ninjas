<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dojo Page</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css"/>
</head>
<body>
	<div class="container">
		<h1><c:out value="${dojo.name }" ></c:out> Location Ninjas</h1>
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Age</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ninja" items="${dojo.ninjas }">
					<tr>
						<td>${ninja.firstName }</td>
						<td>${ninja.lastName }</td>
						<td>${ninja.age }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>