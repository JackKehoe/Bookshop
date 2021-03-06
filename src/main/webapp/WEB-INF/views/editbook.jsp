<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Edit Book</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">


<form id="logoutForm" method="POST" action="${contextPath}/logout">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<div class="col-lg-6">
			<div id="logbox">
				<form:form method="POST" modelAttribute="bookForm">
					<h2 class="form-signin-heading">Update</h2>
					<spring:bind path="author">
						Author
							<div class="form-group">
							<form:input type="text" path="author" class="form-control"
								placeholder="${bookForm.author}" autofocus="true"></form:input>
							<form:errors path="author"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="title">
						Title
							<div class="form-group">
							<form:input type="text" path="title" class="form-control"
								placeholder="${bookForm.title}"></form:input>
							<form:errors path="title"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="price">
						Price
							<div class="form-group">
							<form:input type="number" path="price" class="form-control"
								placeholder="${bookForm.price}"></form:input>
							<form:errors path="price"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="genre">
						Genre
							<div class="form-group">
							<form:input type="text" path="genre" class="form-control"
								placeholder="${bookForm.genre}"></form:input>
							<form:errors path="genre"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="stockLevel">
						Stock Level
							<div class="form-group">
							<form:input type="number" path="stockLevel" class="form-control"
								placeholder="${bookForm.stockLevel}"></form:input>
							<form:errors path="stockLevel"></form:errors>
						</div>
					</spring:bind>


					<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>

				</form:form>
				<form enctype="multipart/form-data" method="post"
					action="${contextPath}/admin/add/${bookForm.id}/profilepic?${_csrf.parameterName}=${_csrf.token}">
					<input type="file" name="image">
					<button type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-floppy-disk"></span> Save
					</button>
				</form>
			</div>
		</div>
		<div class="col-lg-2"></div>
		<div class="col-lg-4">
			<H2>Current Book Details</H2>
			<br>
			<H4>
				<b>Author:</b>${bookForm.author}<br> <br>
				<b>Title:</b>${bookForm.title}<br> <br>
				<b>Price:</b>&euro;${bookForm.price}<br> <br>
				<b>Genre:</b>${bookForm.genre}<br> <br>
				<b>Stock Level:</b>${bookForm.stockLevel}</H4>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</html>