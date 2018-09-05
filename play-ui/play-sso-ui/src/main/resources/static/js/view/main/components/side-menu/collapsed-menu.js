define(["view/main/components/side-menu/mixin","view/main/components/side-menu/item-mixin"],function (mixin,itemMixin) {

var template="<Dropdown @on-click=\"handleClick\" transfer :placement=\"placement\">\n" +
    "    <a class=\"drop-menu-a\" type=\"text\" @mouseover=\"handleMousemove($event, children)\" :style=\"{textAlign: !hideTitle ? 'left' : ''}\"><common-icon :size=\"rootIconSize\" :color=\"textColor\" :type=\"parentItem.icon\"/><span class=\"menu-title\" v-if=\"!hideTitle\">{{ showTitle(parentItem) }}</span><Icon style=\"float: right;\" v-if=\"!hideTitle\" type=\"ios-arrow-forward\" :size=\"16\"/></a>\n" +
    "    <Dropdown-Menu slot=\"list\">\n" +
    "      <template v-for=\"child in children\">\n" +
    "        <collapsed-menu v-if=\"showChildren(child)\" :icon-size=\"iconSize\" :parent-item=\"child\" :key=\"`drop-${child.name}`\"></collapsed-menu>\n" +
    "        <Dropdown-Item v-else :key=\"`drop-${child.name}`\" :name=\"child.name\"><common-icon :size=\"iconSize\" :type=\"child.icon\"/><span class=\"menu-title\">{{ showTitle(child) }}</span></Dropdown-Item>\n" +
    "      </template>\n" +
    "    </Dropdown-Menu>\n" +
    "  </Dropdown>";
    return {
        name: 'CollapsedMenu',
        mixins: [ mixin, itemMixin ],
        template:template,
        props: {
            hideTitle: {
                type: Boolean,
                default: false
            },
            rootIconSize: {
                type: Number,
                default: 16
            }
        },
        data:function ()  {
            return {
                placement: 'right-end'
            }
        },
        methods: {
            handleClick (name) {
                this.$emit('on-click', name)
            },
            handleMousemove (event, children) {
                const { pageY } = event
                const height = children.length * 38
                const isOverflow = pageY + height < window.innerHeight
                this.placement = isOverflow ? 'right-start' : 'right-end'
            }
        }
    }
})
