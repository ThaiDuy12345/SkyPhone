const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function($scope, $http) {
    //Chuyên page giỏ hàng
    $scope.gioHang = function(soLuong) {
            //Nếu số lượng sản phâm trong giỏ hàng nhỏ hơn 1 thì trả về trang notItem.html
            location.href = "/skyphone/GioHang/" + soLuong;
        }
        //Quan ly gio hang
    $scope.cart = {
        items: [],
        //Them san pham vao gio hang
        add(id, check) {
            $http.get(`/cart/add/${id}`).then(resp => {
                if (check == true) {
                    alert("Thêm sản phẩm vào giỏ hàng thành công!");
                }
                this.items = resp.data;
                this.saveToLocalStorage();
            }).catch(error => {
                alert("Lỗi khi thêm sản phẩm vào giỏ hàng! " + error);
                console.log(error);
            })
        },
        //Xóa sản phẩm khỏi giỏ hàng
        remove(id) {
            var index = this.items.findIndex(item => item.dienThoai.id_dt == id);
            if (this.items[index].soLuong < 2) {
                this.items.splice(index, 1);
                this.saveToLocalStorage();
                $http.delete(`/cart/delete/${id}`).then(resp => {
                    console.log(resp + " remove succesffully");
                }).catch(error => {
                    console.log(error);
                })
                if (this.count == 0) {
                    this.items = [];
                    location.href = "/skyphone/GioHang/" + 0;
                }
            } else {
                this.items[index].soLuong--;
                this.saveToLocalStorage();
            }
        },
        //Xoa sach cac sản phẩm trong giỏ
        clear() {
            this.items = [];
            this.saveToLocalStorage();
            $http.delete(`/cart/removeAll`).then(resp => {
                console.log(resp + " remove succesffully");
            }).catch(error => {
                console.log(error);
            })
        },
        //Tinh thanh tien cua mot san pham
        amt_of(item) {
            return item.gia * item.so_luong;
        },
        //Tinh tong so luong cac mat hang trong gio
        get count() {
            var tongSp = 0;
            for (let i = 0; i < this.items.length; i++) {
                tongSp += this.items[i].soLuong;
            }
            return tongSp;
        },
        //Tong thanh tien cac mat hang trong gio
        get amount() {
            var tongTien = 0;
            for (let i = 0; i < this.items.length; i++) {
                tongTien += this.items[i].dienThoai.gia * this.items[i].soLuong;
            }
            return tongTien;
        },
        //Luu gio hang vao local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        //Doc gio hang tu LocalStorage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
            //Đổ dữ liệu vào list trong lớp service
            // $http.post(`/cart/data`, json).then(resp => {

            // }).catch(error => {
            //     console.log(error);
            // });
        }
    }
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        purchase() {
            $scope.cart.items = [];
            $scope.cart.saveToLocalStorage();
        }
    }
});