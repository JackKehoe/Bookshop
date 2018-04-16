<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Book</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<form id="logoutForm" method="POST" action="${contextPath}/logout">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<div class="row">

		<div class="col-lg-3">
			<div id="logbox"></div>

		</div>

		<div class="col-md-4">
			<div id="logbox">
				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-body-center">
							<img src="/resources/images/${book.image}" height="100px"
								width="100px"> <br> <b><a
								href=${contextPath}/admin/book/${book.id}>${book.title}
									${postText.user.lname}</a></b> by ${book.author} <br> <b>Price:
							</b>&euro;${book.price} <br> <b>Genre: </b>${book.genre} <br>
							<b> <c:if test="${book.stockLevel == 0}">Out of stock</c:if></b>
							<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
								<br>
								<a href="${contextPath}/admin/book/edit/${book.id}">Edit</a>
							</c:if>
						</div>
					</div>

					<c:if test="${pageContext.request.isUserInRole('ROLE_CUSTOMER')}">
						<c:if test="${book.stockLevel > 0}">
							<form:form
								action="${contextPath}/customer/book/addtocart/${book.id}"
								method="post">
								<button name="${_csrf.parameterName}" value="${_csrf.token}"
									type="submit" class="btn btn-success btn-sm">Add To
									Cart</button>
							</form:form>
						</c:if>
					</c:if>
					<div class="col-md-7">
						<c:if test="${pageContext.request.isUserInRole('ROLE_CUSTOMER')}">
							<form:form commandName="commentForm"
								action="${contextPath}/customer/comment/${book.id}"
								method="POST">
								<form:input path="content" placeholder="Add comment"
									cssClass="form-control" />
								<input type="submit" class="btn btn-failure btn-sm" value="Post" />
							</form:form>
						</c:if>
						&nbsp;
						<c:forEach items="${comments}" var="comment">
							<div class="panel panel-default">
								<div class="panel-body">
									<b>${comment.user.username} commented:</b> ${comment.content}
								</div>
								<div class="panel-footer">${comment.timestamp}</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-md-7"></div>
		</div>

		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>