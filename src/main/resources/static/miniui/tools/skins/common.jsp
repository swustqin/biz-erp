
<%
	String skin="skin1";
	String baseUrl=request.getContextPath();
	
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<script type="text/javascript" src="<%=baseUrl%>/coframe/common/nui/nui.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/resources/js/global.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/resources/js/jquery.jqprint.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/resources/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/resources/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/resources/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/resources/kindeditor/kindeditor-all-min.js"></script>
<link id="css_skin" rel="stylesheet" type="text/css" href="<%=baseUrl%>/coframe/tools/skins/<%=skin %>/css/style.css"/>
<link id="css_icon" rel="stylesheet" type="text/css" href="<%=baseUrl%>/coframe/tools/icons/icon.css"/>
<script>
	(function(){
		nui.context='<%=baseUrl %>'
	})();
	
	var data={};
	nui.DataTree.prototype.dataField='data';//兼容改造

	//获取当前用户对应当前页面的所有按钮权限
	var curPath="<%=request.getServletPath()%>";
	var url;
	var tab;
	if(top.nui.get("tabContainer")){
		tab=top.nui.get("tabContainer").getActiveTab();
		var url=tab.url;
		
		$(function(){
			g.ajax({
		     	url: "<%=baseUrl%>/getRoleButtonData.do",
					data:{curPath:curPath,url:url},
					backCall:function(text){
							if(text.data){
							var data=nui.clone(text.data);
							for(var cur=0;cur<data.length;cur++){
								if(data[cur].status==0){
									$("#"+data[cur].btCode).hide();
								}
							}
						}
					}
			});
			
		});
	}
	
	
	//获取组织机构  --数据权限 返回全路径
	 function selectUserOrgQljEdit(e) {
	        var btnEdit = this;
	        <%--  url: "<%=baseUrl%>/page.do?view=hr/org/org_selects_list", --%>
	        nui.open({
	          url: "<%=baseUrl%>/page.do?view=hr/business/select_org_info",
	            showMaxButton: false,
	            title: " <%= new String("组织机构".getBytes("ISO-8859-1"), "utf-8")%> ",
	            width: 350,
	            height: 400,
	            ondestroy: function(action){
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
                            btnEdit.setValue(data.quanLuJin);
	                        btnEdit.setText(data.text);
	                    }
	                }
	            }
	        });  
	    }
	
	//获取组织机构  --数据权限 返回组织
	 function selectUserOrgEdit(e) {
	        var btnEdit = this;
	        <%--  url: "<%=baseUrl%>/page.do?view=hr/org/org_selects_list", --%>
	        nui.open({
	          url: "<%=baseUrl%>/page.do?view=hr/business/select_org_info",
	            showMaxButton: false,
	            title: " <%= new String("组织机构".getBytes("ISO-8859-1"), "utf-8")%> ",
	            width: 350,
	            height: 400,
	            ondestroy: function(action){
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
                            btnEdit.setValue(data.text);
	                        btnEdit.setText(data.text);
	                    }
	                }
	            }
	        });  
	    }
	
		//获取组织机构 --数据权限 返回ORG_ID
	 function selectUserOrgIdEdit(e) {
	        var btnEdit = this;
	        <%--  url: "<%=baseUrl%>/page.do?view=hr/org/org_selects_list", --%>
	        nui.open({
	          url: "<%=baseUrl%>/page.do?view=hr/business/select_org_info",
	            showMaxButton: false,
	            title: " <%= new String("组织机构".getBytes("ISO-8859-1"), "utf-8")%> ",
	            width: 350,
	            height: 400,
	            ondestroy: function(action){
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
                            btnEdit.setValue(data.nodeId);
	                        btnEdit.setText(data.text);
	                    }
	                }
	            }
	        });  
	    }

	//获取组织机构  返回ORG_ID
	 function selectAllOrgIdEdit(e) {
	        var btnEdit = this;
	        nui.open({
	        	url: "<%=baseUrl%>/page.do?view=hr/org/org_selects_list",
	            showMaxButton: false,
	            title: " <%= new String("组织机构".getBytes("ISO-8859-1"), "utf-8")%> ",
	            width: 350,
	            height: 400,
	            ondestroy: function(action){
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
	                        btnEdit.setValue(data.nodeId);
	                        btnEdit.setText(data.text);
	                       
	                    }
	                }
	            }
	        });  
	    }
	
	
	//获取组织机构  返回全路径
	 function selectAllOrgQljEdit(e) {
	        var btnEdit = this;
	        nui.open({
	        	url: "<%=baseUrl%>/page.do?view=hr/org/org_selects_list",
	            showMaxButton: false,
	            title: " <%= new String("组织机构".getBytes("ISO-8859-1"), "utf-8")%> ",
	            width: 350,
	            height: 400,
	            ondestroy: function(action){
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
	                        btnEdit.setValue(data.quanLuJin);
	                        btnEdit.setText(data.text);
	                       
	                    }
	                }
	            }
	        });  
	    }
	
	//获取组织机构  返回组织
	 function selectAllOrgEdit(e) {
	        var btnEdit = this;
	        nui.open({
	        	url: "<%=baseUrl%>/page.do?view=hr/org/org_selects_list",
	            showMaxButton: false,
	            title: " <%= new String("组织机构".getBytes("ISO-8859-1"), "utf-8")%> ",
	            width: 350,
	            height: 400,
	            ondestroy: function(action){
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
	                        btnEdit.setValue(data.text);
	                        btnEdit.setText(data.text);
	                       
	                    }
	                }
	            }
	        });  
	    }
	
	//获取职位
	function selectPostListEdit(e){
	  var btnEdit = this;	
	  nui.open({
      url: "<%=baseUrl%>/page.do?view=hr/org/compile_select_post_list",        
      title: "<%= new String("职位列表".getBytes("ISO-8859-1"), "utf-8")%>",
      width: 800, 
      height: 480,
      allowResize:false,
      onload: function () {
      	var data = {orgId:""};
      	var iframe = this.getIFrameEl();
          iframe.contentWindow.SetData(data);
      },
      ondestroy: function (action) {
         if (action == "ok") {
              var iframe = this.getIFrameEl();
              var data = iframe.contentWindow.getData();
              data = nui.clone(data);    //必须
              if (data) {
             	 btnEdit.setValue(data.postName);
                  btnEdit.setText(data.postName);
              
              }
          } 
      }
  });
}
	
</script>
