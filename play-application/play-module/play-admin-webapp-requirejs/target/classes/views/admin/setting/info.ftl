<section class="content-header">
	<h1>
		参数配置 <small>开始管理参数配置</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:;">
				<i class="fa fa-home"></i> 首页
			</a></li>
		<li class="active"><a href="#/page/profile">
				<i class="fa fa-cogs"></i>参数配置
			</a></li>
	</ol>
</section>
<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">参数配置</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" class="profile-form" action="/center/setting/save">
					<input type="hidden" name="id" value="" />

					<div class="nav-tabs-custom" style="box-shadow: none;">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#tab_1">网站信息</a></li>
							<!--<li><a data-toggle="tab" href="#tab_2">密码策略</a></li>
							<li><a data-toggle="tab" href="#tab_3">限制策略</a></li>
							<li><a data-toggle="tab" href="#tab_4">WiFi配置</a></li>
							<li><a data-toggle="tab" href="#tab_5">VPN配置</a></li>
							<li><a data-toggle="tab" href="#tab_6">Email配置</a></li>
							<li><a data-toggle="tab" href="#tab_7">LDAP配置</a></li> -->
							<li class="pull-right"><a class="text-muted" href="javascript:;">
									<i class="fa fa-gear"></i>
								</a></li>
						</ul>
						<div class="tab-content">
							<div id="tab_1" class="tab-pane active">
								<div class="box-body">
									<div class="form-group">
										<label for="siteNameInput">网站名称</label>
										<input type="text" name="siteName" class="form-control" id="siteNameInput" placeholder="请填写网站名称" data-bv-notempty="true"
											data-bv-notempty-message="请填写网站名称" value="${setting.siteName!}">
									</div>
									<div class="form-group">
										<label for="identifierInput">配置标识</label>
										<input type="text" name="identifier" class="form-control" id="identifierInput" placeholder="请填写配置标识" data-bv-notempty="true"
											data-bv-notempty-message="请填写配置标识" value="">
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_removalDisallowed">
										<label> <input type="checkbox" name="removalDisallowed"> <strong>禁止移除</strong>
										</label>
									</div>
									<div class="form-group supervised">
										<label for="removalPasscodeInput">移除密码</label>
										<input type="text" class="form-control" name="removalPasscode" id="removalPasscodeInput" value="">
									</div>
									<div class="checkbox">
										<input type="hidden" name="_isEncrypted">
										<label> <input type="checkbox" name="isEncrypted" checked="checked"> <strong>加密</strong>
										</label>
									</div>
									<div class="form-group">
										<label for="removalDateInput">移除时间</label>

										<input type="date" class="form-control" name="removalDate" id="removalDateInput" value="">
									</div>
									<div class="form-group">
										<label for="durationUtilRemovalInput">隔多少秒后移除</label>
										<input type="text" class="form-control" name="durationUntilRemoval" id="durationUtilRemovalInput" value="">
									</div>
									<div class="form-group">
										<label for="descriptionInput">配置描述</label>
										<textarea name="description" class="form-control" id="descriptionInput"></textarea>
									</div>
								</div>
							</div>
							<div id="tab_2" class="tab-pane">
								<div class="box-body">
									<div class="form-group">
										<div class="checkbox">
											<input type="hidden" name="_passcode.enabled">
											<label> <input type="checkbox" name="passcode.enabled"> <strong>启用密码策略</strong>
											</label>
										</div>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_passcode.content.allowSimple">
										<label> <input type="checkbox" name="passcode.content.allowSimple" checked="checked"> <strong>允许简单密码</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_passcode.content.allowFingerprintModification">
										<label> <input type="checkbox" name="passcode.content.forcePIN"> <strong>强制使用PIN</strong>
										</label>
									</div>
									<div class="form-group">
										<label for="maxFailedAttemptsInput">最多可允许的失败次数</label>
										<input type="text" class="form-control" name="passcode.content.maxFailedAttempts" value=""
											id="maxFailedAttemptsInput">
									</div>
									<div class="form-group">
										<label for="maxInactivityInput">自动锁定前最长时间</label>
										<input type="text" class="form-control" name="passcode.content.maxInactivity" value=""
											id="maxInactivityInput">
									</div>
									<div class="form-group">
										<label for="maxPINAgeInDaysInput">密码的最长有效期</label>
										<input type="text" class="form-control" name="passcode.content.maxPINAgeInDays" value=""
											id="maxPINAgeInDaysInput">
									</div>
									<div class="form-group">
										<label for="minLengthInput">最小密码长度</label>
										<input type="text" class="form-control" name="passcode.content.minLength" value="" id="minLengthInput">
									</div>
									<div class="checkbox">
										<input type="hidden" name="_passcode.content.requireAlphanumeric">
										<label> <input type="checkbox" name="passcode.content.requireAlphanumeric">
											<strong>要求字母和数字值</strong>
										</label>
									</div>
									<div class="form-group">
										<label for="pinHistoryInput">密码历史记录</label>
										<input type="text" class="form-control" name="passcode.content.pinHistory" value="" id="pinHistoryInput"
											placeholder="请输入个数">
									</div>
									<div class="form-group">
										<label for="maxGracePeriodInput">设备锁定前的最长宽限时间</label>
										<input type="text" class="form-control" name="passcode.content.maxGracePeriod" value=""
											id="maxGracePeriodInput" placeholder="请输入秒数">
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_passcode.content.allowFingerprintModification">
										<label> <input type="checkbox" name="passcode.content.allowFingerprintModification"> <strong>允许修改TouchID</strong>
										</label>
									</div>
								</div>
							</div>
							<div id="tab_3" class="tab-pane">
								<div class="box-body">
									<div class="form-group">
										<div class="checkbox">
											<input type="hidden" name="_restrictions.enabled">
											<label> <input type="checkbox" name="restrictions.enabled"> <strong>启用限制策略</strong>
											</label>
										</div>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowAccountModification">
										<label> <input type="checkbox" name="restrictions.content.allowAccountModification" checked="checked"> <strong>允许修改账号</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowAddingGameCenterFriends">
										<label> <input type="checkbox" name="restrictions.content.allowAddingGameCenterFriends" checked="checked"> <strong>允许添加Game Center朋友</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowAirDrop">
										<label> <input type="checkbox" name="restrictions.content.allowAirDrop" checked="checked"> <strong>允许使用AirDrop</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowAppCellularDataModification">
										<label> <input type="checkbox" name="restrictions.content.allowAppCellularDataModification" checked="checked"> <strong>允许应用修改蜂窝数据</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowAppInstallation">
										<label> <input type="checkbox" name="restrictions.content.allowAppInstallation" checked="checked"> <strong>允许安装应用</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowAppRemoval">
										<label> <input type="checkbox" name="restrictions.content.allowAppRemoval" checked="checked">
											<strong>允许移除应用</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowAssistant">
										<label> <input type="checkbox" name="restrictions.content.allowAssistant" checked="checked">
											<strong>允许使用siri</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowAssistantUserGeneratedContent">
										<label> <input type="checkbox" name="restrictions.content.allowAssistantUserGeneratedContent" checked="checked"> <strong>阻止siri查询用户创建的内容</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowAssistantWhileLocked">
										<label> <input type="checkbox" name="restrictions.content.allowAssistantWhileLocked" checked="checked"> <strong>当锁定时允许使用siri</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowBookstore">
										<label> <input type="checkbox" name="restrictions.content.allowBookstore" checked="checked">
											<strong>允许使用BookStore</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowBookstoreErotica">
										<label> <input type="checkbox" name="restrictions.content.allowBookstoreErotica" checked="checked"> <strong>允许iBookstore色情书籍</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowCamera">
										<label> <input type="checkbox" name="restrictions.content.allowCamera" checked="checked"> <strong>允许使用摄像头</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowChat">
										<label> <input type="checkbox" name="restrictions.content.allowChat" checked="checked"> <strong>允许聊天</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowCloudBackup">
										<label> <input type="checkbox" name="restrictions.content.allowCloudBackup" checked="checked">
											<strong>允许使用云备份</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowCloudDocumentSync">
										<label> <input type="checkbox" name="restrictions.content.allowCloudDocumentSync" checked="checked"> <strong>允许文档同步</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowCloudKeychainSync">
										<label> <input type="checkbox" name="restrictions.content.allowCloudKeychainSync" checked="checked"> <strong>允许钥匙串同步</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowDiagnosticSubmission">
										<label> <input type="checkbox" name="restrictions.content.allowDiagnosticSubmission" checked="checked"> <strong>允许提交诊断数据</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowExplicitContent">
										<label> <input type="checkbox" name="restrictions.content.allowExplicitContent" checked="checked"> <strong>允许不良的音乐、Podcast与iTunes U</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowFindMyFriendsModification">
										<label> <input type="checkbox" name="restrictions.content.allowFindMyFriendsModification" checked="checked"> <strong>允许修改查找我的朋友</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowFingerprintForUnlock">
										<label> <input type="checkbox" name="restrictions.content.allowFingerprintForUnlock" checked="checked"> <strong>允许使用TouchID解锁</strong>
										</label>
									</div>
									<div class="checkbo supervisedx">
										<input type="hidden" name="_restrictions.content.allowGameCenter">
										<label> <input type="checkbox" name="restrictions.content.allowGameCenter" checked="checked">
											<strong>允许使用GameCenter</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowGlobalBackgroundFetchWhenRoaming">
										<label> <input type="checkbox" name="restrictions.content.allowGlobalBackgroundFetchWhenRoaming" checked="checked"> <strong>当漫游的时候允许定位</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowInAppPurchases">
										<label> <input type="checkbox" name="restrictions.content.allowInAppPurchases" checked="checked"> <strong>允许应用内购买</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowLockScreenControlCenter">
										<label> <input type="checkbox" name="restrictions.content.allowLockScreenControlCenter" checked="checked"> <strong>允许控制中心锁屏</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowHostPairing">
										<label> <input type="checkbox" name="restrictions.content.allowHostPairing" checked="checked">
											<strong>允许主机配对</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowLockScreenNotificationsView">
										<label> <input type="checkbox" name="restrictions.content.allowLockScreenNotificationsView" checked="checked"> <strong>允许使用锁定屏幕的通知视图</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowLockScreenTodayView">
										<label> <input type="checkbox" name="restrictions.content.allowLockScreenTodayView" checked="checked"> <strong>允许使用锁定屏幕的今天视图</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowMultiplayerGaming">
										<label> <input type="checkbox" name="restrictions.content.allowMultiplayerGaming" checked="checked"> <strong>允许多人游戏</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowOpenFromManagedToUnmanaged">
										<label> <input type="checkbox" name="restrictions.content.allowOpenFromManagedToUnmanaged" checked="checked"> <strong>允许管理的应用中打开未管理的文档</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowOpenFromUnmanagedToManaged">
										<label> <input type="checkbox" name="restrictions.content.allowOpenFromUnmanagedToManaged" checked="checked"> <strong>允许未管理的应用打开管理的文档</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowOTAPKIUpdates">
										<label> <input type="checkbox" name="restrictions.content.allowOTAPKIUpdates" checked="checked"> <strong>允许使用空中KPI升级</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowPassbookWhileLocked">
										<label> <input type="checkbox" name="restrictions.content.allowPassbookWhileLocked" checked="checked"> <strong>锁定时运行使用Passbook</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowPhotoStream">
										<label> <input type="checkbox" name="restrictions.content.allowPhotoStream" checked="checked">
											<strong>允许照片流</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowSafari">
										<label> <input type="checkbox" name="restrictions.content.allowSafari" checked="checked"> <strong>允许使用safari</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.safariAllowAutoFill">
										<label> <input type="checkbox" name="restrictions.content.safariAllowAutoFill" checked="checked"> <strong>允许自动填充</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.safariForceFraudWarning">
										<label> <input type="checkbox" name="restrictions.content.safariForceFraudWarning"> <strong>强制发出欺诈警告</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.safariAllowJavaScript">
										<label> <input type="checkbox" name="restrictions.content.safariAllowJavaScript" checked="checked"> <strong>启用JavaScript</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.safariAllowPopups">
										<label> <input type="checkbox" name="restrictions.content.safariAllowPopups" checked="checked">
											<strong>允许弹出式窗口</strong>
										</label>
									</div>
									<div class="form-group">
										<label for="safariAcceptCookiesInput">接受Cookie</label>
										<input type="text" class="form-control" name="restrictions.content.safariAcceptCookies"
											value="" id="safariAcceptCookiesInput" placeholder="请输入个数">
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowSharedStream">
										<label> <input type="checkbox" name="restrictions.content.allowSharedStream" checked="checked">
											<strong>允许共享的照片流</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowUIConfigurationProfileInstallation">
										<label> <input type="checkbox" name="restrictions.content.allowUIConfigurationProfileInstallation" checked="checked"> <strong>允许使用UI配置文件安装</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowUntrustedTLSPrompt">
										<label> <input type="checkbox" name="restrictions.content.allowUntrustedTLSPrompt" checked="checked"> <strong>允许不信任的TLS提示</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowVideoConferencing">
										<label> <input type="checkbox" name="restrictions.content.allowVideoConferencing" checked="checked"> <strong>允许视频会议</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowVoiceDialing">
										<label> <input type="checkbox" name="restrictions.content.allowVoiceDialing" checked="checked">
											<strong>允许语音会议</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowYouTube">
										<label> <input type="checkbox" name="restrictions.content.allowYouTube" checked="checked"> <strong>允许使用YouTube</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowiTunes">
										<label> <input type="checkbox" name="restrictions.content.allowiTunes" checked="checked"> <strong>允许使用iTunes</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.forceAssistantProfanityFilter">
										<label> <input type="checkbox" name="restrictions.content.forceAssistantProfanityFilter"> <strong>强制siri过滤脏话</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.forceEncryptedBackup">
										<label> <input type="checkbox" name="restrictions.content.forceEncryptedBackup"> <strong>强制加密备份</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.forceITunesStorePasswordEntry">
										<label> <input type="checkbox" name="restrictions.content.forceITunesStorePasswordEntry"> <strong>强制使用密码登录iTunesStore</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.forceLimitAdTracking">
										<label> <input type="checkbox" name="restrictions.content.forceLimitAdTracking"> <strong>强制限制广告跟踪</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.forceAirPlayOutgoingRequestsPairingPassword">
										<label> <input type="checkbox" name="restrictions.content.forceAirPlayOutgoingRequestsPairingPassword"> <strong>强制AirPlay请求退出配对密码</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.forceAirPlayIncomingRequestsPairingPassword">
										<label> <input type="checkbox" name="restrictions.content.forceAirPlayIncomingRequestsPairingPassword"> <strong>强制AirPlay请求加入配对密码</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowManagedAppsCloudSync">
										<label> <input type="checkbox" name="restrictions.content.allowManagedAppsCloudSync" checked="checked"> <strong>允许管理应用云同步</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowEraseContentAndSettings">
										<label> <input type="checkbox" name="restrictions.content.allowEraseContentAndSettings" checked="checked"> <strong>允许擦除内容和设置</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowSpotlightInternetResults">
										<label> <input type="checkbox" name="restrictions.content.allowSpotlightInternetResults" checked="checked"> <strong>允许使用网络搜索结果 </strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowEnablingRestrictions">
										<label> <input type="checkbox" name="restrictions.content.allowEnablingRestrictions" checked="checked"> <strong>允许启用限制</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowActivityContinuation">
										<label> <input type="checkbox" name="restrictions.content.allowActivityContinuation" checked="checked"> <strong>允许活动继续</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowEnterpriseBookBackup">
										<label> <input type="checkbox" name="restrictions.content.allowEnterpriseBookBackup" checked="checked"> <strong>允许企业书籍备份</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowEnterpriseBookMetadataSync">
										<label> <input type="checkbox" name="restrictions.content.allowEnterpriseBookMetadataSync" checked="checked"> <strong>允许企业书籍同步</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowPodcasts">
										<label> <input type="checkbox" name="restrictions.content.allowPodcasts" checked="checked">
											<strong>允许使用广播</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowDefinitionLookup">
										<label> <input type="checkbox" name="restrictions.content.allowDefinitionLookup" checked="checked"> <strong>允许定义查看内容</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowPredictiveKeyboard">
										<label> <input type="checkbox" name="restrictions.content.allowPredictiveKeyboard" checked="checked"> <strong>允许预置键盘 </strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowAutoCorrection">
										<label> <input type="checkbox" name="restrictions.content.allowAutoCorrection" checked="checked"> <strong>允许预置键盘</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowSpellCheck">
										<label> <input type="checkbox" name="restrictions.content.allowSpellCheck" checked="checked">
											<strong>允许拼写检查</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.forceWatchWristDetection">
										<label> <input type="checkbox" name="restrictions.content.forceWatchWristDetection"> <strong>强制检测手表腕</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowMusicService">
										<label> <input type="checkbox" name="restrictions.content.allowMusicService" checked="checked">
											<strong>允许使用音乐服务</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowCloudPhotoLibrary">
										<label> <input type="checkbox" name="restrictions.content.allowCloudPhotoLibrary" checked="checked"> <strong>允许使用云图片库</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowNews">
										<label> <input type="checkbox" name="restrictions.content.allowNews" checked="checked"> <strong>允许使用新闻</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.forceAirDropUnmanaged">
										<label> <input type="checkbox" name="restrictions.content.forceAirDropUnmanaged"> <strong>禁止AirDrop管理</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowUIAppInstallation">
										<label> <input type="checkbox" name="restrictions.content.allowUIAppInstallation" checked="checked"> <strong>允许使用AppStore来安装应用</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_restrictions.content.allowScreenShot">
										<label> <input type="checkbox" name="restrictions.content.allowScreenShot" checked="checked">
											<strong>允许屏幕快照</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowKeyboardShortcuts">
										<label> <input type="checkbox" name="restrictions.content.allowKeyboardShortcuts" checked="checked"> <strong>允许快捷键</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowPairedWatch">
										<label> <input type="checkbox" name="restrictions.content.allowPairedWatch" checked="checked">
											<strong>允许配对手表</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowPasscodeModification">
										<label> <input type="checkbox" name="restrictions.content.allowPasscodeModification" checked="checked"> <strong>允许密码修改</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowDeviceNameModification">
										<label> <input type="checkbox" name="restrictions.content.allowDeviceNameModification" checked="checked"> <strong>允许设备名称修改</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowWallpaperModification">
										<label> <input type="checkbox" name="restrictions.content.allowWallpaperModification" checked="checked"> <strong>允许墙纸修改</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowAutomaticAppDownloads">
										<label> <input type="checkbox" name="restrictions.content.allowAutomaticAppDownloads" checked="checked"> <strong>允许App自动下载</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowEnterpriseAppTrust">
										<label> <input type="checkbox" name="restrictions.content.allowEnterpriseAppTrust" checked="checked"> <strong>允许信任企业App</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowEnterpriseAppTrustModification">
										<label> <input type="checkbox" name="restrictions.content.allowEnterpriseAppTrustModification" checked="checked"> <strong>允许修改是否信任企业App</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowRadioService">
										<label> <input type="checkbox" name="restrictions.content.allowRadioService" checked="checked">
											<strong>允许调频服务</strong>
										</label>
									</div>
									<div class="checkbox supervised">
										<input type="hidden" name="_restrictions.content.allowNotificationsModification">
										<label> <input type="checkbox" name="restrictions.content.allowNotificationsModification" checked="checked"> <strong>允许通知服务</strong>
										</label>
									</div>
								</div>
							</div>
							<div id="tab_4" class="tab-pane">
								<div class="box-body">
									<div class="form-group">
										<div class="checkbox">
											<input type="hidden" name="_wifi.enabled">
											<label> <input type="checkbox" name="wifi.enabled"> <strong>启用WiFi配置</strong>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label for="ssidInput">连接名称</label>
										<input type="text" class="form-control" name="wifi.content.ssidStr" value="" id="ssidInput" placeholder="请输入连接名称">
									</div>
									<div class="checkbox">
										<input type="hidden" name="_wifi.content.hiddenNetwork">
										<label> <input type="checkbox" name="wifi.content.hiddenNetwork"> <strong>隐藏的连接</strong>
										</label>
									</div>
									<div class="checkbox">
										<input type="hidden" name="_wifi.content.autoJoin">
										<label> <input type="checkbox" name="wifi.content.autoJoin"> <strong>自动加入</strong>
										</label>
									</div>
									<div class="form-group">
										<label>加密类型</label> <select class="form-control select2" name="wifi.content.encryptionType" style="width: 100%;">
											<option value="None">None</option>
											<option value="WEP">WEP</option>
											<option value="WPA">WPA</option>
											<option value="WPA2">WPA2</option>
											<option value="Any">Any</option>
										</select>
									</div>
									<div class="form-group">
										<label for="wifiPasswordInput">密码</label>
										<input type="text" class="form-control" name="wifi.content.password" value="" id="wifiPasswordInput"
											placeholder="请输入密码">
									</div>
								</div>
							</div>
							<!-- <div id="tab_5" class="tab-pane">
								<div class="box-body">
									<div class="form-group">
										<div class="checkbox">
											<label> <input type="checkbox"> <strong>启用VPN配置</strong>
											</label>
										</div>
									</div>
								</div>
							</div>
							<div id="tab_6" class="tab-pane">
								<div class="box-body">
									<div class="form-group">
										<div class="checkbox">
											<label> <input type="checkbox"> <strong>启用Email配置</strong>
											</label>
										</div>
									</div>
								</div>
							</div>
							<div id="tab_7" class="tab-pane">
								<div class="box-body">
									<div class="form-group">
										<div class="checkbox">
											<label> <input type="checkbox"> <strong>启用LDAP配置</strong>
											</label>
										</div>
									</div>
								</div>
							</div> -->
						</div>
						<!-- /.tab-content -->
					</div>

					<div class="box-footer">
						<button type="button" class="btn btn-default" onclick="location.href='#/page/profile'">返回列表</button>
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require([ "domReady", "fileinputzh", "select2zh" ], function(domReady) {
		domReady(function() {
			activeMenu("profile");

			$('.select2').select2();

			var $form = $(".profile-form");

			$form.bootstrapValidator({
				excluded : [ ':disabled' ]
			}).on('success.form.bv', function(e) {
				e.preventDefault();

				var $form = $(e.target);

				var bv = $form.data('bootstrapValidator');

				$.post($form.attr('action'), $form.serialize(), function(result) {
					if (result.success) {
						$.alert({
							title : false,
							content : "保存成功",
							confirmButton : "返回列表",
							confirm : function() {
								location.href = "#/page/profile";
							}
						});

					} else {
						alert(result.msg);
						$form.bootstrapValidator('disableSubmitButtons', false);
					}
				}, 'json');
			});
		});
	});
</script>