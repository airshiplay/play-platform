# play-module


##模块命名规则

- 模块接口
  	play-{module}-api
- 模块业务
	play-{module}-business
- 模块管理平台
	play-{module}-webapp-requirejs
	
####实例
- 管理平台主业务模块
play-main-business
- 管理平台主管理页面
play-admin-webapp-requirejs

###模板包名
 - com.airshiplay.play.{module}
 - com.airshiplay.play.{module}.controller
 - com.airshiplay.play.{module}.{module}ApplicationInitializer
 
###模块URL规则

####模块页面URL规则
-  /{modulename}/{businessname}/list
-  /{modulename}/{businessname}/get
-  /{modulename}/{businessname}/add
-  /{modulename}/{businessname}/edit/{id}
-  /{modulename}/{businessname}/view/{id}

####FreeMarker页面定义规则
-  /views/{modulename}/{businessname}/add.ftl

####模块URL规则
-  /{modulename}/{businessname}/page
-  /{modulename}/{businessname}/tree


##管理平台角色权限定义
平台采用Shiro进行管理    

 资源标识符:操作:对象实例ID

{modulename}:{businessname}:对象实例ID

###页面权限

	page:*:*:* 表示拥有所有资源所有权限
	
	//对象实例ID命名规则
	*:*:read 页面查看权限
	*:*:read 数据查看权限
	*:*:create 创建权限
	*:*:update 更新权限
	*:*:delete 删除权限
	
	//系统菜单权限实例
	system:menu:pageview   表示菜单查看
	system:menu:create 表示菜单创建
	system:menu:update 表示菜单更新
	system:menu:delete 表示菜单删除
	system:menu:*	   表示菜单所有权限（增删改查）

###数据权限
	data:*:*:* 表示拥有所有资源所有权限

	
	


<link type="text/css" rel="stylesheet" href="bootstrap/vendor/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
<link type="text/css" rel="stylesheet" href="bootstrap/vendor/plugins/select2/select2.min.css">


