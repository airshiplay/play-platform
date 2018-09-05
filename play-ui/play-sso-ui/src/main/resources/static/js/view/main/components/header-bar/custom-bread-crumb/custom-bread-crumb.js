define(["components/common/util","components/common-icon/common-icon","less!./custom-bread-crumb.less"],function (util,CommonIcon) {

  var  template="<div class=\"custom-bread-crumb\">\n" +
      "    <Breadcrumb :style=\"{fontSize: `${fontSize}px`}\">\n" +
      "      <Breadcrumb-Item v-for=\"item in list\" :to=\"item.to\" :key=\"`bread-crumb-${item.name}`\">\n" +
      "        <common-icon style=\"margin-right: 4px;\" :type=\"item.icon || ''\"/>\n" +
      "        {{ showTitle(item) }}\n" +
      "      </Breadcrumb-Item>\n" +
      "    </Breadcrumb>\n" +
      "  </div>";
    return {
        name: 'customBreadCrumb',
        template:template,
        components: {
            CommonIcon
        },
        props: {
            list: {
                type: Array,
                default: () => []
            },
            fontSize: {
                type: Number,
                default: 14
            },
            showIcon: {
                type: Boolean,
                default: false
            }
        },
        methods: {
            showTitle (item) {
                return util.showTitle(item, this)
            },
            isCustomIcon (iconName) {
                return iconName.indexOf('_') === 0
            },
            getCustomIconName (iconName) {
                debugger
                return iconName.slice(1)
            }
        }
    }
})
