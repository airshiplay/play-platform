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
		组织机构列表 <small>开始管理组织机构</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:;">
				<i class="fa fa-home"></i> Home
			</a></li>
		<li><a href="#/page/center/org/list">
				<i class="fa fa-gavel"></i>组织机构列表
			</a></li>
		<li class="active">新增组织机构</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">新增组织机构</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="book-form" action="/center/org/save">
					<input type="hidden" name="id" value="" />
					<div class="box-body">
						<div class="form-group">
							<label for="nameInput">组织机构名称</label>
							<input type="text" name="name" class="form-control" id="nameInput" placeholder="请填写组织机构名称" data-bv-notempty="true"
								data-bv-notempty-message="请填写组织机构名称" value="">
						</div>
						<div class="form-group">
							<label for="codeInput">组织机构代号</label>
							<input type="text" name="code" class="form-control" id="codeInput" placeholder="请填写组织机构代号" data-bv-notempty="true" data-bv-notempty-message="请填写组织机构代号" value="">
						</div>
						<div class="form-group">
							<label for="parentInput">上级组织机构</label>
							<select id="parentInput" name="parent" class="form-control"></select>
						</div>
						<div class="form-group">
							<label for="typeInput">组织机构类型</label>
							<select id="typeInput" name="type" class="form-control"></select>
						</div>
						<div class="form-group">
							<label for="phoneInput">电话</label>
							<input type="text" name="phone" class="form-control" id="phoneInput" value="">
						</div>
						<div class="form-group">
							<label for="emailInput">邮箱</label>
							<input type="text" name="email" class="form-control" id="emailInput" value="" data-bv-regexp="true" data-bv-regexp-regexp="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+">
						</div>
						<div class="form-group">
							<label for="faxInput">传真</label>
							<input type="text" name="fax" class="form-control" id="faxInput" value="">
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-default" onclick="location.href='#/page/center/org/list'">返回列表</button>
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady","select2zh" ], function(domReady) {
		domReady(function() {
			activeMenu("book");
			var $form = $(".book-form");
			
			$("#typeInput").select2({
				data:[{id:'company',text:'公司'},{id:'department',text:'部门'},{id:'group',text:'组'}]
			});
			console.log('');
			$("#parentInput").select2({
				placeholder: '---请选择上级---',
				allowClear: true,
				ajax: {
		    	    url: base+"/center/org/treeList",
		    	    dataType: 'json',	    	     
		    	      processResults: function (data, params) {    	  
		    	      return {
		    	        results: data.rows		    	                 
		    	      };
		    	    },  
		    	    cache: true
		    	  },
		    	  escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
		    	  templateResult: formatRepo, // omitted for brevity, see the source of this page
		    	  templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
			});
			   function formatRepo (repo) {
				      if (repo.loading) return repo.text;
					var markup =""+repo.name;
				     /*  var markup = "<div class='select2-result-repository clearfix'>" +
				        "<div class='select2-result-repository__avatar'><img src='" + repo.owner.avatar_url + "' /></div>" +
				        "<div class='select2-result-repository__meta'>" +
				          "<div class='select2-result-repository__title'>" + repo.full_name + "</div>";

				      if (repo.description) {
				        markup += "<div class='select2-result-repository__description'>" + repo.description + "</div>";
				      }

				      markup += "<div class='select2-result-repository__statistics'>" +
				        "<div class='select2-result-repository__forks'><i class='fa fa-flash'></i> " + repo.forks_count + " Forks</div>" +
				        "<div class='select2-result-repository__stargazers'><i class='fa fa-star'></i> " + repo.stargazers_count + " Stars</div>" +
				        "<div class='select2-result-repository__watchers'><i class='fa fa-eye'></i> " + repo.watchers_count + " Watchers</div>" +
				      "</div>" +
				      "</div></div>"; */

				      return markup;
				    }

				    function formatRepoSelection (repo) {
				      return repo.name || repo.text;
				    }
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
								location.href = "#/page/center/org/list";
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