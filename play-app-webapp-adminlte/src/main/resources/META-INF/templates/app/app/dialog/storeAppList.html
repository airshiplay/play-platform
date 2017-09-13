
		<div class="box-header">
			<div class="input-group">
				<input type="text" placeholder="请输入应用名称" class="form-control" id="storeAppNameInput">

				<div class="input-group-btn">
					<button class="btn btn-primary" type="button" id="searchStoreAppButton">
						<i class="fa fa-search"></i> 搜索
					</button>
				</div>
			</div>
		</div>
		<div class="box box-primary">
			<div class="box-body no-padding">
				<table class="table table-condensed table-hover">
					<thead>
						<tr>
							<th style="width: 10px">#</th>
							<th style="width: 60px">图标</th>
							<th>名称</th>
							<th>标识</th>
						</tr>
					</thead>
					<tbody id="storeAppStoreTable">
					</tbody>
				</table>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->

<!-- 
https://search.itunes.apple.com/WebObjects/MZSearchHints.woa/wa/hints?cc=cn&q=qq
https://itunes.apple.com/lookup?id=710380093
 -->
		<script type="text/javascript">
			require([ "domReady" ], function(domReady) {
				domReady(function() {
					$("#searchStoreAppButton").on("click", function() {
						$.ajax({
							url : "https://itunes.apple.com/search",
							type : "GET",
							data : {
								entity : "software",
								limit: 20,
								term : $("#storeAppNameInput").val()
							},
							dataType : "jsonp",
							success : function(data, textStatus, jqXHR) {
								var count = data.resultCount;
								var apps = data.results;
								var $body = $("#storeAppStoreTable");
								$body.empty();

								for (var i = 0; i < apps.length; i++) {
									var $tr = $("<tr />");
									$tr.data("trackId", apps[i].trackId);
									$tr.data("name", apps[i].trackName);
									$tr.data("identifier", apps[i].bundleId);
									$tr.data("version", apps[i].version);
									$tr.data("fileSizeBytes", apps[i].fileSizeBytes);
									$tr.data("iconPath", apps[i].artworkUrl100);
									
									$tr.data("screenshotUrls", apps[i].screenshotUrls.join());
									$tr.data("description", apps[i].description);
									
									var $radio = $("<input type='radio' value='"+apps[i].trackId+"' name='trackId'>");
									$tr.append($("<td />").append($radio));
									$tr.append($("<td />").append($("<img />").attr("src", apps[i].artworkUrl60)));
									$tr.append($("<td />").text(apps[i].trackName));
									$tr.append($("<td />").text(apps[i].bundleId));
									$body.append($tr);
								}
							}
						});
					});
				})
			})
		</script>
	