const app = angular.module("createSkyPhoneApp", []);
app.controller("skyPhone", function($scope, $http) {
    $scope.itemsNhanHang = [];
    $scope.sanPhamKoHinh = [];
    $scope.skyPhone = {};
    $scope.getnewProduct = function() {
        $http.get("/nhanHang/all").then(resp => {
            this.itemsNhanHang = resp.data;
        });
        $http.get("/skyPhone/notImage").then(resp => {
            this.sanPhamKoHinh = resp.data;
        });
    }
    $scope.create = function(skyPhone) {
        skyPhone.hoat_dong = false;
        $http.post(`/skyPhone/create`, skyPhone).then(resp => {
            alert("Thêm mới sản phẩm vào SkyPhone thành công!");
            location.href = "/SkyPhone/admin/dienThoai";
        }).catch(error => {
            alert("Xảy ra lỗi khi bạn thêm sản phẩm vào SkyPhone!");
            console.log(error);
        });
    }
    $scope.reset = function() {
        $scope.skyPhone = {};
    }
    $scope.getnewProduct();
});