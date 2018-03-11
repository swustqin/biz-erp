window.onload = function() {
	document.getElementsByTagName("body")[0].onkeydown = function() {
		if (event.keyCode == 8) {
			var elem = event.srcElement;
			var name = elem.nodeName;
			if (name != 'INPUT' && name != 'TEXTAREA') {
				// event.returnValue = false;
				event.stopPropagation();
				event.preventDefault();
				return false;
			}
			var type_e = elem.type.toUpperCase();
			if (name == 'INPUT'
					&& (type_e != 'TEXT' && type_e != 'TEXTAREA'
							&& type_e != 'PASSWORD' && type_e != 'FILE')) {
				// event.returnValue = false;
				event.stopPropagation();
				event.preventDefault();
				return false;
			}
			if (name == 'INPUT'
					&& (elem.readOnly == true || elem.disabled == true)) {
				// event.returnValue = false;
				event.stopPropagation();
				event.preventDefault();
				return false;
			}
		}
	}
}

// 全局引用扩展JS
var global = {
	CloseWindow : function(action) {
		if (action == "close" && form.isChanged()) {
			if (confirm("数据被修改了，是否先保存？")) {
				return false;
			}
		}
		if (window.CloseOwnerWindow)
			return window.CloseOwnerWindow(action);
		else
			window.close();
	}
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}