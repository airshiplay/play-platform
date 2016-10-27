
<link type="text/css" rel="stylesheet" href="//cdn.bootcss.com/bootstrap-fileinput/4.3.3/css/fileinput.min.css">
<link type="text/css" rel="stylesheet" href="//cdn.bootcss.com/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.min.css">

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
		个人信息 <small>${user.name!''}</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:;">
				<i class="fa fa-home"></i> Home
			</a></li>
		<li class="active"><a href="#/page/user">
				<i class="fa fa-user"></i>个人信息
			</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary">
				<div id="kv-avatar-errors-1" style="width: 100%; display: none"></div>
				<!-- form start -->
				<form role="form" class="user-form" action="/center/account/save">
					<input type="hidden" name="id" value="${user.id}" />
					<div class="box-body">
						<div class="form-group">
							<label for="nameInput">姓名</label>
							<input type="text" name="name" class="form-control" id="nameInput" placeholder="请填写姓名" data-bv-notempty="true" data-bv-notempty-message="请填写姓名"
								value="${user.name!''}">
						</div>
						<div class="form-group">
							<label for="avatarInput">头像</label>
							<div class="kv-avatar">
								<input name="avatar" type="hidden" value="${user.photo!''}">
								<input id="avatarInput" name="file" type="file" class="file-loading">
							</div>
						</div>
						<div class="form-group">
							<label>性别</label>
							<div class="input-group radio">
								<label> <input type="radio" value="boy" name="sex">男
								</label> &nbsp; &nbsp; <label> <input type="radio" value="girl" name="sex">女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="birthdayInput">出生日期</label>

							<div class="input-group date">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" id="birthdayInput" name="birthday" value="" class="form-control pull-right">
							</div>
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>

			<div class="box box-primary">
				<div class="box-body">
					<div class="form-group">
						<label for="mobileInput">手机号码</label>
						<div>
							
							<a href="javascript:;">绑定</a>
						</div>
					</div>
					<div class="form-group">
						<label for="emailInput">邮箱</label>
						<div>
							
							<a href="javascript:;">绑定</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady", "fileinputzh", "datepickerzh" ], function(domReady) {
		domReady(function() {
			activeMenu("user");

		    $('#birthdayInput').datepicker({
		      autoclose: true,
		      language: 'zh-CN'
		    });
			 
			var userAvatar = "${user.photo!''}";
			
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

			$(".user-form").bootstrapValidator().on('success.form.bv', function(e) {
				e.preventDefault();

				var $form = $(e.target);

				var bv = $form.data('bootstrapValidator');

				$.post($form.attr('action'), $form.serialize(), function(result) {
					if (result.success) {
						$(".user-image").attr("src", $("input[name=avatar]").val());
						$.alert({
							title : false,
							content : "保存成功",
							confirmButton : "确定"
						});
					} else {

						$form.bootstrapValidator('disableSubmitButtons', false);
					}
				}, 'json');
			});
		});
	});
</script>