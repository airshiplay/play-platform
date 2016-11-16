<section class="content-header">
	<h1>
		文章标签列表 <small>开始管理文章标签信息</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i> Home</a></li>
		<li><a href="#page/cms/articleTag/articleTagList"><i class="fa fa-home"></i> 文章标签列表</a></li>
		<li class="active">新建文章标签</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">新建文章标签</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="book-form" action="/cms/articleTag/save">
					<input type="hidden" name="id" value="${(articleTag.id)!}" />
					<div class="box-body">
						<div class="form-group">
							<label for="nameInput">名称</label>
							<input type="text" name="name" class="form-control" id="nameInput" placeholder="请填写名称" data-bv-notempty="true"
								data-bv-notempty-message="请填写名称" value="${(articleTag.name)!}">
						</div>
						<div class="form-group">
							<label for="iconImageInput">图标</label>
							<div class="kv-icon">
								<input id="iconInput" name="icon" type="text" class="file-loading">
								<input type="button" id="uploadButton" value="上传" />
							</div>
						</div>							
						<div class="form-group">
							<label for="memoInput">备注</label>
							<input type="text" name="memo" class="form-control" id="memoInput" value="${(articleTag.memo)!}">
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-default" onclick="location.href='#/page/cms/articleTag/articleTagList'">返回列表</button>
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady","datetimepickerzh","select2zh","kindeditor" ], function(domReady) {
		domReady(function() {
			activeMenu("cms_articleTag_list");
			var $form = $(".book-form");
		    //Date picker
		   		    var uploadbutton = KindEditor.uploadbutton({
					button : $('#uploadButton')[0],
					fieldName : 'imgFile',
					url : base+'/requirejs/kindeditor/upload?dir=image',
					afterUpload : function(data) {
						if (data.error === 0) {
							var url = KindEditor.formatUrl(data.url, 'domain');
							$('#iconInput').val(url);
						} else {
							alert(data.message);
						}
					},
					afterError : function(str) {
						alert('自定义错误信息: ' + str);
					}
				});
			uploadbutton.fileBox.change(function(e) {
				uploadbutton.submit();
			});
			$(".book-form").bootstrapValidator().on('success.form.bv', function(e) {
				e.preventDefault();

				var $form = $(e.target);

				var bv = $form.data('bootstrapValidator');
				console.log("");
				$.post($form.attr('action'), $form.serialize(), function(result) {
					if (result.success) {
						$.alert({
							title : false,
							closeIcon: true,
							content : "保存成功",
							confirmButton : "返回列表",
							confirm : function() {
								location.href = "#/page/cms/articleTag/articleTagList";
							}
						});

					} else {
						$.alert({
							title : false,
							content : result.msg,
							
							
						});
						$form.bootstrapValidator('disableSubmitButtons', false);
					}
				}, 'json');
			});
		});
	});
</script>