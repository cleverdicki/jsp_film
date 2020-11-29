<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Comment Section</title>
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
				<a href="https://www.javaguides.net" class="navbar-brand"> Comment Section </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Comments</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="film-list"
					class="nav-link">Film</a></li>
			</ul>
			<button><a href="logout">Log Out</a></button>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${comment != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${comment == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${comment != null}">
            			Edit Comment
            		</c:if>
						<c:if test="${comment == null}">
            			Add New Comment
            		</c:if>
					</h2>
				</caption>

				<c:if test="${comment != null}">
					<input type="hidden" name="id_comment" value="<c:out value='${comment.id_comment}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Film's Name</label> <input type="text"
						value="<c:out value='${comment.name_film}' />" class="form-control"
						name="name_film" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Film's Distributor</label> <input type="text"
						value="<c:out value='${comment.distributor_film}' />" class="form-control"
						name="distributor_film">
				</fieldset>

				<fieldset class="form-group">
					<label>Comment</label> <input type="text"
						value="<c:out value='${comment.comment_film}' />" class="form-control"
						name="comment_film">
                </fieldset>
                
                <fieldset class="form-group">
					<label>Date</label> <input type="text"
						value="<c:out value='${comment.date_comment}' />" class="form-control"
						name="date_comment">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>