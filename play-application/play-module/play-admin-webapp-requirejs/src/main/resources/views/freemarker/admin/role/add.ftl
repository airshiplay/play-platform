<link type="text/css" rel="stylesheet" href="//cdn.bootcss.com/select2/4.0.3/css/select2.css">
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
		角色列表 <small>开始管理系统角色</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:;">
				<i class="fa fa-home"></i> Home
			</a></li>
		<li><a href="#/page/center/role/list">
				<i class="fa fa-gavel"></i>角色列表
			</a></li>
		<li class="active">新增角色</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">新增角色</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="book-form" action="/center/role/save">
					<input type="hidden" name="id" value="" />
					<input type="hidden" name="kind" value="" />
					<input type="hidden" name="type" value="Book" />
					<div class="box-body">
						<div class="form-group">
							<label for="nameInput">角色名</label>
							<input type="text" name="name" class="form-control" id="nameInput" placeholder="请填写角色名" data-bv-notempty="true"
								data-bv-notempty-message="请填写角色名" value="">
						</div>
						<div class="form-group">
							<label for="codeInput">标识</label>
							<input type="text" name="code" class="form-control" id="codeInput" placeholder="请填写昵称" data-bv-notempty="true" data-bv-notempty-message="请填写昵称" value="">
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-default" onclick="location.href='#/page/center/role/list'">返回列表</button>
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
			activeMenu("book");
			var $form = $(".book-form");
			
			

			$(".book-form").bootstrapValidator().on('success.form.bv', function(e) {
				e.preventDefault();

				var $form = $(e.target);

				var bv = $form.data('bootstrapValidator');
				console.log("");
				$.post($form.attr('action'), $form.serialize(), function(result) {
					if (result.success) {
						$.alert({
							title : false,
							content : "保存成功",
							confirmButton : "返回列表",
							confirm : function() {
								location.href = "#/page/center/role/list";
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