define(["view/main/components/side-menu/mixin","view/main/components/side-menu/side-menu-item","view/main/components/side-menu/collapsed-menu","libs/tools","less!./side-menu.less"],function (mixin,SideMenuItem,CollapsedMenu,tools) {

    var template="<div class=\"side-menu-wrapper\">\n" +
        "    <slot></slot>\n" +
        "    <Menu ref=\"menu\" v-show=\"!collapsed\" :active-name=\"activeName\" :open-names=\"openedNames\" :accordion=\"accordion\" :theme=\"theme\" width=\"auto\" @on-select=\"handleSelect\">\n" +
        "      <template v-for=\"item in menuList\">\n" +
        "        <template v-if=\"item.children && item.children.length === 1\">\n" +
        "          <side-menu-item v-if=\"showChildren(item)\" :key=\"`menu-${item.name}`\" :parent-item=\"item\"></side-menu-item>\n" +
        "          <menu-item v-else :name=\"getNameOrHref(item, true)\" :key=\"`menu-${item.children[0].name}`\"><common-icon :type=\"item.children[0].icon || ''\"/><span>{{ showTitle(item.children[0]) }}</span></menu-item>\n" +
        "        </template>\n" +
        "        <template v-else>\n" +
        "          <side-menu-item v-if=\"showChildren(item)\" :key=\"`menu-${item.name}`\" :parent-item=\"item\"></side-menu-item>\n" +
        "          <menu-item v-else :name=\"getNameOrHref(item)\" :key=\"`menu-${item.name}`\"><common-icon :type=\"item.icon || ''\"/><span>{{ showTitle(item) }}</span></menu-item>\n" +
        "        </template>\n" +
        "      </template>\n" +
        "    </Menu>\n" +
        "    <div class=\"menu-collapsed\" v-show=\"collapsed\" :list=\"menuList\">\n" +
        "      <template v-for=\"item in menuList\">\n" +
        "        <collapsed-menu v-if=\"item.children && item.children.length > 1\" @on-click=\"handleSelect\" hide-title :root-icon-size=\"rootIconSize\" :icon-size=\"iconSize\" :theme=\"theme\" :parent-item=\"item\" :key=\"`drop-menu-${item.name}`\"></collapsed-menu>\n" +
        "        <Tooltip transfer v-else :content=\"(item.meta && item.meta.title) || (item.children && item.children[0] && item.children[0].meta.title)\" placement=\"right\" :key=\"`drop-menu-${item.name}`\">\n" +
        "          <a @click=\"handleSelect(getNameOrHref(item, true))\" class=\"drop-menu-a\" :style=\"{textAlign: 'center'}\"><common-icon :size=\"rootIconSize\" :color=\"textColor\" :type=\"item.icon || (item.children && item.children[0].icon)\"/></a>\n" +
        "        </Tooltip>\n" +
        "      </template>\n" +
        "    </div>\n" +
        "  </div>";
return {

    name: 'SideMenu',
    mixins: [ mixin ],
    template:template,
    components: {
        SideMenuItem,
        CollapsedMenu
    },
    props: {
        menuList: {
            type: Array,
            default () {
                return []
            }
        },
        collapsed: {
            type: Boolean
        },
        theme: {
            type: String,
            default: 'dark'
        },
        rootIconSize: {
            type: Number,
            default: 20
        },
        iconSize: {
            type: Number,
            default: 16
        },
        accordion: Boolean,
        activeName: {
            type: String,
            default: ''
        },
        openNames: {
            type: Array,
            default: () => []
        }
    },
    data () {
        return {
            openedNames: []
        }
    },
    methods: {
        handleSelect (name) {
            this.$emit('on-select', name)
        },
        getOpenedNamesByActiveName (name) {
            return this.$route.matched.map(item => item.name).filter(item => item !== name)
        },
        updateOpenName (name) {
            if (name === 'home') this.openedNames = []
            else this.openedNames = this.getOpenedNamesByActiveName(name)
        }
    },
    computed: {
        textColor () {
            return this.theme === 'dark' ? '#fff' : '#495060'
        }
    },
    watch: {
        activeName (name) {
            debugger
            if (this.accordion) this.openedNames = this.getOpenedNamesByActiveName(name)
                else  this.openedNames = tools.getUnion(this.openedNames, this.getOpenedNamesByActiveName(name))
        },
        openNames (newNames) {
            this.openedNames = newNames
        },
        openedNames () {
            this.$nextTick(() => {
                this.$refs.menu.updateOpened()
            })
        }
    },
    mounted () {
        debugger
        this.openedNames = tools.getUnion(this.openedNames, this.getOpenedNamesByActiveName(name))
    }
}
})
