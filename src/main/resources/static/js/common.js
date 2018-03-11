//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
};
T.p = url;

T.closeOpenWindow = function (action) {
    if (action == "close" && form.isChanged()) {
        if (confirm("数据被修改了，是否先保存？")) {
            return false;
        }
    }
    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
    else window.close();
}
//全局配置
$.ajaxSetup({
    dataType: "json",
    cache: false
});

Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//重写alert
window.alert = function (option) {
    if (typeof(option) != 'object') {
        var msg = option;
        option = {msg: msg};
    }
    parent.mini.alert(option.msg, option.title ? option.title : "系统提示", function () {
        if (typeof(option.callback) == "function") {
            option.callback("ok");
        }
    });
}

//重写confirm式样框
window.confirm = function (option) {
    parent.mini.confirm(option.msg, option.title ? option.title : "系统提示",
        function (action) {
            if (action == "ok") {
                if (typeof(option.callback) == "function") {
                    option.callback("ok");
                }
            }
        });
}
/**
 * ajax请求
 * @param option ajax必要参数
 */
var $ajax = function (option) {
    $.ajax({
        url: option.url,
        data: option.data ? option.data : "",
        type: option.type ? option.type : "POST",
        dataType: "json",
        async: option.async ? option.async : false,
        contentType: "application/json",
        success: function (data) {
            mini.unmask();
            if (typeof(option.callback) == "function") {
                if (data.code == 0 || data.code != 500) {
                    option.callback(data);
                } else {
                    alert(data.msg);
                }
            }
        },
        error: function () {
            alert({msg: "Ajax请求错误。"})
            mini.unmask();
        }
    })
}
var timerFormatter = function (row) {
    var value = row.value;
    return value > 0 ? new Date(value * 1000).format('yyyy-MM-dd hh:mm:ss') : 0;
};
var timerFormatterYmd = function (value) {
    return value > 0 ? new Date(value * 1000).format('yyyy-MM-dd') : 0;
};
var timerFormatterAll = function (value) {
    return value > 0 ? new Date(value * 1000).format('yyyy-MM-dd hh:mm:ss') : 0;
};

var lockMask = function (msg) {
    mini.mask({
        el: document.body,
        cls: 'mini-mask-loading',
        html: msg ? msg : '正在保存...'
    });
}

var umMask = function () {
    mini.unmask();
}


/**
 * 选择用户
 * @param basePath
 * @param destory
 */
var $chooseUser = function (basePath, destory) {
    mini.open({
        url: basePath + "/basic/personinfo/chooseUser",
        title: "选择用户",
        width: 500,
        height: 460,
        allowResize: false,
        ondestroy: function (action) {
            if (typeof(destory) == 'function') {
                if (action == "confirm") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getSelectedData();
                    destory(data);
                }else
                    destory({});
            }
        }
    });
}
/**
 * 选择供应商
 * @param basePath
 * @param destory
 */
var $chooseSupplier = function (basePath, destory) {
    mini.open({
        url: basePath + "/basic/supplier/chooseSupplier",
        title: "选择供应商",
        width: 500,
        height: 460,
        allowResize: false,
        ondestroy: function (action) {
            if (typeof(destory) == 'function') {
                if (action == "confirm") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getSelectedData();
                    destory(data);
                } else
                    destory({});
            }
        }
    });
}

 /**
  * 选择终端资产配件类型
  * @param basePath
  * @param destory
  */
    var $choosePartType = function (basePath, destory) {
        mini.open({
            url: basePath + "/busi/trml/parts/choosePartType",
            title: "选择配件类型",
            width: 400,
            height: 470,
            allowResize: false,
            ondestroy: function (action) {
                if (typeof(destory) == 'function') {
                    if (action == "confirm") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.getSelectedData();
                        destory(data);
                    }else
                        destory({});
                }
            }
        });
    }

/**
 * 选择区域
 * @param basePath
 * @param destory
 */
var $chooseArea = function (basePath, destory) {
    mini.open({
        url: basePath + "/basic/area/chooseArea",
        title: "选择区域",
        width: 500,
        height: 460,
        allowResize: false,
        ondestroy: function (action) {
            if (typeof(destory) == 'function') {
                if (action == "confirm") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getSelectedData();
                    destory(data);
                }else
                    destory({});
            }
        }
    });
}

/**
 * 选择软件基本信息
 * @param basePath
 * @param destory
 */
var $chooseSoftInfo = function (basePath, destory) {
    mini.open({
        url: basePath + "/soft/basicInfo/chooseSoftInfo",
        title: "选择软件信息",
        width: 500,
        height: 460,
        allowResize: false,
        ondestroy: function (action) {
            if (typeof(destory) == 'function') {
                if (action == "confirm") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getSelectedData();
                    destory(data);
                }else
                    destory({});
            }
        }
    });
}

/**
 * 选择规格型号
 * @param basePath
 * @param destory
 */
var $chooseSpeciModel = function (basePath, destory) {
    mini.open({
        url: basePath + "/trml/basic/chooseSpeciModel",
        title: "选择规格型号",
        width: 500,
        height: 460,
        allowResize: false,
        ondestroy: function (action) {
            if (typeof(destory) == 'function') {
                if (action == "confirm") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getSelectedData();
                    destory(data);
                }else
                    destory({});
            }
        }
    });
}

/**
 * 选择管理员
 * @param basePath
 * @param destory
 */
var $chooseManagerUser = function (basePath, destory) {
    debugger
    mini.open({
        url: basePath + "/sys/user/chooseUser",
        title: "选择管理员",
        width: 500,
        height: 460,
        allowResize: false,
        ondestroy: function (action) {
            if (typeof(destory) == 'function') {
                if (action == "confirm") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getSelectedData();
                    destory(data);
                }else
                    destory({});
            }
        }
    });
}

/**
 * 选择组织
 * @param basePath
 * @param destory
 */
var $chooseOrg = function (basePath, destory) {
    debugger
    mini.open({
        url: basePath + "/basic/orginfo/chooseOrg",
        title: "选择组织",
        width: 500,
        height: 460,
        allowResize: false,
        ondestroy: function (action) {
            if (typeof(destory) == 'function') {
                if (action == "confirm") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getSelectedData();

                    destory(data);
                }else
                    destory({});
            }
        }
    });
}


/**
 * 选择财务组织
 * @param basePath
 * @param destory
 */
var $chooseVir = function (basePath, destory) {
    debugger
    mini.open({
        url: basePath + "/basic/virtual/chooseVir",
        title: "选择财务组织",
        width: 500,
        height: 460,
        allowResize: false,
        ondestroy: function (action) {
            if (typeof(destory) == 'function') {
                if (action == "confirm") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getSelectedData();
                    destory(data);
                }else
                    destory({});
            }
        }
    });
}

// 上传插件
$(function () {
    $(".fileupload").each(function (index, item) {
        var _id = $(item).attr("id");
        var _remote = $(item).attr("remote");
        var _callFn = $(item).attr("callFn");
        var _allowExtends = $(item).attr("allowExtends");
        var _disAllowTip = $(item).attr("allowExtends");
        if (!_allowExtends) {
            _allowExtends = ',jpg,jpeg,png,gif,';
        }
        if (_disAllowTip) {
            _disAllowTip = "只支持jpg、png、gif格式的图片！";
        }
        if (!_callFn) {
            alert("请设置callFn属性");
            return;
        }
        new AjaxUpload('#' + _id, {
            action: '../sys/oss/upload',
            name: 'file',
            data: {"uploadType": _remote ? _remote : "oss"},
            autoSubmit: true,
            responseType: "json",
            onSubmit: function (file, extension) {
                // 进行文件验证
                if (!(extension && _allowExtends.indexOf("," + extension.toLowerCase() + ",") >= 0)) {
                    alert(_disAllowTip);
                    return false;
                }
            },
            onComplete: function (file, r) {
                if (r.code == 0) {
                    eval(_callFn + "('" + file + "','" + r.url + "')")
                } else {
                    alert("上传文件失败");
                }
            }
        });
    });
});
