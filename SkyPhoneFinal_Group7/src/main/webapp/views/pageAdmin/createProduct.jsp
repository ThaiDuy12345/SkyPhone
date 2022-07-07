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
<title>SkyPhone - Create Product</title>
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
<link rel="stylesheet" href="/css/cssAdmin/tableCreate.css">
<style>
.paginate_button {
	padding: 10px;
	border: 1px solid #e3e3e3;
	cursor: pointer;
}

.paginate_button:hover {
	text-decoration: none;
	background-color: gray;
}

.paginate_button:focus {
	color: white;
	background-color: #3cadff;
}
</style>
</head>

<body>
	<script>
		if ("${message}" != "") {
			alert("${message}")
		}
	</script>
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
						<div class="col-12 grid-margin">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Thêm mới sản phẩm vào SkyPhone</h4>
									<form:form class="form-sample" action="/dienthoai/create"
										modelAttribute="phone" method="post">
										<hr class="pb-4">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Tên sản phẩm</label>
													<div class="col-sm-9">
														<form:input path="ten_dt" type="text" class="form-control" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Dung lượng</label>
													<div class="col-sm-9">
														<form:input path="dung_luong" type="text"
															class="form-control" />
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Màu</label>
													<div class="col-sm-9">
														<form:input path="mau" type="text" class="form-control" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Nhãn hàng</label>
													<div class="col-sm-9">
														<form:select path="" id="" class="form-control"
															style="height: 40px;" name="nhanHang">
															<c:forEach var="item" items="${nhanHangList}">
																<option value="${item.id}">${item.ten_nhan_hang}</option>
															</c:forEach>
														</form:select>
													</div>
												</div>
											</div>
										</div>
										<hr class="pb-3">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Giá sản phẩm</label>
													<div class="col-sm-9">
														<form:input path="gia" type="number" class="form-control" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Nhà sản xuất</label>
													<div class="col-sm-9">
														<form:input path="nha_sx" type="text" class="form-control" />
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Trả góp %</label>
													<div class="col-sm-9">
														<form:input path="tra_gop" type="number"
															class="form-control" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Bảo hành</label>
													<div class="col-sm-9">
														<form:input path="bao_hanh" type="text"
															class="form-control" />
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group row">
													<label class="col-sm-1 col-form-label">Mô tả</label>
													<div class="col-sm-11" style="padding-left: 65px;">
														<textarea name="mota" class="form-control" col=10
															rows="10" placeholder="Giới thiệu về sản phẩm..."></textarea>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<button class="btn-add" formaction="/dienthoai/create">Thêm
												sản phẩm vào SkyPhone</button>
										</div>
									</form:form>
								</div>
							</div>
						</div>

						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Thêm hình cho sản phẩm SkyPhone</h4>
									<p class="card-description">
										Thêm hình
										<code>sản phẩm</code>
									</p>
									<div class="table-responsive">
										<table class="table table-striped" id="dataTables-example">
											<thead>
												<tr>
													<th>Tên Sản phẩm</th>
													<th>Giá</th>
													<th>Ảnh hiện có</th>
													<th>Trạng thái</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${sanPhamKoHinh}">
													<tr>
														<td>${item.ten_dt}</td>
														<td class="text-danger"><fmt:formatNumber
																type="number" value="${item.gia}" /> <i
															class="typcn typcn-arrow-sorted-down"></i></td>
														<td>0</td>
														<td>
															<form action="/skyPhone/image/${item.id_dt}"
																method="post">
																<button class="add"
																	formaction="/skyPhone/image/${item.id_dt}"
																	type="submit">Thêm ảnh</button>
															</form>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>

									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!-- content-wrapper ends -->
				<!-- Footer -->
				<jsp:include page="/views/layOut/footerAdmin.jsp" />
				<!-- Footer -->
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
	<!-- DATA TABLE SCRIPTS -->
	<script src="/js/assert/boostrapJs.js"></script>
	<script src="/js/assert/jquery.js"></script>
	<script src="/js/assert/dataTable.js"></script>
	<script>
		$(document).ready(function() {
			$('#dataTables-example').dataTable();
		});
	</script>
</body>

</html>