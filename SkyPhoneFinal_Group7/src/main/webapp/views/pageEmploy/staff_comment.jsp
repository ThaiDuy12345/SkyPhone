<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nhân viên</title>
    <link rel="shortcut icon" href="/images/logoJava5-removeBg.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="/staff/style.css" rel="stylesheet">
    <link href="/staff/commentStyle.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/staff/script.js"></script>
    <script>
        function notification(text){
            alert(text);
        }
    </script>
</head>

<body>
    <div class="staffContainer">
        <nav class="navbar navbar-expand-lg">
            <a class="navbar-brand text-light" href="#">SKYPHONE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="text-light">More</span>
            </button>
            <div class="collapse row m-0 p-0 navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav col-lg">
                    <li class="nav-item">
                        <a class="text-light nav-link" href="#">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="text-light nav-link" href="#">Báo cáo hỗ trợ</a>
                    </li>
                </ul>
                <ul class="navbar-nav col-lg row m-0 p-0">
                    <div class="col-12 text-right">
                        <i class="fa fa-bell"></i>
                        <span class="badge badge-danger">4</span>
                    </div>
                </ul>
            </div>
        </nav>
        <form class="category" onmouseover="showCategory(this)" onmouseout="closeCategory(this)">
            <a href="/skyPhoneEmploy" class="categoryItem">
                <i class="bi bi-house"></i> &ensp; Trang chủ
            </a>
            <a href="/skyPhoneEmploy/order" class="categoryItem">
                <i class="bi bi-box-seam"></i> &ensp; Đơn hàng
            </a>
            <a href="/skyPhoneEmploy/comment" class="categoryItem">
                <i class="bi bi-filter-square"></i> &ensp; Đánh giá
            </a>
        </form>
        <div class="mainContent">
            <form class="theContent">
                <div class="commentTitle mb-3">
                    Đánh giá
                </div>
                <div class="commentTable">
                    <c:if test="${danhGia.content.size() == 0}">
                        <div class="row">
                            <h2 class="text-dark col-lg-12  text-center">Không có đánh giá cần duyệt</h2>
                        </div>
                    </c:if>
                    <c:forEach var="item" items="${danhGia.content}">
                    <div class="comment row">
                        <div class="customerTitle col-12">
                            Khách hàng <b>${item.sdt}</b> đã đánh giá
                        </div>
                        <div class="commentInfor col-4 row">
                            <div class="col-12">Sản phẩm đánh giá: <a href="/item/product/${item.dienThoai.id_dt}">${item.dienThoai.ten_dt}</a> </div>
                            <div class="col-12">Loại: ${item.dienThoai.mau}/${item.dienThoai.dung_luong}</div>
                        </div>
                        <div class="theComment col-7 row">
                            <div class="col-12 font-weight-bold">Mô tả đánh giá:</div>
                            <div class="col-12 font-italic">${item.noi_dung}</div>
                        </div>
                        <div class="buttonComment col-12 row">
                            <div class="col-12 mb-2"><button formmethod="POST" formaction="/skyPhoneEmploy/comment/accept/${item.stt}" class="p-2 w-100 h-100 m-auto" style="color:green">Phê
                                    duyệt</button></div>
                            <div class="col-12 mb-2"><button formmethod="POST" formaction="/skyPhoneEmploy/comment/delete/${item.stt}" class="p-2 w-100 h-100 m-auto" style="color:red">Huỷ
                                    bỏ</button></div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <c:if test="${danhGia.content.size() != 0}">
                    <div class="centerText row">
                        <button onclick="notification('Duyệt đánh giá thành công')" ${danhGia.number == 0? 'disabled':''} formaction="/skyPhoneEmploy/order?page${danhGia.number - 1}"  class="col-lg-5 col-sm-12 m-lg-auto mb-sm-2">Trang trước đó</button>
                        <button onclick="notification('Duyệt đánh giá thành công')" ${danhGia.number >= (danhGia.totalPages-1)? 'disabled':''} formaction="/skyPhoneEmploy/order?page${danhGia.number + 1}" class="col-lg-5 col-sm-12 m-lg-auto mb-sm-2">Trang tiếp theo</button>
                    </div>
                </c:if>
            </form>
        </div>
    </div>

</body>

</html>