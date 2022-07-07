<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>SkyPhone - Admin</title>
<!-- base:css -->
<link rel="stylesheet" href="/css/cssAdmin/typicons.css">
<!-- endinject -->
<!-- inject:css -->
<link rel="stylesheet" href="/css/cssAdmin/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="/images/logoJava5-removeBg.png" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/typicons/2.1.2/typicons.min.css">
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
</head>

<body>
	<div class="container-scroller">
		<!-- Nav -->
		<jsp:include page="/views/layOut/navAdmin.jsp" />
		<!-- End Nav -->
		<div class="container-fluid page-body-wrapper">
			<!-- SettingColor -->
			<jsp:include page="/views/layOut/settingColor.jsp" />
			<!-- partial SidebarAdmin-->
			<jsp:include page="/views/layOut/sidebarAdmin.jsp" />
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Sản phẩm đang được bán</h4>
									<p class="card-description">
										Quản lý
										<code>sản phẩm</code>
									</p>
									<div class="table-responsive">
										<table class="table table-hover">
											<thead>
												<tr>
													<th></th>
													<th>Tên Sản phẩm</th>
													<th>Dung lượng</th>
													<th>Màu</th>
													<th>Giá</th>
													<th>Trả góp</th>
													<th>Trạng thái</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${itemAc.content}">
													<tr>
														<td><img
															src="/images/phone_images/${item.id_dt}/0.jpg" alt="">
														</td>
														<td>${item.ten_dt}</td>
														<td>${item.dung_luong}</td>
														<td>${item.mau}</td>
														<td class="text-danger"><fmt:formatNumber
																type="number" value="${item.gia}" /><i
															class="typcn typcn-arrow-sorted-down"></i></td>
														<td>${item.tra_gop}%</td>
														<td><label class="badge badge-success">Đang
																bán</label><br>
															<form action="/skyPhone/admin/stop/${item.id_dt}"method="post">
																<button class="cancel"
																	formaction="/skyPhone/admin/stop/${item.id_dt}"
																	type="submit">Ngừng bán</button>
															</form></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
										<nav aria-label="Page navigation example">
											<ul class="pagination pagination-sm mt-3">
												<li class="page-item"><a class="page-link"
													href="/SkyPhone/page1?p=0">First</a></li>
												<li class="page-item"><a class="page-link"
													href="/SkyPhone/page1?p=${itemAc.number-1 <= 0?0:itemAc.number-1}">Previous</a></li>
												<li class="page-item"><a class="page-link"
													href="/SkyPhone/page1?p=${itemAc.number+1 >= itemAc.totalPages-1?itemAc.totalPages-1:itemAc.number+1}">Next</a></li>
												<li class="page-item"><a class="page-link"
													href="/SkyPhone/page1?p=${itemAc.totalPages-1}">Last</a></li>
											</ul>
										</nav>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-12 grid-margin text-center"
							style="font-size: 18px;">Các sản phẩm tạm ngưng bán bạn có
							thể cập nhật chúng để hoàn thiện hơn</div>
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Sản phẩm tạm ngưng</h4>
									<p class="card-description">
										Quản lý
										<code>sản phẩm</code>
									</p>
									<p class="card-description">Ghi chú: Click vào ảnh sản phẩm
										để cập nhật</p>
									<div class="table-responsive">
										<table class="table table-striped">
											<thead>
												<tr>
													<th></th>
													<th>Tên Sản phẩm</th>
													<th>Dung lượng</th>
													<th>Màu</th>
													<th>Giá</th>
													<th>Trả góp</th>
													<th>Trạng thái</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${itemUnActive.content}">
													<tr>
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageAdmin/indexAdmin.jsp
														<td><a href="/skyPhone/image/${item.id_dt}"><img
=======
														<td><a href="/dienthoai/edit/${item.id_dt}"><img
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageAdmin/indexAdmin.jsp
																src="/images/phone_images/${item.id_dt}/0.jpg"
																alt=""></a></td>
														<td>${item.ten_dt}</td>
														<td>${item.dung_luong}</td>
														<td>${item.mau}</td>
														<td class="text-danger"><fmt:formatNumber
																type="number" value="${item.gia}" /><i
															class="typcn typcn-arrow-sorted-down"></i></td>
														<td>${item.tra_gop}%</td>
														<td>
															<form action="/skyPhone/admin/continue/${item.id_dt}" method="post">
																<button class="add"
																	formaction="/skyPhone/admin/continue/${item.id_dt}"
																	type="submit">Thêm vào</button>
															</form><a class="cancel badge badge-primary"
															href="/dienthoai/edit/${item.id_dt}">Chỉnh sửa</a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<nav aria-label="Page navigation example">
											<ul class="pagination pagination-sm mt-3">
												<li class="page-item"><a class="page-link"
													href="/SkyPhone/page2?p=0">First</a></li>
												<li class="page-item"><a class="page-link"
													href="/SkyPhone/page2?p=${itemUnActive.number-1 <= 0?0:itemUnActive.number-1}">Previous</a></li>
												<li class="page-item"><a class="page-link"
													href="/SkyPhone/page2?p=${itemUnActive.number+1 >= itemUnActive.totalPages-1?itemUnActive.totalPages-1:itemUnActive.number+1}">Next</a></li>
												<li class="page-item"><a class="page-link"
													href="/SkyPhone/page2?p=${itemUnActive.totalPages-1}">Last</a></li>
											</ul>
										</nav>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- content-wrapper ends -->
				<!-- Footer -->
				<jsp:include page="/views/layOut/footerAdmin.jsp" />
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<script src="/js/jsAdmin/vendor.bundle.base.js"></script>
	<script src="/js/jsAdmin/off-canvas.js"></script>
	<script src="/js/jsAdmin/hoverable-collapse.js"></script>
	<script src="/js/jsAdmin/template.js"></script>
	<script src="/js/jsAdmin/settings.js"></script>
	<script src="/js/jsAdmin/todolist.js"></script>
</body>

</html>
