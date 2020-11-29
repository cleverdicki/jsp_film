<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Our Film</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: yellow">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Films </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Films</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="alert alert-success" *ngIf='message'>{{message}}</div>

		<div class="container">
			<h3 class="text-center">List of Films</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add Film</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Distributor</th>
						<th>Genre</th>
						<th>Year</th>
						<th>Country</th>
						<th>Duration</th>
						<th>Trailer</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="film" items="${listFilm}">

						<tr>
							<td><c:out value="${film.id}" /></td>
							<td><c:out value="${film.name}" /></td>
							<td><c:out value="${film.distributor}" /></td>
							<td><c:out value="${film.genre}" /></td>
							<td><c:out value="${film.year}" /></td>
							<td><c:out value="${film.country}" /></td>
							<td><c:out value="${film.duration}" /></td>
							<td><c:out value="${film.trailer}" /></td>
							<td><a href="editFilm?id=<c:out value='${film.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteFilm?id=<c:out value='${film.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>