/**
 * ajax公共方法封装（所有ajax请求若没有请求参数，默认会携带时间戳作为参数）
 */
$(function () {
    // $(window).ajaxStart(function(){
    //     $.showLoading();
    // }).ajaxStop(function(){
    //     $.hideLoading();
    // })
    /**
     * 重载
     * 具体参数要求参看ax7-3属性函数
     */
    jQuery.ax = function () {
        var url = arguments[0];
        var len = arguments.length;
        switch (len) {
            case 7:
                var data = arguments[1];
                var async = arguments[2];
                var requestType = arguments[3];
                var dataType = arguments[4];
                var successfn = arguments[5];
                var errorfn = arguments[6];
                $.ax7(url, data, async, requestType, dataType, successfn, errorfn);
                break;
            case 6:
                data = arguments[1];
                requestType = arguments[2];
                dataType = arguments[3];
                successfn = arguments[4];
                errorfn = arguments[5];
                $.ax6(url, data, requestType, dataType, successfn, errorfn);
                break;
            case 5:
                data = arguments[1];
                requestType = arguments[2];
                successfn = arguments[3];
                errorfn = arguments[4];
                $.ax5(url, data, requestType, successfn, errorfn);
                break;
            case 4:
                data = arguments[1];
                successfn = arguments[2];
                errorfn = arguments[3];
                $.ax4(url, data, successfn, errorfn);
                break;
            case 3:
                data = arguments[1];
                successfn = arguments[2];
                $.ax3(url, data, successfn);
                break;
        }

    }

    /**
     * ajax封装
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
     * async 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
     *       注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
     * requestType 请求方式("POST" 或 "GET")， 默认为 "GET"
     * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
     * successfn 成功回调函数
     * errorfn 失败回调函数
     */
    jQuery.ax7 = function (url, data, async, requestType, dataType, successfn, errorfn) {
        async = (async == null || async == "" || typeof (async) == "undefined") ? "true" : async;
        requestType = (requestType == null || requestType == "" || typeof (requestType) == "undefined") ? "post" : requestType;
        dataType = (dataType == null || dataType == "" || typeof (dataType) == "undefined") ? "json" : dataType;
        data = (data == null || data == "" || typeof (data) == "undefined") ? { "date": new Date().getTime() } : data;
        $.ajax({
            type: requestType,
            async: async,
            data: data,
            url: url,
            dataType: dataType,
            success: function (data) {
                successfn(data);
            },
            error: function (e) {
                errFunc();
                errorfn(e);
            }
        });
    };

    /**
     * ajax封装
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
     * requestType 请求方式("POST" 或 "GET")， 默认为 "GET"
     * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
     * successfn 成功回调函数
     * errorfn 失败回调函数
     */
    jQuery.ax6 = function (url, data, requestType, dataType, successfn, errorfn) {
        requestType = (requestType == null || requestType == "" || typeof (requestType) == "undefined") ? "post" : requestType;
        dataType = (dataType == null || dataType == "" || typeof (dataType) == "undefined") ? "json" : dataType;
        data = (data == null || data == "" || typeof (data) == "undefined") ? { "date": new Date().getTime() } : data;
        $.ajax({
            type: requestType,
            data: data,
            url: url,
            dataType: dataType,
            success: function (d) {
                successfn(d);
            },
            error: function (e) {
                errFunc();
                errorfn(e);
            }
        });
    };


    /**
     * ajax封装
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
     * requestType 请求方式("POST" 或 "GET")， 默认为 "GET"
     * successfn 成功回调函数
     * errorfn 失败回调函数
     */
    jQuery.ax5 = function (url, data, requestType, successfn, errorfn) {
        requestType = (requestType == null || requestType == "" || typeof (requestType) == "undefined") ? "post" : requestType;
        data = (data == null || data == "" || typeof (data) == "undefined") ? { "date": new Date().getTime() } : data;
        $.ajax({
            type: requestType,
            data: data,
            url: url,
            dataType: "json",
            success: function (d) {
                successfn(d);
            },
            error: function (e) {
                errFunc();
                errorfn(e);
            }
        });
    };

    /**
     * ajax封装
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
     * successfn 成功回调函数
     * errorfn 失败回调函数
     */
    jQuery.ax4 = function (url, data, successfn, errorfn) {
        data = (data == null || data == "" || typeof (data) == "undefined") ? { "date": new Date().getTime() } : data;
        $.ajax({
            type: "post",
            data: data,
            url: url,
            dataType: "json",
            headers: { 'X-Requested-With': 'XMLHttpRequest' },
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            success: function (d) {
                successfn(d);
            },
            error: function (e) {
                errFunc();
                errorfn(e);
            }
        });
    };

    /**
     * ajax封装
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
     * successfn 成功回调函数
     */
    jQuery.ax3 = function (url, data, successfn) {
        data = (data == null || data == "" || typeof (data) == "undefined") ? { "date": new Date().getTime() } : data;
        $.ajax({
            type: "post",
            data: data,
            url: url,
            dataType: "json",
            success: function (d) {
                successfn(d);
            },
            error: function (e) {
                errFunc();
            }
        });
    };

    //公共ajax错误处理方法
    var errFunc = function () {
        // $.alert("软件出错了，请稍后访问或联系管理员！","警告",function(){
        //     return;
        // });
    }

});