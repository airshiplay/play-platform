<section class="content-header">
	<h1>
		我的应用 <small>阳光欣晴-做最专业的诊后管理平台</small>
		<input type="hidden" value="17" id="appIdInput" />
	</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:;">
				<i class="fa fa-dashboard"></i> 工作台
			</a></li>
		<li><a href="#/page/app/app/list">
				<i class="fa fa-cloud"></i>我的应用
			</a></li>
		<li class="active">${app.name}</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="mailbox-controls">
		<div class="btn-group btn-group-sm">
			<button type="button" class="btn btn-primary">安装到设备</button>
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
					<img alt="User profile picture" src="${app.iconPath}" class="profile-user-img img-responsive img-rounded">
					<h3 class="profile-username text-center">${app.name}</h3>
					<p class="text-muted text-center">${app.platform}</p>

					<dl>
						<dt>标识</dt>
						<dd>${app.identifier}</dd>
						<dt>版本</dt>
						<dd>${app.version}</dd>
						<dt>大小</dt>
						<dd>${app.fileSizeBytes}</dd>
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
				<div class="box-body" style="min-height: 300px;">${app.description}</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->

			<div class="box box-solid">
				<div class="box-header with-border">
					<h3 class="box-title">应用图片</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<#list app.photos as photo>
								<#if photo_index==0>
									<li data-slide-to="${photo_index}" class="active"></li>
								<#else>
								<li data-slide-to="${photo_index}"></li>
								</#if>	
							
							</#list>
						</ol>
						<div class="carousel-inner">
							<#list app.photos as photo>
							<#if photo_index==0>
								<div class="item active">
								<img src="${photo}" alt="${photo_index}" style="margin: 0 auto;" class="img-responsive text-center">
								</div>
							<#else>
							<div class="item">
								<img src="${photo}" alt="${photo_index}" style="margin: 0 auto;" class="img-responsive text-center">
							</div>
							</#if>
							</#list>

						</div>
						<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
							<span class="fa fa-angle-left"></span>
						</a>
						<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
							<span class="fa fa-angle-right"></span>
						</a>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
		</div>
		<!-- /.col -->
	</div>
</section>

<script type="text/javascript">
	require([ "domReady" ], function(domReady) {
		domReady(function() {
			activeMenu("app");

			var appId = $("#appIdInput").val();

			//删除
			$("#deleteButton").on("click", function() {
				$.confirm({
					title : "提示",
					content : "确定删除应用?",
					confirmButton : "确定",
					cancelButton : "关闭",
					icon : 'fa fa-warning',
					confirmButtonClass : 'btn-warning',
					animation : 'zoom',
					confirm : function() {
						$.post(base + "/center/app/delete", {
							ids : [ deviceId ]
						}, function(resp) {
							if (resp.success) {
								$.alert({
									title : false,
									content : "操作成功",
									confirmButton : "关闭"
								});
								location.href = "#/page/app";
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