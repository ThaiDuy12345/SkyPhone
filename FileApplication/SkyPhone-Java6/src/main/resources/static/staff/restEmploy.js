var app = angular.module("app", []);
app.controller("controller_employ_comment", function ($scope, $http) {
    $scope.curPage = 0;
    $scope.danhGia = {};
    $scope.APIData = {
        "dg": [],
        "dt": [],
        "nh": []
    };
    $scope.pageSize = function () {
        if ($scope.APIData.dg.length < 5)
            return 0;
        else if ($scope.APIData.dg.length % 5 == 0)
            return $scope.APIData.dg.length / 5;
        else if (Math.round($scope.APIData.dg.length / 5) < $scope.APIData.dg.length / 5)
            return Math.round($scope.APIData.dg.length / 5) + 1;
        else
            return Math.round($scope.APIData.dg.length / 5);
    };
    $scope.pagingLeft = function () {
        $scope.curPage = $scope.curPage == 0 ? 0 : $scope.curPage - 1;
        $scope.loadPage();
    };
    $scope.pagingRight = function () {
        $scope.curPage = $scope.curPage == $scope.pageSize() ? $scope.curPage : $scope.curPage + 1;
        $scope.loadPage();
    };
    $scope.loadPage = function () {
        var listDg = [];
        var listDt = [];
        var listNh = [];
        for (var i = $scope.curPage * 5; i < $scope.curPage * 5 + 5; i++) {
            if (i == $scope.APIData.dg.length)
                break;
            listDg.push($scope.APIData.dg[i]);
            listDt.push($scope.APIData.dt[i]);
            listNh.push($scope.APIData.nh[i]);
        }
        $scope.danhGia = {
            "dg": listDg,
            "dt": listDt,
            "nh": listNh
        };
    };
    var url = "http://localhost:8080/skyPhoneEmploy/comment/";
    $scope.loading = function () {
        $http.get(url + "getAllComment")
            .then(function (resp) {
            $scope.APIData = resp.data;
            $scope.loadPage();
        })["catch"](function (error) {
            console.log(error);
        });
    };
    $scope.pheDuyet = function (stt) {
        $http.put(url + "accept/" + stt)
            .then(function (resp) {
            alert("Phê duyệt đánh giá thành công");
            if (($scope.APIData.dg.length - 1) % 5 == 0)
                $scope.curPage = $scope.curPage - 1;
            $scope.loading();
        });
    };
    $scope.huyBo = function (stt) {
        $http["delete"](url + "delete/" + stt)
            .then(function (resp) {
            alert("Xoá đánh giá thành công");
            if (($scope.APIData.dg.length - 1) % 5 == 0)
                $scope.curPage = $scope.curPage - 1;
            $scope.loading();
        });
    };
    $scope.loading();
});
app.controller("controller_employ_not_order", function ($scope, $http) {
    var url = "http://localhost:8080/skyPhoneEmploy/order/";
    $scope.curPage = 0;
    $scope.notDoneOrder = {};
    $scope.APIData = {
        "hd": {}
    };
    $scope.pageSize = function () {
        if ($scope.APIData.hd.length < 3)
            return 0;
        else if ($scope.APIData.hd.length % 3 == 0)
            return $scope.APIData.hd.length / 3;
        else if (Math.round($scope.APIData.hd.length / 3) < $scope.APIData.hd.length / 3)
            return Math.round($scope.APIData.hd.length / 3) + 1;
        else
            return Math.round($scope.APIData.hd.length / 3);
    };
    $scope.pagingLeft = function () {
        $scope.curPage = $scope.curPage == 0 ? 0 : $scope.curPage - 1;
        $scope.loadPage();
    };
    $scope.pagingRight = function () {
        $scope.curPage = $scope.curPage == $scope.pageSize() ? $scope.curPage : $scope.curPage + 1;
        $scope.loadPage();
    };
    $scope.loadPage = function () {
        var listhd = [];
        for (var i = $scope.curPage * 3; i < $scope.curPage * 3 + 3; i++) {
            if (i == $scope.APIData.hd.length)
                break;
            listhd.push($scope.APIData.hd[i]);
        }
        $scope.notDoneOrder = {
            "hd": listhd
        };
    };
    $scope.loading = function () {
        $http.get(url + "getAllNotDoneOrder")
            .then(function (resp) {
            $scope.APIData = resp.data;
            $scope.loadPage();
        })["catch"](function (error) {
            console.log(error);
        });
    };
    $scope.loading();
});
app.controller("controller_employ_order", function ($scope, $http) {
    var url = "http://localhost:8080/skyPhoneEmploy/order/";
    $scope.curPage = 0;
    $scope.doneOrder = {};
    $scope.APIData = {
        "hd": {}
    };
    $scope.pageSize = function () {
        if ($scope.APIData.hd.length < 3)
            return 0;
        else if ($scope.APIData.hd.length % 3 == 0)
            return $scope.APIData.hd.length / 3;
        else if (Math.round($scope.APIData.hd.length / 3) < $scope.APIData.hd.length / 3)
            return Math.round($scope.APIData.hd.length / 3) + 1;
        else
            return Math.round($scope.APIData.hd.length / 3);
    };
    $scope.pagingLeft = function () {
        $scope.curPage = $scope.curPage == 0 ? 0 : $scope.curPage - 1;
        $scope.loadPage();
    };
    $scope.pagingRight = function () {
        $scope.curPage = $scope.curPage == $scope.pageSize() ? $scope.curPage : $scope.curPage + 1;
        $scope.loadPage();
    };
    $scope.loadPage = function () {
        var listhd = [];
        for (var i = $scope.curPage * 3; i < $scope.curPage * 3 + 3; i++) {
            if (i == $scope.APIData.hd.length)
                break;
            listhd.push($scope.APIData.hd[i]);
        }
        $scope.doneOrder = {
            "hd": listhd
        };
    };
    $scope.loading = function () {
        $http.get(url + "getAllDoneOrder")
            .then(function (resp) {
            $scope.APIData = resp.data;
            $scope.loadPage();
        })["catch"](function (error) {
            console.log(error);
        });
    };
    $scope.loading();
});
