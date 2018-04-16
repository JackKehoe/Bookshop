<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml"
	xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html" />

<title>Login</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"	rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${contextPath}/homepage">BookStore</a>
			</div>
		</div>

	</nav>

	<div class="container">

		<div class="row main">
			<div class="col-lg-5 description">
				<h1>Book Store</h1>
				<p class="lead">Welcome!</p>
			</div>

			<div class="col-lg-7">
				<div id="logbox">
					<form:form method="POST" modelAttribute="userForm"
						class="form-signin" action="${contextPath}/register">
						<h2 class="form-signin-heading">Create account</h2>
						<spring:bind path="username">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="username" class="form-control"
									placeholder="Username" autofocus="true"></form:input>
								<form:errors path="username"></form:errors>
							</div>
						</spring:bind>

						<spring:bind path="password">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="password" path="password" class="form-control"
									placeholder="Password"></form:input>
								<form:errors path="password"></form:errors>
							</div>
						</spring:bind>

						<spring:bind path="passwordConfirm">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="password" path="passwordConfirm"
									class="form-control" placeholder="Confirm your password"></form:input>
								<form:errors path="passwordConfirm"></form:errors>
							</div>
						</spring:bind>

						<spring:bind path="admin">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:radiobutton path="admin" value="true" />
								Administrator 
								<form:radiobutton path="admin" value="false" />
								Customer
							</div>
						</spring:bind>

						<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
						
						<h4 class="text-center"><a href="${contextPath}/login">Login</a></h4>

					</form:form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>