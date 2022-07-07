<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<!-- Modal -->
	<jsp:include page="/views/pageUser/modalHistory.jsp" />
	<nav class="navbar navbar-expand-lg navbar-light"
		style="color: white; background-color: #1E90FF;">
		<div class="col-3 site-navbar">
			<h1 class="my-0 site-logo">
				<a href="/skyPhoneUser">SkyPhone</a>
			</h1>
		</div>
		<div class="">
			<div class="d-inline-block d-lg-none ml-md-0 mr-auto py-3 ">
				<a href="# " class="site-menu-toggle js-menu-toggle text-white "><span
					class="icon-menu h3 "></span></a>
			</div>
		</div>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<div class="row">
				<form action="/skyPhoneUser" class="form-inline my-2 my-lg-0 pl-5">
					<input class="form-control mr-sm-2" style="width: 350px;"
						type="search" placeholder="Bạn tìm gì ?" name="KeySearch"
						aria-label="Search">
					<button class="btn btn-outline-light my-2 my-sm-0">
						<i class="fa fa-search" aria-hidden="true"></i>
					</button>
				</form>
				<ul class="navbar-nav mr-auto ml-5">
					<li class="nav-item mt-1"><c:choose>
							<c:when test="${sdtHistory==null}">
								<a class="btn btn-light" data-toggle="modal"
									data-target="#exampleModalCenter"
									style="width: 100px; font-size: 12px; height: 55px; color: black;">
									Lịch sử đơn <br> hàng
								</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-light" href="/user/history/?id=${sdtHistory}"
									style="width: 100px; font-size: 12px; height: 55px; color: black;">
									Lịch sử đơn <br> hàng
								</a>
							</c:otherwise>
						</c:choose></li>
					<li class="nav-item ml-3 mt-1">
						<form action="/skyphone/GioHang">
							<button formaction="/skyphone/GioHang"
								class="btn btn-light position-relative font-weight-bold"
								style="width: 130px; font-size: 15px; height: 55px;">
								<i class="fa fa-shopping-cart" aria-hidden="true"
									style="font-size: 2rem;"></i> Giỏ hàng <span
									ng-if="${cart.size()}!=0"
									class="position-absolute top-0 start-50 translate-middle badge rounded-pill bg-warning"
									style="font-size: 14px;"> ${cart.size()}+ </span>
							</button>
						</form>
					</li>

					<li class="nav-item ml-4 mr-2"><a class="nav-link text-light"
						href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;24h<br> Công
							nghệ
					</a></li>
					<div class="space"></div>
					<li class="nav-item "><a
						class="nav-link text-light pt-3 ml-2 " href="#"> Hỏi đáp</a></li>
					<div class="space"></div>
					<li class="nav-item "><a class="nav-link text-light pt-3"
						href="#"> Game App</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>