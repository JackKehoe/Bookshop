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

<title>Homepage</title> `
<form id="logoutForm" method="POST" action="${contextPath}/logout">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<div class="col-lg-3"></div>
		<div class="col-lg-4">
			<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
			Sort By:
				<a class="dropdown-item" 
					href="${contextPath}/admin/homepage/name">Name</a>
				<a class="dropdown-item"
					href="${contextPath}/admin/homepage/author">Author</a>
				<a class="dropdown-item" 
					href="${contextPath}/admin/homepage/genre">Genre</a>

			</c:if>

			<c:if test="${pageContext.request.isUserInRole('ROLE_CUSTOMER')}">
		Sort By:
				<a class="dropdown-item"
					href="${contextPath}/customer/homepage/name">Name</a>
				<a class="dropdown-item"
					href="${contextPath}/customer/homepage/author">Author</a>
				<a class="dropdown-item"
					href="${contextPath}/customer/homepage/genre">Genre</a>

			</c:if>

			<c:if test="${pageContext.request.isUserInRole('ROLE_CUSTOMER')}">
				<c:forEach items="${bookList}" var="book">
					<div class="panel panel-default">
						<div class="panel-body-center">
							<img src="/resources/images/${book.image}" height="100px"
								width="100px"><br> <b><a
								href=${contextPath}/customer/book/${book.id}>${book.title}</a></b><br>
							<b>Author:</b>${book.author}<br>
							<b>Genre:</b>${book.genre}<br>
							<b>Price:</b> &euro;${book.price}<br>
							<div id="mainwrap"></div>
						</div>
					</div>

				</c:forEach>
			</c:if>
			<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
				<c:forEach items="${bookList}" var="book">
					<div class="panel panel-default">
						<div class="panel-body-center">
							<img src="/resources/images/${book.image}" height="100px"
								width="100px"><br> <b><a
								href=${contextPath}/admin/book/${book.id}>${book.title}</a></b><br>

							<b>Author:</b>${book.author}<br>
							<b>Genre:</b>${book.genre}<br>
							<b>Price:</b> &euro;${book.price}<br>
							<div id="mainwrap"></div>
						</div>

					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="col-lg-5"></div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</html>