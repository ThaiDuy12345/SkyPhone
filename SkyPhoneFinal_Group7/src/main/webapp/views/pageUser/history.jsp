<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="card-title">
		<div style="color: black;">
			<b style="font-size: 22px;">Đơn hàng đã mua gần đây</b> <br> <small><i
				class="fa fa-info-circle text-primary mr-1" aria-hidden="true"></i>
				Đây là danh sách đơn hàng bạn đã mua từ ngày 05/06/2021 đến
				05/06/2022</small>
		</div>
	</div>
	<table>
		<thead class="text-secondary">
			<tr>
				<th>Mã đơn hàng</th>
				<th class="text-center">Sản phẩm</th>
				<th>Giá</th>
				<th>Ngày đặt mua</th>
				<th>Trạng thái</th>
			</tr>
		</thead>
		<c:forEach var="itemhd" items="${HD}">
			<tbody>
				<tr style="border-top:0.05px solid lightgray;border-bottom:0.05px solid lightgray">
					<td class="py-1 p-2 text-primary">#${itemhd.id_hd}</td>
					<td class="p-2">
						<c:forEach var="itemhdct" items="${itemhd.getHDChiTiet()}">
							<div class="row">
								<div class="col-3">
									<a href="/item/product/${itemhdct.dienThoai.id_dt}"> <img src="/images/phone_images/${itemhdct.dienThoai.id_dt}/0.jpg"
										class="img-fluid" width="400px" alt=""></a>
								</div>
								<div class="col-3" style="display: flex; justify-content: center; align-items:center">
									<p class="text-danger">${itemhdct.so_luong_don}x</p>
								</div>
								<div class="col-6"  style="display: flex; justify-content: center; align-items:center">
									<p class="text-danger">${itemhdct.dienThoai.ten_dt}</p>
								</div>
							</div>
						</c:forEach>
					</td>
					<td class="p-2">
						<p class="text-danger"><fmt:formatNumber value="${itemhd.tong_gia}" type="currency" currencyCode="VND"/></p>
					</td>
					<td class="p-2">
						<p><fmt:formatDate value="${itemhd.ngay_tao_don}" pattern="dd-MM-yyyy"/></p>
					</td>
					<td class="p-2">
						<c:choose>
							<%-- Đã xét duyệt --%>
							<c:when test="${itemhd.tinh_trang==2}">
								<label class="text-success">Đã giao thành công!</label>
							</c:when>

							<%-- Chua giao --%>
							<c:when test="${itemhd.tinh_trang==0}">
								<label class="text-danger">Chưa giao!</label>
							</c:when>

							<%-- Các trường hợp khác --%>
							<c:otherwise>
								<label class="text-warning">Đang xử lí!</label>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>