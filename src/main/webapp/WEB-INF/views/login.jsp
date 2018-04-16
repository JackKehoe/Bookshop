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

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

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
				<a class="navbar-brand" href="${contextPath}/welcome">BookStore</a>
			</div>
			
				<form class="navbar-form navbar-right" method="POST"
					action="${contextPath}/login" class="form-group">
					<div class="form-group ${error != null ? 'has-error' : ''}">
					<span>${error}</span>
						<span>${message}</span> <input name="username" type="text"
							class="form-control" placeholder="Username" autofocus="true"/>
					</div>
					<div class="form-group ${error != null ? 'has-error' : ''}">

						<input name="password" type="password" class="form-control"
							placeholder="Password" />  <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
					<button type="submit">Log In</button>
				</form>
			</div>

	</nav>

	<div class="container">

		<div class="row main">
			<div class="col-lg-5 description">
				<h1>Online Book Shop</h1>

			</div>

			<div class="col-lg-7">
				<div id="logbox">
					
					<h4 class="text-center"><a href="${contextPath}/register">Create an account</a></h4>
					
				</div>
			</div>
		</div>
	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>