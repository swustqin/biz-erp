/**
 * Created by mr.jie on 2017/8/30.
 */

mini.parse();
var tree = mini.get("leftTree");
var currentTab = null;
function showTab(node) {
    var tabs = getMainTabs();
    var id = "tab$" + node._id;
    var tab = tabs.getTab(id);
    if (!tab) {
        tab = {};
        tab._nodeid = node._id;
        tab.name = id;
        tab.title = node.text;
        tab.showCloseButton = true;

        //这里拼接了url，实际项目，应该从后台直接获得完整的url地址
        tab.url = node.url + "?menuId=" + node.id;
        tabs.addTab(tab);
    }
    tabs.activeTab(tab);
}

function nodeSelect(e) {
    var node = e.node;
    var isLeaf = e.isLeaf;
    if (isLeaf) {
        showTab(node);
    }
}

/************jquery文本滚动*********************************************/
function marqueeScroll(id) {
    $(id).find("ul:first").animate({
        marginTop: "-22px"
    }, 500, function () {
        $(this).css({marginTop: "0px"}).find("li:first").appendTo(this);
    });
}

function autoScroll(id) {
    var myar = setInterval('marqueeScroll("' + id + '")', 3000);
    //当鼠标放上去的时候，滚动停止，鼠标离开的时候滚动开始
    $(id).hover(function () {
        clearInterval(myar);
    }, function () {
        myar = setInterval('marqueeScroll("' + id + '")', 3000)
    });
}


/******************************************************************/
var closeTab = function () {
    console.info(currentTab.name)
    if (currentTab.title != '首页') {
        mini.get("mainTabs").removeTab(currentTab);
    }
}

var closeOtherTab = function () {
    var mainTabs = getMainTabs();
    var exclude = [currentTab];
    exclude.push(mainTabs.getTab("indexPage"));
    mainTabs.removeAll(exclude);
}
var closeAllTab = function () {
    var mainTabs = getMainTabs();
    var exclude = [];
    exclude.push(mainTabs.getTab("indexPage"));
    mainTabs.removeAll(exclude);

}
var refreshTab = function () {
    var mainTabs = getMainTabs();
    mainTabs.activeTab(currentTab);
    mainTabs.reloadTab(currentTab);
}

var getMainTabs = function () {
    var mainTabs = mini.get("mainTabs");
    return mainTabs;
}

function beforeOpen(e) {
    currentTab = mini.get("mainTabs").getTabByEvent(e.htmlEvent);
    if (!currentTab) {
        e.cancel = true;
    }
}
//页面加载完成后

autoScroll("#div_Scroll");
$(function () {

})