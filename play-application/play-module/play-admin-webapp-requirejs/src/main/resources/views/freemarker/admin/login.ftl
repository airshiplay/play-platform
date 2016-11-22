<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <base id="base" href="${base}">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${base}/requirejs/assets/bootstrap/css/bootstrap.min.css">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="//cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${base}/requirejs/assets/AdminLTE-2.3.6/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="${base}/requirejs/assets/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">
  	.btn-qq {
    	color: #ffffff;
    	background-color: #0073c0;
   	 	border-color: rgba(0, 0, 0, 0.2);
	}
	.btn-weibo {
    	color: #ffffff;
    	background-color: #da1700;
    	border-color: rgba(0, 0, 0, 0.2);
	}
	.btn-wechat {
    	color: #ffffff;
    	background-color: #99D41D;
    	border-color: rgba(0, 0, 0, 0.2);
	}
	.btn-github {
    	color: #ffffff;
    	background-color: #444444;
    	border-color: rgba(0, 0, 0, 0.2);
	}
  </style>
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="#"><b>Admin</b>LTE</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>

    <form action="login" method="post">
      <!--<div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="Email">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>-->
      <div class="form-group has-feedback">
		<input type="text" class="form-control" name="username" placeholder="用户名" data-bv-notempty="true" data-bv-notempty-message="请填写用户名"
						 data-bv-regexp-message="请填写正确的用户名">
		<span class="fa fa-user form-control-feedback"></span>
	 </div>
	 <div class="form-group has-feedback" style="display:none">
		<input type="text" class="form-control" name="mobile" placeholder="手机号码" data-bv-notempty="true" data-bv-notempty-message="请填写手机号码"
						data-bv-regexp="true" data-bv-regexp-regexp="^[1]([3][0-9]{1}|59|58|82|88|89)[0-9]{8}$" data-bv-regexp-message="请填写正确的手机号码">
		<span class="fa fa-phone form-control-feedback"></span>
	 </div>
	 <!-- password -->
     <div class="form-group has-feedback">
					<input type="password" class="form-control" name="password" placeholder="密码" data-bv-notempty="true" data-bv-notempty-message="请填写密码">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
	  </div>
	  <!--验证码                     -->
	  <div class="form-group has-feedback">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="请输入验证码" name="captcha" data-bv-notempty="true" data-bv-notempty-message="请填写验证码"
							aria-describedby="validate-code-addon">
				<span class="input-group-btn" id="validate-code-addon">
				<a href="javascript:;" id="refreshCaptchaButton">
				<img src="/captcha.jpg" style="height: 34px;" />
				</a>
				</span>
		</div>
	  </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"> Remember Me
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

    <div class="social-auth-links text-center">
      <p>- OR -</p>
      <a href="${base}/oauth/authorization/qqOauthPlugin" class="btn btn-block btn-social btn-qq btn-flat"><i class="fa fa-qq"></i>使用QQ登录</a>
      <a href="${base}/oauth/authorization/weiboOauthPlugin" class="btn btn-block btn-social btn-weibo btn-flat"><i class="fa fa-weibo"></i> 使用微博登录</a>
	  <a href="${base}/oauth/authorization/githubOauthPlugin" class="btn btn-block btn-social btn-github btn-flat"><i class="fa fa-github"></i> 使用Github登录</a>
	  <a href="${base}/oauth/authorization/wechatOpenOauthPlugin" class="btn btn-block btn-social btn-wechat btn-flat"><i class="fa fa-wechat"></i> 使用微信登录</a>
      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using
        Facebook</a>
      <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using
        Google+</a>
    </div>
    <!-- /.social-auth-links -->

    <a href="#">I forgot my password</a><br>
    <a href="register.html" class="text-center">Register a new membership</a>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.4 -->
<script src="//cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${base}/requirejs/assets/bootstrap/js/bootstrap.min.js"></script>
<!-- Bootstrap 3.3.6 
	<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>-->
	
	
	<script type="text/javascript" src="${base}/requirejs/assets/vendor/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="${base}/requirejs/assets/vendor/bootstrapvalidator/js/language/zh_CN.js"></script>
	<!-- iCheck -->
	<script src="//cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<!-- iCheck 
<script src="${base}/requirejs/assets/plugins/iCheck/icheck.min.js"></script>-->
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
	<script type="text/javascript">
		var base = "";
		if (typeof requirejs != 'undefined') {
			require([ "domReady" ], function(domReady) {
				domReady(function() {
					location.reload(true);
					return;
				})
			})
		} else {
			$(function() {
				$('input').iCheck({
					checkboxClass : 'icheckbox_square-blue',
					radioClass : 'iradio_square-blue',
					increaseArea : '20%' // optional
				});
				$("form").bootstrapValidator().on('success.form.bv', function(e) {
					e.preventDefault();

					var $form = $(e.target);

					var bv = $form.data('bootstrapValidator');

					$.post($form.attr('action'), $form.serialize(), function(result) {
						if (result.success) {
							$(".login-box-msg").nextAll(".alert").remove();
							location.href = base + "/admin";
						} else {
							$(".login-box-msg").nextAll(".alert").remove();
							$(".login-box-msg").after("\
										<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">\
										  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\
										  <strong>错误提示!</strong> " + result.msg + "\
										</div>");
							$("#refreshCaptchaButton").children("img").attr("src", base + "/captcha.jpg?_t=" + new Date().getTime());
							$form.bootstrapValidator('disableSubmitButtons', false);
						}
					}, 'json');
				});

				$("#refreshCaptchaButton").on("click", function() {
					$(this).children("img").attr("src", base + "/captcha.jpg?_t=" + new Date().getTime());
				});
			});
		}
	</script>
</body>
</html>
