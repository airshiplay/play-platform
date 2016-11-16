<section class="content-header">
	<h1>
		文章列表 <small>开始管理文章信息</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i> Home</a></li>
		<li><a href="#page/cms/article/articleList"><i class="fa fa-home"></i> 文章列表</a></li>
		<li class="active">新建文章</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">新建文章</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="book-form" action="/cms/article/save">
					<input type="hidden" name="id" value="${(article.id)!}" />
					<div class="box-body">
						<div class="form-group">
							<label for="titleInput">标题</label>
							<input type="text" name="title" class="form-control" id="titleInput" placeholder="请填写标题" data-bv-notempty="true"
								data-bv-notempty-message="请填写标题" value="${(article.title)!}">
						</div>
						<div class="form-group">
		                  <label for="articleCategoryInput">文章分类</label>
		                  <select id="articleCategoryInput" name="articleCategory" class="form-control">
		                   
		                  </select>
		                </div>
						<div class="form-group">
							<label for="coverImageInput">封面图片</label>
							<div class="kv-coverImage">
								<input id="coverImageInput" name="coverImage" type="text" class="file-loading">
								<input type="button" id="uploadButton" value="上传" />
							</div>
						</div>	
						<div class="form-group">
							<label for="authorInput">作者</label>
							<input type="text" name="author" class="form-control" id="authorInput" placeholder="请填写作者" data-bv-notempty="true"
								data-bv-notempty-message="请填写作者" value="${(article.author)!}">
						</div>
						<div class="form-group">
							<label for="tagsInput">标签</label>
			                  <div class="checkbox" id="tagsDiv">
			                    
			                  </div>
                		</div>
                		<div class="form-group">
							<label for="settingInput">设置</label>
							 <div class="checkbox">
			                    <label>
			                      <input type="checkbox" name="publication" class="minimal">
			                      是否发布
			                    </label>
			                    <label>
			                      <input type="checkbox" name="stick" class="minimal">
			                      是否置顶
			                    </label>
			                   </div>
                		</div>
		                <div class="form-group">
		                  <label for="summaryInput">摘要</label>
		                  <textarea name=summary class="form-control" id="summaryInput" rows="3" placeholder="Enter ..."></textarea>
		                </div>
		                <div class="form-group">
		                  <label for="contentInput">内容</label>
		                  <textarea name=content id=content class="form-control" id="contentInput" rows="3" style="height:140px;" placeholder="Enter ..."></textarea>
		                </div>
						<div class="form-group">
							<label for="seoTitleInput">SEO标题</label>
							<input type="text" name="seoTitle" class="form-control" id="seoTitleInput" placeholder="请填写SEO标题" data-bv-notempty="true"
								data-bv-notempty-message="请填写SEO标题" value="${(article.seoTitle)!}">
						</div>
						<div class="form-group">
							<label for="seoKeywordsInput">SEO关键字</label>
							<input type="text" name="seoKeywords" class="form-control" id="seoKeywordsInput" placeholder="请填写SEO关键字" data-bv-notempty="true"
								data-bv-notempty-message="请填写SEO关键字" value="${(article.seoKeywords)!}">
						</div>
						<div class="form-group">
							<label for="seoDescriptionInput">SEO描述</label>
							<input type="text" name="seoDescription" class="form-control" id="seoDescriptionInput" placeholder="请填写SEO描述" data-bv-notempty="true"
								data-bv-notempty-message="请填写SEO描述" value="${(article.seoDescription)!}">
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-default" onclick="location.href='#/page/cms/article/articleList'">返回列表</button>
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady","datetimepickerzh","select2zh","kindeditor","icheck" ], function(domReady) {
		domReady(function() {
			activeMenu("cms_article_list");
			var icheckStyle=function(){
				$('input[type="checkbox"].minimal').iCheck({
				      checkboxClass: 'icheckbox_minimal-blue',
				      radioClass: 'iradio_minimal-blue'
				});
			};
			icheckStyle();
			var $form = $(".book-form");
			//articleTag
			$.get(base+"/cms/articleTag/list", function(result){
				
				if(result.success){
					var html="";
					$.each(result.content,function(index,val) {
						html=html+"<label>\
	                      <input type=\"checkbox\" name=\"tags\" value="+val.id+" class=\"minimal\">\
	                      "+val.name+"\
	                    </label>";
					});
					$("#tagsDiv").html(html);
					icheckStyle();
				}
			    
			  });
			
			
		    //Date picker
		   	$("#articleCategoryInput").select2({
		    	  placeholder: '---请选择文章分类---',
		    	  ajax: {
		    	    url: base+"/cms/articleCategory/page",
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
		    var uploadbutton = KindEditor.uploadbutton({
					button : $('#uploadButton')[0],
					fieldName : 'imgFile',
					url : base+'/requirejs/kindeditor/upload?dir=image',
					afterUpload : function(data) {
						if (data.error === 0) {
							var url = KindEditor.formatUrl(data.url, 'absolute');
							$('#coverImageInput').val(url);
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
			window.editor = KindEditor.create('#content',{
				uploadJson : base+'/requirejs/kindeditor/upload',
				fileManagerJson : base+'/requirejs/kindeditor/list',
				allowFileManager : true,
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
								location.href = "#/page/cms/article/articleList";
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