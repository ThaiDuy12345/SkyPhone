<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>Hóa đơn page</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel="shortcut icon" href="/images/logoJava5-removeBg.png"
	type="image/x-icon">
<link
	href="https://fonts.googleapis.com/css?family=Quicksand:400,600,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="/fonts/icomoon/style.css">

<link rel="stylesheet" href="/css/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/css/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="/css/style.css">
<!--Style phone-->
<link rel="stylesheet" href="/css/phone.css">
<link rel="stylesheet" href="/css/footer.css">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<title>SkyPhone</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
</head>

<body ng-app="">
	<script>
		if ("${message}" != "") {
			alert("${message}")
		}
	</script>
	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close mt-3">
				<span class="icon-close2 js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>
	<!-- .site-mobile-menu -->

	<div class="site-navbar-wrap">
		<!-- OnNav -->
		<jsp:include page="/views/layOut/onNavUser.jsp" />
		<!-- Nav -->
		<jsp:include page="/views/layOut/navUser.jsp" />
		<!-- SideBar for user -->
		<jsp:include page="/views/layOut/sidebarUser.jsp" />
		<!-- Item  or NotItem-->
		<jsp:include page="${page}" />
	</div>
</body>
<script src="/js/jquery-3.3.1.min.js "></script>
<script src="/js/popper.min.js "></script>
<script src="/js/bootstrap.min.js "></script>
<script src="/js/jquery.sticky.js "></script>
<script src="/js/main.js "></script>

</html>
