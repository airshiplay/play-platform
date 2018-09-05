define(["view/main/components/header-bar/sider-trigger/sider-trigger","view/main/components/header-bar/custom-bread-crumb/custom-bread-crumb","less!./header-bar.less"],function (siderTrigger,customBreadCrumb) {

  var template="<div class=\"header-bar\">\n" +
      "    <sider-trigger :collapsed=\"collapsed\" icon=\"md-menu\" @on-change=\"handleCollpasedChange\"></sider-trigger>\n" +
      "    <custom-bread-crumb show-icon style=\"margin-left: 30px;\" :list=\"breadCrumbList\"></custom-bread-crumb>\n" +
      "    <div class=\"custom-content-con\">\n" +
      "      <slot></slot>\n" +
      "    </div>\n" +
      "  </div>";

    return {
        name: 'HeaderBar',
        template:template,
        components: {
            siderTrigger,
            customBreadCrumb
        },
        props: {
            collapsed: Boolean
        },
        computed: {
            breadCrumbList () {
                debugger
                return this.$store.state.app.breadCrumbList
            }
        },
        methods: {
            handleCollpasedChange (state) {
                this.$emit('on-coll-change', state)
            }
        }
    }
})
