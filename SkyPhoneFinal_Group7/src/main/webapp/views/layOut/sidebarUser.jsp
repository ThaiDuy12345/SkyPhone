<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="site-navbar site-navbar-target js-sticky-header "
		style="color: white; background-color: #1E90FF;">
		<div class="container ">
			<div class="row align-items-center ">
				<form class="col-12">
					<nav class="site-navigation text-right " role="navigation ">
						<div class="container ">
							<ul class="site-menu main-menu js-clone-nav d-none d-lg-block ">
								<li><a href="/skyPhoneUser" class="nav-link text-light ">Trang
										chủ</a></li>
								<c:forEach var="item" items="${brands}">
								<li><a href="/skyPhoneUser/ChooseBrand/${item.id}" class="nav-link text-light " style="background:none; border:none">${item.ten_nhan_hang}</a></li>
								</c:forEach>
							</ul>
						</div>
					</nav>
				</form>
			</div>
		</div>
	</div>
</body>
</html>