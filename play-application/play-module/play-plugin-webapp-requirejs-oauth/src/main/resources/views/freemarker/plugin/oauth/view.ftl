<section class="content-header">
	<h1>
		OAuth认证 <small>开始管理OAuth认证</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i>
				Home</a></li>
		<li class="active">OAuth认证</li>
	</ol>
</section>

<section class="content">

	<div role="form" class="app-form" >
		 
		   
		   <div class="form-group">
				<label for="nameInput">OAuth插件名称</label> <input type="text"
					name="name" class="form-control"
					id="nameInput" data-bv-notempty="true" readonly="readonly"
					data-bv-notempty-message="" placeholder="client_id"
					value="${(plugin.name)!}">
			</div>
			<div class="form-group">
				<label for="avatarInput">插件图标</label>
				<div class="kv-avatar">
				<img alt="插件图标" src="${(plugin_config.attributes.icon)!''}">
					
				</div>
			</div>
			<div class="form-group">
				<label>启用</label>
				<div class="input-group radio">
				<#if (plugin_config.isEnabled)?exists && plugin_config.isEnabled>
					<label> <input type="radio" value="false" disabled="disabled" readonly="readonly" name="isEnabled"  >禁用
					</label> &nbsp; &nbsp; <label> 
					<input type="radio" value="true" readonly="readonly" disabled="disabled" name="isEnabled" checked >启用
					</label>
				<#else>
				 	<label> <input type="radio" value="false" disabled="disabled" readonly="readonly" name="isEnabled" checked >禁用
					</label> &nbsp; &nbsp; <label> 
					<input type="radio" value="true"  readonly="readonly" disabled="disabled" name="isEnabled"  >启用
					</label>
				</#if>
					
				</div>
			</div>
			<div class="form-group">
				<label for="client_idInput">client_id</label> <input type="text"
					name="attributes['client_id']" class="form-control"
					id="client_idInput" data-bv-notempty="true" readonly="readonly"
					data-bv-notempty-message="请填写client_id" placeholder="client_id"
					value="${(plugin_config.attributes.client_id)!}">
			</div>
			<div class="form-group">
				<label for="client_secretInput">client_secret</label> <input
					type="text" name="attributes['client_secret']" class="form-control"
					id="client_secretInput" placeholder="client_secret" readonly="readonly"
					data-bv-notempty="true" data-bv-notempty-message="client_secret" value="${(plugin_config.attributes.client_secret)!}">
			</div>
			

		 
		<!-- /.box-body -->

		<div >
			<button type="button" class="btn btn-default"
				onclick="location.href='#/page/plugin/oauth/list'">返回列表</button>
		</div>
	</div>

</section>

<script type="text/javascript">
	require(
			[ "domReady", "fileinputzh" ],
			function(domReady) {
				domReady(function() {
					activeMenu("app");
				});
			});
</script>