<link type="text/css" rel="stylesheet" href="bootstrap/vendor/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
<link type="text/css" rel="stylesheet" href="bootstrap/vendor/plugins/select2/select2.min.css">
<link type="text/css" rel="stylesheet" href="bootstrap/vendor/plugins/bootstrap-fileinput/css/fileinput.min.css">

<#list fields as field>
<#if field.fieldName?contains("icon") || field.fieldName?contains("Icon") ||field.fieldName?contains("image")||field.fieldName?contains("Image")>
<style>
.kv-${field.fieldName} .file-preview-frame, .kv-${field.fieldName} .file-preview-frame:hover {
	margin: 0;
	padding: 0;
	border: none;
	box-shadow: none;
	text-align: center;
}
.kv-${field.fieldName} .file-input {
	display: table-cell;
	max-width: 220px;
}
</style>
</#if>
</#list>
<section class="content-header">
	<h1>
		${functionShowName}列表 <small>开始管理${functionShowName}</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i> Home</a></li>
		<li><a href="#page/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}List"><i class="glyphicon glyphicon-list"></i> ${functionShowName}列表</a></li>
		<li class="active" th:text="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'新建${functionShowName}':'编辑${functionShowName}'}">新建${functionShowName}</li>
	</ol>
</section>
<!-- Main content -->
<section class="content" th:inline="text">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title" th:text="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'新建${functionShowName}':'编辑${functionShowName}'}">新建${functionShowName}</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="${'${shortSimpleName}'?lower_case}-form" action="/${moduleName}/${'${shortSimpleName}'?uncap_first}/save">
					<input type="hidden" name="id" th:value="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.id}" />
					<div class="box-body">
						<#list fields as field>
						<#if field.formtype="date">
						<div class="form-group">
			                <label for="${field.fieldName}Input">${field.fieldShowName}</label>
			                <div class="input-group date" id="${field.fieldName}picker">
			                  <div class="input-group-addon">
			                    <i class="fa fa-calendar"></i>
			                  </div>
			                  <input type="text" class="form-control pull-right"  name="${field.fieldName}" <#if field.notnull?exists&&field.notnull > data-bv-notempty="true" data-bv-notempty-message="请填写${field.fieldName}"</#if> th:value="${r"${"}${'${shortSimpleName}'?uncap_first}==null||${'${shortSimpleName}'?uncap_first}.${field.fieldName}==null?'': #dates.format(${'${shortSimpleName}'?uncap_first}.${field.fieldName}, 'yyyy年MM月dd日 HH:mm')}"/>
			                </div>
			            </div>
						<#elseif field.formtype="enum">
						<div class="form-group">
		                  <label for="${field.fieldName}Input">${field.fieldShowName}</label>
		                  <select id="${field.fieldName}Input" name="type" class="form-control" <#if field.notnull?exists&&field.notnull > data-bv-notempty="true" data-bv-notempty-message="请选择${field.fieldName}"</#if>
								data-bv-notempty-message="请选择${field.fieldShowName}" >
		                  </select>
		                </div>
		                <#elseif field.formtype="select2">
						<div class="form-group">
		                  <label for="${field.fieldName}Input">${field.fieldShowName}</label>
		                  <select id="${field.fieldName}Input" name="type" class="form-control" <#if field.notnull?exists&&field.notnull > data-bv-notempty="true" data-bv-notempty-message="请选择${field.fieldName}"</#if>
								data-bv-notempty-message="请选择${field.fieldShowName}" >
		                  </select>
		                </div>
		                <#elseif field.formtype="image">
		                <div class="form-group">
							<label for="${field.fieldName}Input">${field.fieldShowName}</label>
							<input name="${field.fieldName}" type="hidden" th:value="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.${field.fieldName}}">
							<input id="${field.fieldName}Input" name="file" type="file" class="file-loading">
						</div>
						<#elseif field.formtype="textarea">
						<div class="form-group">
							<label for="${field.fieldName}Input">${field.fieldShowName}</label>
							<textarea type="text" name="${field.fieldName}" class="form-control" id="${field.fieldName}Input" rows="3" placeholder="请填写${field.fieldName}"
								<#if field.notnull?exists&&field.notnull > data-bv-notempty="true" data-bv-notempty-message="请填写${field.fieldName}"</#if><#if field.minSize?exists>data-bv-stringlength="true" data-bv-stringlength-min="${field.minSize}" data-bv-stringlength-max="${field.maxSize}" data-bv-stringlength-message="个数必须在${field.minSize}和${field.maxSize}之间"
								<#elseif field.size?exists>data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="${field.size}" data-bv-stringlength-message="个数必须在0和${field.size}之间"</#if>
		                  		th:text="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.${field.fieldName}}"/>
						</div>
						<#else>
						<div class="form-group">
							<label for="${field.fieldName}Input">${field.fieldShowName!''}</label>
							<input type="text" name="${field.fieldName}" class="form-control" id="${field.fieldName}Input" placeholder="请填写${field.fieldName}"
								<#if field.notnull?exists&&field.notnull > data-bv-notempty="true" data-bv-notempty-message="请填写${field.fieldName}"</#if><#if field.minSize?exists>data-bv-stringlength="true" data-bv-stringlength-min="${field.minSize}" data-bv-stringlength-max="${field.maxSize}" data-bv-stringlength-message="个数必须在${field.minSize}和${field.maxSize}之间"
								<#elseif field.size?exists>data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="${field.size}" data-bv-stringlength-message="个数必须在0和${field.size}之间"</#if>
		                  		th:value="${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.${field.fieldName}}"/>
						</div>
						</#if>
						</#list>
						
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-default" onclick="location.href='#/page/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}List'">返回列表</button>
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady","datetimepickerzh","select2zh","fileinputzh","kindeditor"], function(domReady) {
		domReady(function() {
			activeMenu("${moduleName}_${'${shortSimpleName}'?lower_case}_list");
			
			<#list fields as field>
			<#if field.formtype="date">
			$('#${field.fieldName}picker').datetimepicker({
		    	format: "yyyy年mm月dd日 hh:ii",
		    	initialDate:"[[${r"${"}${'${shortSimpleName}'?uncap_first}==null||${'${shortSimpleName}'?uncap_first}.${field.fieldName}==null?'': #dates.format(${'${shortSimpleName}'?uncap_first}.${field.fieldName}, 'yyyy年MM月dd日 HH:mm')}]]",
		      	autoclose: true
		    });
		    <#elseif field.formtype="enum">
		    $${field.fieldName}Select=$("#${field.fieldName}Input").select2({
		    	placeholder: '---请选择${field.fieldShowName}---',
		    	data:[<#list field.enumList as e>{id:'${e}',text:'${e}'},</#list>]
		    });${r"//{id:' ',text:' '}"}
		    $${field.fieldName}Select.val("[[${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.${field.fieldName}}]]").trigger("change");
		    <#elseif field.formtype="select2">
		    $("#${field.fieldName}Input").select2({
				placeholder : '---请选择${field.fieldShowName}---',
				initSelection : function(element, callback) { // 初始化时设置默认值  	
					var data = [];
					$(element.find("option")).each(function() {
						data.push({
							id : $(this).val(),
							text : $(this).text()
						});
					});
					callback(data);
				},
				ajax : {
					url : base + '${field.select2Url}',
					type : 'POST',
					dataType : 'json',
					data : function(params) {
						return {
							searchPhrase : params.term, // search term
							page : params.page
						};
					},
					processResults : function(data, params) {
						params.page = params.page || 1;
						$.each(data.rows, function(i, val) {
							val.text = val.name;
						});
						return {
							results : data.rows,
							pagination : {
								more : (params.page * 20) < data.total
							}
						};
					},
					cache : true
				}
			});
		    <#elseif field.formtype="image">
			var ${field.fieldName}Icon = "[[${r"${"}${'${shortSimpleName}'?uncap_first}==null?'':${'${shortSimpleName}'?uncap_first}.${field.fieldName}}]]";
			var $${field.fieldName}Input = $("#${field.fieldName}Input");
			$${field.fieldName}Input.fileinput({
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
				elErrorContainer : '#kv-${field.fieldName}-errors-1',
				msgErrorClass : 'alert alert-block alert-danger',
				defaultPreviewContent : ${field.fieldName}Icon&&${field.fieldName}Icon!="" ?"<img src='"+${field.fieldName}Icon+"' alt='头像' style='width:160px'>":"<img src='' alt='头像' style='width:160px'>",
				allowedFileTypes : [ "image" ]
			}).on("filebatchselected", function(event, files) {
				$${field.fieldName}Input.fileinput("upload");
			}).on('fileuploaded', function(event, data, previewId, index) {
				var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
				if (response.success) {
					var $form = $(".${'${shortSimpleName}'?lower_case}-form");
					$form.find("input[name=${field.fieldName}]").val(KindEditor.formatUrl(response.urls, 'absolute'));
				}
			});
			</#if>
		    </#list>

			var $form = $(".${'${shortSimpleName}'?lower_case}-form");		  
			$(".${'${shortSimpleName}'?lower_case}-form").bootstrapValidator().on('success.form.bv', function(e) {
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
								location.href = "#/page/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}List";
							}
						});

					} else {
						$.alert({
							title : false,
							content : result.msg,
							confirmButton : "关闭"
						});
						$form.bootstrapValidator('disableSubmitButtons', false);
					}
				}, 'json');
			});
		});
	});
</script>