<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<script src="${pageContext.request.contextPath}/js/pagination/myPagination/jquery.myPagination6.0.js">  </script> 
<script src="${pageContext.request.contextPath}/js/pagination/msgbox/msgbox.js">  </script> 
<script src="${pageContext.request.contextPath}/js/pagination/jTemplates/jquery-jtemplates.js" >   </script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/pagination/msgbox/msgbox.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/pagination/myPagination/page.css"/>
<style type='text/css'>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
  <!--  
  <script type="text/javascript" src="${ctx}/sysjs/TreeSelector.js"></script>
  <script type="text/javascript" src="${ctx}/outh/backUserview.js"></script>
  <script type="text/javascript" src="${ctx}/outh/addBackUser.js"></script>
  <script type="text/javascript" src="${ctx}/outh/addLogisticsUser.js"></script>
 <script type="text/javascript" src="${ctx}/outh/backUserGrantRoleWindow.js"></script>
  <script type="text/javascript" src="${ctx}/outh/updateUserRole.js"></script>
  -->
 
<script type="text/javascript">
			__ctxPath = "${pageContext.request.contextPath}";
			var productPagination;
			$(function() {    	
			    initUserRole();
			    $("#pageSelect").change(userRoleQuery);
			});
			function userRoleQuery(){
		        var params = $("#product_form").serialize();
		        //alert("表单序列化后请求参数:"+params);
		        params = decodeURI(params);
		        productPagination.onLoad(params);
		   	}
			function clearQuery(){
				$("#name").val("");
				$("#type").val("");
			}
			//新增电果网用户
			function addBackUser(){
				var url = __ctxPath+"/jsp/UserRole/addBackUser.jsp";
				$("#pageBody").load(url);
			}
			//新增物流专员
			function addLogisticsUser(){
				var url = __ctxPath+"/jsp/UserRole/addLogisticUser.jsp";
				$("#pageBody").load(url);
			}
			//初始化角色列表
		 	function initUserRole() {
				var url = __ctxPath+"/backUser/getBackUserList";
				productPagination = $("#productPagination").myPagination({
		           panel: {
		             tipInfo_on: true,
		             tipInfo: '&nbsp;&nbsp;跳{input}/{sumPage}页',
		             tipInfo_css: {
		               width: '25px',
		               height: "20px",
		               border: "2px solid #f0f0f0",
		               padding: "0 0 0 5px",
		               margin: "0 5px 0 5px",
		               color: "#48b9ef"
		             }
		           },
		           debug: false,
		           ajax: {
		             on: true,
		             url: url,
		             dataType: 'json',
		             ajaxStart: function() {
		               ZENG.msgbox.show(" 正在加载中，请稍后...", 1, 1000);
		             },
		             ajaxStop: function() {
		               //隐藏加载提示
		               setTimeout(function() {
		                 ZENG.msgbox.hide();
		               }, 300);
		             },
		             callback: function(data) {
		               //使用模板
		               $("#product_tab tbody").setTemplateElement("product-list").processTemplate(data);
		             }
		           }
		         });
		    }	
		 	//禁用用户
			function delBackUser(){
				var checkboxArray=[];
				$("input[type='checkbox']:checked").each(function(i, team){
					var productSid = $(this).val();
					checkboxArray.push(productSid);
				});
				if(checkboxArray.length>1){
					ZENG.msgbox.show(" 只能选择一列", 5, 2000);
					 return false;
				}else if(checkboxArray.length==0){
					ZENG.msgbox.show("请选取要删除的记录", 5, 2000);
					 return false;
				}
				bootbox.confirm("确定要删除吗?", function(r){
					if(r){
						var value=	checkboxArray[0];
						$.ajax({
							type: "post",
							contentType: "application/x-www-form-urlencoded;charset=utf-8",
							url: __ctxPath+"/backUser/deleteBackUser",
							dataType: "json",
							data: {
								"sid":value
							},
							success: function(response) {
								if(response.success=="true"){
									$("#modal-body-success").html("<div class='alert alert-success fade in'>"+
										"<i class='fa-fw fa fa-times'></i><strong>删除成功，返回列表页!</strong></div>");
					  				$("#modal-success").attr({"style":"display:block;","aria-hidden":"false","class":"modal modal-message modal-success"});
								}else{
									$("#warning2Body").text(buildErrorMessage("","删除失败！"));
									$("#warning2").show();
								}
								return;
							}
						});
					}
				});
			}
		 	//用户赋角色
		 	function setUserRole(){
		 		var checkboxArray=[];
				$("input[type='checkbox']:checked").each(function(i, team){
					var productSid = $(this).val();
					checkboxArray.push(productSid);
				});
				if(checkboxArray.length>1){
					$("#warning2Body").text(buildErrorMessage("","只能选择一列！"));
					$("#warning2").show();
					 return false;
				}else if(checkboxArray.length==0){
					$("#warning2Body").text(buildErrorMessage("","请选取要修改的列！"));
					$("#warning2").show();
					 return false;
				}
				var value=	checkboxArray[0];
				sid=value;
				userName_ = $("#userName_"+value).text().trim();
				realName_ = $("#realName_"+value).text().trim();
				roleSid_= $("#roleSid_"+value).attr("value");
		 		var url = __ctxPath+"/jsp/UserRole/setUserRole.jsp";
				$("#pageBody").load(url);
		 	}
			function successBtn(){
				$("#modal-success").attr({"style":"display:none;","aria-hidden":"true","class":"modal modal-message modal-success fade"});
				$("#pageBody").load(__ctxPath+"/jsp/backUser.jsp");
			}
</script> 
</head>
<body>
          <!-- Main Container -->
    <div class="main-container container-fluid">
        <!-- Page Container -->
        <div class="page-container">
                <!-- Page Body -->
                <div class="page-body" id="pageBodyRight">
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="widget">
                                <div class="widget-header ">
                                    <span class="widget-caption"><h5>用户管理</h5></span>
                                    <div class="widget-buttons">
                                        <a href="#" data-toggle="maximize"></a>
                                        <a href="#" data-toggle="collapse" onclick="tab('pro');">
                                            <i class="fa fa-minus" id="pro-i"></i>
                                        </a>
                                        <a href="#" data-toggle="dispose"></a>
                                    </div>
                                </div>
                                <div class="widget-body" id="pro">
                                 <form id="product_form" action="">
                                    <div class="table-toolbar">
                                    	用户名：<input style="width: 60px;margin-top:3px" type="text" name="name" id="name" >
                                    	用户类型：
                                    	<select class="btn btn-default" name="type" id="type">
                                    		<option value=""></option>
                                    		<option value="0">电果网用户</option>
                                    		<option value="1">物流专员</option>
                                    	</select>
                                    	<a id="editabledatatable_new" onclick="userRoleQuery();" class="btn btn-default">
											查询
                                        </a>&nbsp;&nbsp;
                                        <a id="editabledatatable_new" onclick="clearQuery();" class="btn btn-default">
											重置
                                        </a>&nbsp;&nbsp;
                                        <a id="editabledatatable_new" onclick="addBackUser();" class="btn btn-primary glyphicon glyphicon-plus">
										新增电果网用户
                                        </a>&nbsp;&nbsp;
                                        <a id="editabledatatable_new" onclick="addLogisticsUser();" class="btn btn-primary glyphicon glyphicon-plus">
										新增物流专员
                                        </a>&nbsp;&nbsp;
                                        <a id="editabledatatable_new" onclick="delBackUser();" class="btn btn-danger glyphicon glyphicon-trash">
											禁用用户
                                        </a>
                                        <a id="editabledatatable_new" onclick="setUserRole();" class="btn btn-info glyphicon glyphicon-wrench">
										用户赋角色
                                        </a>
                                       <div class="btn-group pull-right">
                                       		
	                                        	<select id="pageSelect" name="pageSize">
													<option>5</option>
													<option selected="selected">10</option>
													<option>15</option>
													<option>20</option>
												</select>
                                        </div>
                                    </div>
                                    </form>
                                    <table class="table table-striped table-hover table-bordered" id="product_tab">
                                        <thead>
                                            <tr role="row">
                                            	<th></th>
                                            	<th>sid</th>
                                                <th>用户名</th>
                                                <th>真实姓名</th>
                                                <th>账户类型</th>
                                                <th>是否有效</th>
                                                <th>角色id</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div id="productPagination"></div>
                                </div>
								<!-- Templates -->
								<p style="display:none">
									<textarea id="product-list" rows="0" cols="0">
										<!--
										{#template MAIN}
											{#foreach $T.list as Result}
												<tr class="gradeX">
													<td align="left">
														<div class="checkbox">
															<label>
																<input type="checkbox" id="tdCheckbox_{$T.Result.sid}" value="{$T.Result.sid}" >
																<span class="text"></span>
															</label>
														</div>
													</td>
													<td id="sid_{$T.Result.sid}">
														{$T.Result.sid}
													</td>
													<td id="userName_{$T.Result.sid}">{$T.Result.userName}</td>
													<td id="realName_{$T.Result.sid}">{$T.Result.realName}</td>
													<td id="type_{$T.Result.sid}" value="{$T.Result.type}">
														{#if $T.Result.type==0}
															电果网用户
														{#elseif $T.Result.type == 1}
						           							物流专员
						                   				{#/if}
													</td>
													<td id="status_{$T.Result.sid}" value="{$T.Result.status}">
														{#if $T.Result.status==1}
														有效
														{#else}
						           						无效
						                   				{#/if}
													</td>
													<td id="roleSid_{$T.Result.sid}" value="{$T.Result.roleSid}">
														{#if $T.Result.roleSid ==null}
						           							未分配
						                      			{#else}
						           							{$T.Result.roleSid}
						                   				{#/if}
													</td>
									       		</tr>
											{#/for}
									    {#/template MAIN}	-->
									</textarea>
								</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /Page Body -->
            </div>
            <!-- /Page Content -->
        </div>
</body>
</html>