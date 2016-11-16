
<link type="text/css" rel="stylesheet" href="//cdn.bootcss.com/bootstrap-fileinput/4.3.3/css/fileinput.min.css">

<section class="content-header">
	<h1>
		修改密码 <small>李根@sh</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:;">
				<i class="fa fa-home"></i> Home
			</a></li>
		<li class="active"><a href="#/page/account/password">
				<i class="fa fa-key"></i>修改密码
			</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<!-- form start -->
				<form role="form" class="user-form" action="/center/account/password">
					<input type="hidden" name="id" value="45" />
					<div class="box-body">
						<div class="form-group">
							<label for="passwordInput">原密码</label>
							<input type="password" class="form-control" id="passwordInput" name="password" autocomplete="off" placeholder="请输入密码，如无密码则留空">
						</div>
						<div class="form-group">
							<label for="newPasswordInput">新密码</label>
							<input type="password" class="form-control" id="newPasswordInput" name="newPassword" autocomplete="off" placeholder="请输入新密码"
								data-bv-notempty="true" data-bv-notempty-message="请填写密码" value="">
						</div>
						<div class="form-group">
							<label for="repeatPasswordInput">重复密码</label>
							<input type="password" class="form-control" id="repeatPasswordInput" name="repeatPassword" autocomplete="off" placeholder="重复输入新密码"
								data-bv-notempty="true" data-bv-notempty-message="请填写重复密码" data-bv-identical="true" data-bv-identical-field="newPassword"
								data-bv-identical-message="与密码不一致" value="">
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady" ], function(domReady) {
		domReady(function() {
			activeMenu("password");

			$(".user-form").bootstrapValidator().on('success.form.bv', function(e) {
				e.preventDefault();

				var $form = $(e.target);

				var bv = $form.data('bootstrapValidator');

				$.post($form.attr('action'), $form.serialize(), function(result) {
					if (result.success) {
						$.alert({
							title : false,
							content : "保存成功",
							confirmButton : "确定"
						});

					} else {
						$.alert({
							title : false,
							content : result.msg,
							confirmButton : "确定"
						});
						$form.bootstrapValidator('disableSubmitButtons', false);
					}
				}, 'json');
			});
		});
	});
</script>