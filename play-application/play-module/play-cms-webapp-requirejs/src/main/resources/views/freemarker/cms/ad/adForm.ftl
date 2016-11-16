<section class="content-header">
	<h1>
		广告列表 <small>开始管理广告信息</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i> Home</a></li>
		<li><a href="#page/cms/ad/adList"><i class="fa fa-home"></i> 广告列表</a></li>
		<li class="active">新建广告</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">新建广告</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="book-form" action="/cms/ad/save">
					<input type="hidden" name="id" value="${(ad.id)!}" />
					<div class="box-body">
						<div class="form-group">
		                  <label for="adPostionInput">广告位选择</label>
		                  <select id="adPostionInput" name="adPosition" class="form-control">
		                   <!--  <option value="1">option 1</option>
		                    <option>option 2</option>
		                    <option>option 3</option>
		                    <option>option 4</option>
		                    <option>option 5</option> -->
		                  </select>
		                </div>
						<div class="form-group">
							<label for="titleInput">标题</label>
							<input type="text" name="title" class="form-control" id="titleInput" placeholder="请填写广告标题" data-bv-notempty="true"
								data-bv-notempty-message="请填写广告标题" value="${(ad.title)!}">
						</div>
						<div class="form-group">
		                  <label for="typeInput">类型</label>
		                  <select id="typeInput" name="type" class="form-control">
		                    <!-- <option value="text">文本</option>
		                    <option value="code">代码</option>
		                    <option value="image">图片</option>
		                    <option value="flash">动画</option>
		                    <option value="video">视频</option> -->
		                  </select>
		                </div>
		                <div class="form-group">
		                  <label for="contentInput">内容</label>
		                  <textarea name="content" class="form-control" rows="3" placeholder="Enter ..."></textarea>
		                </div>
						<div class="form-group">
							<label for="pathInput">路径</label>
							<div class="kv-path">
								<input id="pathInput" name="path" type="text" class="file-loading">
								<input type="button" id="uploadButton" value="上传" />
							</div>
						</div>		                
						<div class="form-group">
			                <label>开始时间</label>
			                <div class="input-group date" id="begindatepicker">
			                  <div class="input-group-addon">
			                    <i class="fa fa-calendar"></i>
			                  </div>
			                  <input type="text" class="form-control pull-right"  name="beginDate">
			                </div>
			                <!-- /.input group -->
			            </div>
						<div class="form-group">
			                <label>结束时间</label>
			                <div class="input-group date" id="enddatepicker">
			                  <div class="input-group-addon">
			                    <i class="fa fa-calendar"></i>
			                  </div>
			                  <input type="text" class="form-control pull-right" id="datepicker" name="endDate">
			                </div>
			                <!-- /.input group -->
			            </div>
			            <div class="form-group">
							<label for="sortNoInput">排序</label>
							<input type="text" name="sortNo" class="form-control" id="titleInput">
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
	require([ "domReady","kindeditor","datetimepickerzh","select2zh" ], function(domReady) {
		domReady(function() {
			activeMenu("cms_ad_list");
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
		    $type=$("#typeInput").select2({
		    	placeholder: '---请选择类型---',
		    	data:[{id:'text',text:'文本'},
		    	{id:'code',text:'代码'},
		    	{id:'image',text:'图片'},
		    	{id:'flash',text:'动画'},
		    	{id:'video',text:'视频'}]
		    });
		    
			
			//console.log("");
				var uploadbutton = KindEditor.uploadbutton({
					button : $('#uploadButton')[0],
					fieldName : 'imgFile',
					url : base+'/requirejs/kindeditor/upload?dir='+$type.select2("val"),
					afterUpload : function(data) {
						if (data.error === 0) {
							var url = KindEditor.formatUrl(data.url, 'absolute');
							$('#pathInput').val(url);
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
							content : "保存成功",
							confirmButton : "返回列表",
							confirm : function() {
								location.href = "#/page/cms/ad/adList";
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