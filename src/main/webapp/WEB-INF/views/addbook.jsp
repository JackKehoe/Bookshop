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

<title>Add book</title>

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
		<div id="logbox">
			<form:form method="POST" modelAttribute="bookForm">
				<h2 class="form-signin-heading">Add book</h2>
				<spring:bind path="author">
					<div class="form-group">
						<form:input type="text" path="author" class="form-control"
							placeholder="Auhor" autofocus="true"></form:input>
						<form:errors path="author"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="title">
					<div class="form-group">
						<form:input type="text" path="title" class="form-control"
							placeholder="Title"></form:input>
						<form:errors path="title"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="price">
					<div class="form-group">
						<form:input type="number" path="price" class="form-control"
							placeholder="Price"></form:input>
						<form:errors path="price"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="genre">
					<div class="form-group">
						<form:input type="text" path="genre" class="form-control"
							placeholder="Genre"></form:input>
						<form:errors path="price"></form:errors>
					</div>
				</spring:bind>
				
				<spring:bind path="stockLevel">
					<div class="form-group">
						<form:input type="number" path="stockLevel" class="form-control"
							placeholder="Stock Level"></form:input>
						<form:errors path="stockLevel"></form:errors>
					</div>
				</spring:bind>

				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

			</form:form>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</html>