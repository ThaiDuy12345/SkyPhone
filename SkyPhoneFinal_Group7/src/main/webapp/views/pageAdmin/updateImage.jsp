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
<title>SkyPhone - Update Image</title>
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
						<div class="col-12 grid-margin">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Sản phẩm đang cập nhật ảnh</h4>
									<hr>
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageAdmin/updateImage.jsp
									<form enctype='multipart/form-data'
										action="/skyPhone/image/add/${dienThoai.id_dt}"
										class="form-sample">
=======
									<form enctype='multipart/form-data' action="" class="form-sample">
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageAdmin/updateImage.jsp
										<div class="row">
											<div class="text-left col-lg-7">${dienThoai.ten_dt!=""?dienThoai.ten_dt:'Vui lòng chọn sản phẩm cập nhật'}/${dienThoai.mau!=""?dienThoai.mau:''}/${dienThoai.dung_luong!=""?dienThoai.dung_luong:''}</div>
											<div class="text-left col-lg-5">ID Sản phẩm:
												${dienThoai.id_dt!=""?dienThoai.id_dt:'Trang cập nhật ảnh sản phẩm'}</div>
										</div>
										<div class="mt-5">
											<div class="col-lg-12 m-auto text-center img-fluid">
												<input type="file" id="file-input1" name="anhChinh"
													accept="image/gif, image/jpeg, image/png, image/jpg"
													onchange="readURL(this);"> <label for="file-input1"
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageAdmin/updateImage.jsp
													class="file-label1 "> <img id="blah1"
													src="${mainImage}" alt="" name="mainImage"
													class="mainImg img-fluid" width="530" height="510" />
=======
													class="file-label1 "> <img id="blah1" src="${mainImage != ""? mainImage:''}"
													alt="" name="mainImage" class="mainImg img-fluid" width="530" height="510" />
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageAdmin/updateImage.jsp
												</label> <br> <span class="text-center"> <span
													id="file-name1"></span>
												</span>
												<hr>
											</div>
											<div class="row canGiua">
												<div>
													<input type="file" id="file-input2" name="anhPhu1"
														accept="image/gif, image/jpeg, image/png, image/jpg"
														onchange="readURL1(this);"> <label
														for="file-input2" class="file-label2"> <img
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageAdmin/updateImage.jsp
														id="blah2" src="${image1}" name="image1" alt=""
														width="130" height="130" style="outline: none;" />
=======
														id="blah2" src="${image1 != ""? image1:''}" name="image1" alt="" width="130" height="130"
														style="outline: none;" />
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageAdmin/updateImage.jsp
													</label> <br> <span id="file-name2" style="font-size: 8px;"></span>
												</div>
												<br> <br>
												<div>
													<input type="file" id="file-input3" name="anhPhu2"
														accept="image/gif, image/jpeg, image/png, image/jpg"
														onchange="readURL2(this);"> <label
														for="file-input3" class="file-label3"> <img
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageAdmin/updateImage.jsp
														id="blah3" src="${image2}" name="image2" alt=""
														width="130" height="130" style="outline: none;" />
=======
														id="blah3" src="${image2 != ""? image2:''}" name="image2" alt="" width="130" height="130"
														style="outline: none;" />
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageAdmin/updateImage.jsp
													</label> <br> <span id="file-name3" style="font-size: 8px;"></span>
												</div>
												<br> <br>
												<div>
													<input type="file" id="file-input4" name="anhPhu3"
														accept="image/gif, image/jpeg, image/png, image/jpg"
														onchange="readURL3(this);"> <label
														for="file-input4" class="file-label4"> <img
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageAdmin/updateImage.jsp
														id="blah4" src="${image3}" name="image3" alt=""
														width="130" height="130" style="outline: none;" />
=======
														id="blah4" src="${image3 != ""? image3:''}" name="image3" alt="" width="130" height="130"
														style="outline: none;" />
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageAdmin/updateImage.jsp
													</label> <br> <span id="file-name4" style="font-size: 8px;"></span>
												</div>
											</div>
										</div>
										<div class="canGiua mt-5">
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/webapp/views/pageAdmin/updateImage.jsp
											<button class="updateImage" type="submit" formmethod="POST"
=======
											<button class="updateImage" formmethod="POST" formaction="/skyPhone/image/add/${dienThoai.id_dt}"
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/webapp/views/pageAdmin/updateImage.jsp
												style="background: linear-gradient(180deg, #d18d8d, #ff172e);">Cập
												nhật ảnh vào sản phẩm</button>
										</div>
										<div class="text-center mt-3 text-warning">
											<p>Ghi chú: Hãy chọn đủ ảnh yêu cầu không được để xót</p>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="col-12 grid-margin text-center"
							style="font-size: 22px;">Bạn có thể tiếp tục cập nhật ảnh
							của một số sản phẩm từ SkyPhone</div>

						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<div class="text-center">
										<h4 class="card-title">Sản phẩm có thể cập nhật lại ảnh</h4>
										<p class="card-description">
											Cập nhật ảnh
											<code>sản phẩm</code>
										</p>
										<p>Các sản phẩm này đã ngừng được bán bạn có thể cập nhật
											lại ảnh !</p>
									</div>
									<div class="table-responsive">
										<table class="table table-striped">
											<thead>
												<tr>
													<th>Tên Sản phẩm</th>
													<th>Giá</th>
													<th>Ảnh hiện có</th>
													<th>Trạng thái</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${sanPham}" varStatus="index">
													<tr>
														<td>${item.ten_dt}/${item.mau}/${item.dung_luong}</td>
														<td class="text-danger"><fmt:formatNumber
																type="number" value="${item.gia}" /><i
															class="typcn typcn-arrow-sorted-down"></i></td>
														<td>${soLuongAnh.get(index.index)}</td>
														<td>
															<form action="/skyPhone/image/${item.id_dt}"
																method="post">
																<button class="update"
																	formaction="/skyPhone/image/${item.id_dt}"
																	type="submit">Cập nhật ảnh</button>
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
	<script src="/js/jsAdmin/uploadFile.js"></script>
</body>

</html>
