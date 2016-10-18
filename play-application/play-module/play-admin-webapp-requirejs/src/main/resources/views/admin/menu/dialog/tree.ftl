<link type="text/css" rel="stylesheet"
	href="//cdn.bootcss.com/jstree/3.3.2/themes/default/style.min.css">
<div class="col-md-4">

	<div id="org_tree"></div>

</div>



<script type="text/javascript">
	require([ "domReady", "jstree" ], function(domReady) {
		domReady(function() {
			$('#org_tree').jstree({
				'core' : {
					'data' : {
						"url" : base + "/center/menu/tree",
						"dataType" : "json" // needed only if you do not supply JSON headers
					}
				},
				"plugins" : [ "checkbox" ]
			});
			
			$('#org_tree').on("changed.jstree", function (e, data) {
				  console.log(data.selected);
				});
		})
	});
</script>