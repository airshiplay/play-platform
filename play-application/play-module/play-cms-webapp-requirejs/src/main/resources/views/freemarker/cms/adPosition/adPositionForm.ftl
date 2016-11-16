<section class="content-header">
	<h1>
		广告位列表 <small>开始管理广告位信息</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i> Home</a></li>
		<li><a href="#page/cms/adPosition/adPositionList"><i class="fa fa-home"></i> 广告位列表</a></li>
		<li class="active">新建广告</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">新建广告位</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="book-form" action="/cms/adPosition/save">
					<input type="hidden" name="id" value="${(adPosition.id)!}" />
					<div class="box-body">
						<div class="form-group">
							<label for="nameInput">名称</label>
							<input type="text" name="name" class="form-control" id="nameInput" placeholder="请填写广告位名称" data-bv-notempty="true"
								data-bv-notempty-message="请填写广告位名称" value="${(adPosition.name)!}">
						</div>
						<div class="form-group">
							<label for="codeInput">代号</label>
							<input type="text" name="code" class="form-control" id="codeInput" placeholder="请填写广告位代号" data-bv-notempty="true"
								data-bv-notempty-message="请填写广告位代号" value="${(adPosition.code)!}">
						</div>
						<div class="form-group">
							<label for="widthInput">宽度</label>
							<input type="text" name="width" class="form-control" id="widthInput" placeholder="请填写广告位宽度" data-bv-notempty="true"
								data-bv-notempty-message="请填写广告位宽度" value="${(adPosition.width)!}">
						</div>
						<div class="form-group">
							<label for="heightInput">高度</label>
							<input type="text" name="height" class="form-control" id="heightInput" placeholder="请填写广告位高度" data-bv-notempty="true"
								data-bv-notempty-message="请填写广告位高度" value="${(adPosition.height)!}">
						</div>
		                <div class="form-group">
		                  <label for="descriptionInput">描述</label>
		                  <textarea name=description class="form-control" id="descriptionInput" rows="3" placeholder="Enter ..."></textarea>
		                </div>
						<div class="form-group">
		                  <label for="templateInput">模板</label>
		                  <textarea name=template class="form-control" id="templateInput" rows="3" placeholder="Enter ..."></textarea>
		                </div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-default" onclick="location.href='#/page/cms/ad/adList'">返回列表</button>
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady","datetimepickerzh","select2zh" ], function(domReady) {
		domReady(function() {
			activeMenu("cms_adposition_list");
			var $form = $(".book-form");
		    //Date picker
		    $('#begindatepicker').datetimepicker({
		    	format: "yyyy年mm月dd日 hh:ii",
		      	autoclose: true
		    });
		    $('#enddatepicker').datetimepicker({
		    	format: "yyyy年mm月dd日 hh:ii",
		      	autoclose: true
		    });
		    $("#adPostionInput").select2({
		    		placeholder: '---请选择广告位---',
		    	  ajax: {
		    	    url: base+"/cms/adPosition/page",
		    	    dataType: 'json',
		    	    data: function (params) {
		    	        return {
		    	          searchPhrase: params.term, // search term
		    	          page: params.page
		    	        };
		    	      },
		    	    processResults: function (data, params) {
		    	    	console.log("");
		    	    	 params.page = params.page || 1;
		    	    	 $.each(data.rows, function(i,val){      
		    	    		 val.text=val.name;
		    	    	  });
		    	      return {
		    	        results: data.rows,
		    	        pagination: {
		    	            more: (params.page * 20) < data.total
		    	          }		    	       
		    	      };
		    	    },
		    	    cache: true
		    	  }
		    });
		    $("#typeInput").select2();
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
								location.href = "#/page/cms/adPosistion/adPositionList";
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