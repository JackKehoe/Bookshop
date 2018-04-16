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

<title>PurchaseHistory</title>

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

			<h3>Purchase History</h3>


			<c:forEach items="${purchaseList}" var="purchase">
				<div class="panel panel-default">
					<div class="panel-body">
						<c:if test="${pageContext.request.isUserInRole('ROLE_CUSTOMER')}">
							<b><a href=${contextPath}/customer/purchase/${purchase.id}>Purchase:
									${purchase.id}</a></b>
							<br>
							<b>Customer:</b>${purchase.user.username}<br>
							<b>Date:</b>${purchase.date}<br>
						</c:if>
						<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
							<b><a href=${contextPath}/admin/purchase/${purchase.id}>Purchase:
									${purchase.id}</a></b>
							<br><
							<b>Customer:</b>${purchase.user.username}<br>
							<b>Date:</b>${purchase.date}<br>
						</c:if>
						<div id="mainwrap"></div>
					</div>

				</div>
			</c:forEach>
		</div>
		<div class="col-lg-5"></div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</html>