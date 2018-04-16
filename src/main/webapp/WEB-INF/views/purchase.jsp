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

<title>Purchase</title>

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
		<div class="col-lg-3"></div>
		<div class="col-lg-4">

			<h3>Purchase: ${purchase.id}</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					Customer: ${purchase.user.username}<br> Date: ${purchase.date}<br>
					Books<br>
					<c:forEach items="${books}" var="book">
								${book.title}<br>
								${book.author}<br>
								${book.genre}<br>
								${book.price}<br>
					</c:forEach>

				</div>

			</div>
		</div>
		<div class="col-lg-5"></div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</html>