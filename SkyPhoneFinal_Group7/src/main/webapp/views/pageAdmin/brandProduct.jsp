<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SkyPhone - BrandProduct</title>
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
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Nhãn hàng của các sản phẩm</h4>
									<p class="card-description">
										Quản lý
										<code>nhãn hàng sản phẩm</code>
									</p>
									<hr />
									<div class=row>
										<div class="col-lg-6 grid-margin stretch-card">
											<form:form action="/brand/update" modelAttribute="item"
												style="width: 100%;">
												<div class="form-group">
													<p class="card-description text-center">Tùy chỉnh nhãn
														hàng</p>
													<form:input path="ten_nhan_hang" type="text"
														class="form-control w-100" id="formGroupExampleInput2"
														placeholder="Nhãn hàng khác" />
													<div class="row mt-4"
														style="display: flex; justify-content: center; align-items: center;">
														<button formaction="/brand/create" class="btn btn-success">Thêm
															vào nhãn hàng</button>
														<button formaction="/brand/update"
															class="btn btn-danger ml-3">Sửa nhãn hàng</button>
													</div>
												</div>
											</form:form>
										</div>
										<div class="col-lg-6 grid-margin stretch-card">
											<div class="table-responsive">
												<p class="card-description text-center">Danh sách các
													nhãn hàng của SkyPhone</p>
												<table class="table table-hover">
													<thead>
														<tr>
															<th>#</th>
															<th>Tên nhãn hàng</th>
															<th>Cập nhật</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="item" items="${items.content}">
															<tr>
																<td>${item.id}</td>
																<td>${item.ten_nhan_hang}</td>
																<td>
																	<form action="/brand/edit/${item.id}" method="post">
																		<button formaction="/brand/edit/${item.id}"
																			class="cancel">Chỉnh sửa</button>
																	</form>
																</td>
															</tr>

														</c:forEach>
													</tbody>
												</table>
												<nav aria-label="Page navigation example"
													style="display: flex; justify-content: center; align-items: center;">
													<ul class="pagination pagination-sm mt-3">
														<li class="page-item"><a class="page-link"
															href="/SkyPhone/brand/page?p=0">First</a></li>
														<li class="page-item"><a class="page-link"
															href="/SkyPhone/brand/page?p=${items.number-1 <= 0?0:items.number-1}">Previous</a></li>
														<li class="page-item"><a class="page-link"
															href="/SkyPhone/brand/page?p=${items.number+1 >= items.totalPages-1?items.totalPages-1:items.number+1}">Next</a></li>
														<li class="page-item"><a class="page-link"
															href="/SkyPhone/brand/page?p=${items.totalPages-1}">Last</a></li>
													</ul>
												</nav>
											</div>
										</div>
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
	<script src="/js/jsAdmin/vendor.bundle.base.js"></script>
	<script src="/js/jsAdmin/off-canvas.js"></script>
	<script src="/js/jsAdmin/hoverable-collapse.js"></script>
	<script src="/js/jsAdmin/template.js"></script>
	<script src="/js/jsAdmin/settings.js"></script>
	<script src="/js/jsAdmin/todolist.js"></script>
</body>
</html>