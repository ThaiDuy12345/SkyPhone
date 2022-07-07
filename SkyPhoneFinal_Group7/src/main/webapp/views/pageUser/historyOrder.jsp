<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SkyPhone - History Order</title>
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
<link rel="stylesheet" href="/css/modal.css">
<!--Style phone-->
<link rel="stylesheet" href="/css/phone.css">
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/history.css">
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
	<!-- Modal -->
	<jsp:include page="/views/pageUser/modalHistory.jsp" />
	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close mt-3">
				<span class="icon-close2 js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>

	<div class="site-navbar-wrap">
		<!-- OnNav -->
		<jsp:include page="/views/layOut/onNavUser.jsp" />
		<!-- Nav -->
		<jsp:include page="/views/layOut/navUser.jsp" />
		<!-- SideBar for user -->
		<jsp:include page="/views/layOut/sidebarUser.jsp" />

		<div class="super_container bannerChu">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xl-3 col-lg-3">
						<ul>
							<a href="/user/history/?id=${sdtHistory}" class="text-dark">
								<li style="list-style-type: none;" class="list-order ${list}"><i
									class="fa fa-list-alt mr-2" aria-hidden="true"
									style="font-size: 1rem;"></i> Danh sách đơn hàng đã mua</li>
							</a>
							<a href="/history/proFile" class="text-dark">
								<li style="list-style-type: none;" class="list-order ${profile}"><i
									class="fa fa-user mr-2" aria-hidden="true"
									style="font-size: 1rem;"></i> Thông tin cá nhân và sổ địa chỉ</li>
							</a>

						</ul>
					</div>
					<div class="col-xl-9 col-md-9">
						<c:forEach var="itemN" items="${HD}" end='0'>
							<div class="row">
								<div class="col-lg-8">
									<p class="text-dark">
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageUser/historyOrder.jsp
										Chào <b class="font-weight-bold">${itemN.ten_nguoi_nhan} -
											${itemN.sdt_nguoi_nhan}</b>
=======
										Chào <b class="font-weight-bold">${itemN.ten_nguoi_nhan}
											- ${itemN.sdt_nguoi_nhan}</b>
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageUser/historyOrder.jsp
									</p>
								</div>
								<div class="row sunRight col-lg-4">
									<a href="" class="text-primary"><i class="fa fa-comments"
										aria-hidden="true"></i> Phản hồi, góp ý</a>
									<div class="space"></div>
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageUser/historyOrder.jsp
									<a href="/SkyPhoneUser/logout" class="text-primary font-weight-bold">Thoát tài
=======
									<a href="" class="text-primary font-weight-bold">Thoát tài
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageUser/historyOrder.jsp
										khoản</a>
								</div>
							</div>
						</c:forEach>

						<div class="card" style="width: 65rem;">
							<!-- Page inclue -->
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageUser/historyOrder.jsp
							<jsp:include page="${page}" />
=======
							<jsp:include page="/views/pageUser/history.jsp" />
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageUser/historyOrder.jsp
							<!-- End page -->
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Footer -->
		<jsp:include page="/views/layOut/footer.jsp" />
	</div>


	<script src="/js/jquery-3.3.1.min.js "></script>
	<script src="/js/popper.min.js "></script>
	<script src="/js/bootstrap.min.js "></script>
	<script src="/js/jquery.sticky.js "></script>
	<script src="/js/main.js "></script>
</body>
</html>