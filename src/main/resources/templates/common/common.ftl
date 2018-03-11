<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${request.contextPath}/coframe/common/nui/nui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/global.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script type="text/javascript" src="${request.contextPath}/kindeditor/kindeditor.js"></script>
    <script type="text/javascript" src="${request.contextPath}/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <link id="css_skin" rel="stylesheet" type="text/css" href="${request.contextPath}/coframe/tools/skins/skin1/css/style.css"/>
    <link id="css_icon" rel="stylesheet" type="text/css" href="${request.contextPath}/coframe/tools/icons/icon.css"/>
</head>
    <h1>${request.ServletPath}</h1>
<script>
    //项目名
    (function(){
        nui.context=${request.contextPath}
    })();

    //http://p.primeton.com/articles/53b0f3b8e138230c68000081  修改为win7风格
    var data={};
    nui.DataTree.prototype.dataField='data';//兼容改造

    //ServletPath项目路径
    var curPath=${request.ServletPath};





</script>
</html>