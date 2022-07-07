<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
  <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close d-flex align-items-center justify-content-center" data-dismiss="modal" aria-label="Close">
		        <i class="fa fa-times" aria-hidden="true"></i>
		        </button>
                </div>
                <div class="row no-gutters">
                    <div class="col-md-6 d-flex">
                        <div class="modal-body p-5 img d-flex" style="background-image: url(https://png.pngtree.com/template/20190422/ourlarge/pngtree-phone-store-logo-design-image_145177.jpg);">
                        </div>
                    </div>
                    <div class="col-md-6 d-flex">
                        <div class="modal-body p-5 d-flex align-items-center">
                            <div class="text w-100 text-center py-5">
                                <h2 class="mb-0">50<span>%</span> Off</h2>
                                <h4 class="mb-4">Cho tất cả các nhãn hàng SkyPhone</h4>
                                <form action="/user/history/${hd.sdt_nguoi_nhan}" class="code-form">
                                    <div class="form-group d-flex">
                                        <input type="text" class="form-control" name="id" value="${id}"  placeholder="Số điện thoại của bạn">
                                    </div>
                                    <button class="btn btn-primary d-block py-3">Kiểm tra số điện thoại</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>