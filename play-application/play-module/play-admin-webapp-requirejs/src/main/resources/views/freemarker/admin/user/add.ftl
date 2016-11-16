<link type="text/css" rel="stylesheet" href="//cdn.bootcss.com/bootstrap-fileinput/4.3.3/css/fileinput.min.css">
<style>
.kv-avatar .file-preview-frame, .kv-avatar .file-preview-frame:hover {
	margin: 0;
	padding: 0;
	border: none;
	box-shadow: none;
	text-align: center;
}

.kv-avatar .file-input {
	display: table-cell;
	max-width: 220px;
}
</style>

<section class="content-header">
	<h1>
		用户列表 <small>开始管理系统用户</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:;">
				<i class="fa fa-home"></i> Home
			</a></li>
		<li><a href="#/page/user/list">
				<i class="fa fa-user"></i>用户列表
			</a></li>
		<li class="active">新增用户</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">新增用户</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="book-form" action="/center/user/save">
					<input type="hidden" name="id" value="" />
					<input type="hidden" name="kind" value="" />
					<input type="hidden" name="type" value="Book" />
					<div class="box-body">
						<div class="form-group">
							<label for="usernameInput">用户名</label>
							<input type="text" name="username" class="form-control" id="usernameInput" placeholder="请填写用户名" data-bv-notempty="true"
								data-bv-notempty-message="请填写用户名" value="">
						</div>
						<div class="form-group">
							<label for="nameInput">昵称</label>
							<input type="text" name="name" class="form-control" id="nameInput" placeholder="请填写昵称" data-bv-notempty="true" data-bv-notempty-message="请填写昵称" value="">
						</div>
						<div class="form-group">
							<label for="avatarInput">头像</label>
							<div class="kv-avatar">
								<input name="avatar" type="hidden" value="http://q.qlogo.cn/qqapp/101312453/F4F1854A872AC1B2AC58AF619797A8F1/100">
								<input id="avatarInput" name="file" type="file" class="file-loading">
							</div>
						</div>
						
						<div class="form-group">
							<label for="emailInput">邮箱</label>
							<input type="text" name="email" class="form-control" id="emailInput" placeholder="请填写邮箱" value="">
						</div>
						<div class="form-group">
							<label for="newPasswordInput">密码</label>
							<input type="password" name="newPassword" class="form-control" id="newPasswordInput" placeholder="请填写密码" value="">
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-default" onclick="location.href='#/page/center/user/list'">返回列表</button>
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady", "fileinputzh" ], function(domReady) {
		domReady(function() {
			activeMenu("book");

			var $form = $(".book-form");
			
			var getFileExt = function(filepath) {
	            if (filepath != "") {
	                var pos = "." + filepath.replace(/.+\./, "");
	                return pos;
	            }
	        }
			

			var userAvatar = "";
			
			var $avatarInput = $("#avatarInput");
			$avatarInput.fileinput({
				overwriteInitial : true,
				language : "zh",
				uploadUrl : base + "/center/multipart/upload",
				maxFileSize : 1500,
				showClose : false,
				showCaption : false,
				multiple : false,
				showUpload : false, // hide upload button
				showRemove : false, // hide remove button
				minFileCount : 1,
				maxFileCount : 1,
				elErrorContainer : '#kv-avatar-errors-1',
				msgErrorClass : 'alert alert-block alert-danger',
				defaultPreviewContent : userAvatar&&userAvatar!="" ?"<img src='"+userAvatar+"' alt='头像' style='width:160px'>":"<img src='"+base+"/assets/img/user2-160x160.jpg' alt='头像' style='width:160px'>",
				allowedFileTypes : [ "image" ]
			}).on("filebatchselected", function(event, files) {
				$avatarInput.fileinput("upload");
			}).on('fileuploaded', function(event, data, previewId, index) {
				var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
				if (response.success) {
					var $form = $(".user-form");
					$form.find("input[name=avatar]").val(response.urls);
				}
			});;

			$(".book-form").bootstrapValidator().on('success.form.bv', function(e) {
				e.preventDefault();

				var $form = $(e.target);

				var bv = $form.data('bootstrapValidator');

				$.post($form.attr('action'), $form.serialize(), function(result) {
					if (result.success) {
						$.alert({
							title : false,
							content : "保存成功",
							confirmButton : "返回列表",
							confirm : function() {
								location.href = "#/page/center/user/list";
							}
						});

					} else {
						$form.bootstrapValidator('disableSubmitButtons', false);
					}
				}, 'json');
			});
		});
	});
</script>