const app = angular.module("skyPhoneController", []);
app.controller("adminSkyPhone", function($scope, $http) {
    $scope.itemsActive = [];
    $scope.itemsUnActive = [];
    $scope.getAll = function() {
        $http.get("/skyPhone/dienThoai/active").then(resp => {
            $scope.itemsActive = resp.data;
        });
        $http.get("/skyPhone/dienThoai/unactive").then(resp => {
            $scope.itemsUnActive = resp.data;
        });

    }
    $scope.stopSkyPhone = function(id) {
        $http.put(`/skyPhone/admin/stop/${id}`).then(resp => {
            alert("Sản phẩm của SkyPhone đã dừng hoạt động!");
            $scope.getAll();
        }).cath(error => {
            alert("Sản phẩm đang xảy ra lỗi thử lại sau!");
            console.log(error);
        });
    }
    $scope.continueSkyPhone = function(id) {
            $http.put(`/skyPhone/admin/continue/${id}`).then(resp => {
                alert("Sản phẩm của SkyPhone đã hoạt động trở lại!");
                $scope.getAll();
            }).cath(error => {
                alert("Sản phẩm đang xảy ra lỗi thử lại sau!");
                console.log(error);
            });
        }
        //Các void phân trang
    $scope.pagerUn = {
        page: 0,
        size: 5,
        get itemsUnActive() {
            var start = this.page * this.size;
            return $scope.itemsUnActive.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.itemsUnActive.length / this.size)
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.page = 0;
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.page = this.count - 1;
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
    $scope.pager = {
        page: 0,
        size: 5,
        get itemsActive() {
            var start = this.page * this.size;
            return $scope.itemsActive.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.itemsActive.length / this.size)
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.page = 0;
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.page = this.count - 1;
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
    $scope.getAll();
})