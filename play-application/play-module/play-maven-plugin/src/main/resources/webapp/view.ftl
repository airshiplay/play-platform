<section class="content-header">
	<h1>
		${functionShowName} <small>${functionShowName}</small>
		<input type="hidden" value="" id="${'${shortSimpleName}'?uncap_first}IdInput" th:value="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.id}" />
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i> Home</a></li>
		<li><a href="#page/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}List"><i class="glyphicon glyphicon-list"></i> ${functionShowName}列表</a></li>
		<li class="active" >查看${functionShowName}</li>
	</ol>
</section>

<!-- Main content -->
<section class="content" th:inline="text">
	<div class="mailbox-controls">
		<div class="btn-group btn-group-sm">
			<button aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" class="btn btn-primary dropdown-toggle" type="button">
				操作
				<span class="ion-arrow-down-b"></span>
			</button>
			<ul class="dropdown-menu pull-right">
				<li><a href="javascript:;" id="deleteButton">
						<i class="glyphicon glyphicon-remove"></i>移除
					</a></li>
			</ul>
		</div>
		<!-- /.btn-group -->
		<button type="button" class="btn btn-warning btn-sm" onclick="history.back(-1)">
			<i class="fa fa-reply"></i>
		</button>
	</div>
	<div class="row">
		<div class="col-md-4">

			<!-- Profile Image -->
			<div class="box box-primary">
				<div class="box-body box-profile">
					<#list fields as field>
						<#if field.formtype=="image">
					<img alt="picture" th:src="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.${field.fieldName}}" class="profile-user-img img-responsive img-rounded">
					</#if>
					</#list>
					<h3 class="profile-username text-center">***</h3>
					<dl>
						<#list fields as field>
						<#if field.fieldType="Date">
						<dt>${field.fieldShowName}</dt>
						<dd th:text="${r"${"}${'${shortSimpleName}'?uncap_first}==null||${'${shortSimpleName}'?uncap_first}.${field.fieldName}==null?'': #dates.format(${'${shortSimpleName}'?uncap_first}.${field.fieldName}, 'yyyy年MM月dd日 HH:mm')}"></dd>
						<#else>
						<dt>${field.fieldShowName!''}</dt>
						<dd th:text="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.${field.fieldName}}"></dd>
						</#if>
						</#list>
					</dl>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
		<div class="col-md-8">
			<div class="box box-solid">
				<div class="box-header with-border">
					<h3 class="box-title">内容描述</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body" style="min-height: 300px;">***</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
</section>

<script type="text/javascript">
	require([ "domReady" ], function(domReady) {
		domReady(function() {
			activeMenu("${moduleName}_${'${shortSimpleName}'?lower_case}_list");

			var ${'${shortSimpleName}'?uncap_first}Id = $("#${'${shortSimpleName}'?uncap_first}IdInput").val();

			//删除
			$("#deleteButton").on("click", function() {
				$.confirm({
					title : "提示",
					content : "确定删除${functionShowName}?",
					confirmButton : "确定",
					cancelButton : "关闭",
					icon : 'fa fa-warning',
					confirmButtonClass : 'btn-warning',
					animation : 'zoom',
					confirm : function() {
						$.post(base + "/${moduleName}/${'${shortSimpleName}'?uncap_first}/delete", {
							ids : [ [[${'${shortSimpleName}'?uncap_first}]]Id ]
						}, function(resp) {
							if (resp.success) {
								$.alert({
									title : false,
									content : "操作成功",
									confirmButton : "关闭"
								});
								location.href = "#page/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}List";
							} else {
								$.alert({
									title : false,
									content : resp.msg,
									confirmButton : "关闭"
								});
							}
						}, "json");
					}
				});
			});

		});
	});
</script>