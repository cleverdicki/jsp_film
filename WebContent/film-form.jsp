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
			style="background-color: gray">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Add Film </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Films</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${film != null}">
					<form action="updateFilm" method="post">
				</c:if>
				<c:if test="${film == null}">
					<form action="insertFilm" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${film != null}">
            			Edit Film
            		</c:if>
						<c:if test="${film == null}">
            			Add New Film
            		</c:if>
					</h2>
				</caption>

				<c:if test="${film != null}">
					<input type="hidden" name="id" value="<c:out value='${film.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Film's Name</label> <input type="text"
						value="<c:out value='${film.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Film's Distributor</label> <input type="text"
						value="<c:out value='${film.distributor}' />" class="form-control"
						name="distributor">
				</fieldset>

				<fieldset class="form-group">
					<label>Film's Genre</label> <input type="text"
						value="<c:out value='${film.genre}' />" class="form-control"
						name="genre">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Film's Year</label> <input type="text"
						value="<c:out value='${film.year}' />" class="form-control"
						name="year">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Film's Country</label> <input type="text"
						value="<c:out value='${film.country}' />" class="form-control"
						name="country">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Film's Duration</label> <input type="text"
						value="<c:out value='${film.duration}' />" class="form-control"
						name="duration">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Film's Trailer</label> <input type="text"
						value="<c:out value='${film.trailer}' />" class="form-control"
						name="trailer">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>