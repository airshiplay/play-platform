define(["view/main/components/side-menu/mixin","view/main/components/side-menu/item-mixin"],function (mixin, itemMixin ) {
    return {
        name: 'SideMenuItem',
        mixins: [ mixin, itemMixin ],
        template:"  <Submenu :name=\"`${parentName}`\">\n" +
        "    <template slot=\"title\">\n" +
        "      <common-icon :type=\"parentItem.icon || ''\"/>\n" +
        "      <span>{{ showTitle(parentItem) }}</span>\n" +
        "    </template>\n" +
        "    <template v-for=\"item in children\">\n" +
        "      <template v-if=\"item.children && item.children.length === 1\">\n" +
        "        <side-menu-item v-if=\"showChildren(item)\" :key=\"`menu-${item.name}`\" :parent-item=\"item\"></side-menu-item>\n" +
        "        <menu-item v-else :name=\"getNameOrHref(item, true)\" :key=\"`menu-${item.children[0].name}`\"><common-icon :type=\"item.children[0].icon || ''\"/><span>{{ showTitle(item.children[0]) }}</span></menu-item>\n" +
        "      </template>\n" +
        "      <template v-else>\n" +
        "        <side-menu-item v-if=\"showChildren(item)\" :key=\"`menu-${item.name}`\" :parent-item=\"item\"></side-menu-item>\n" +
        "        <menu-item v-else :name=\"getNameOrHref(item)\" :key=\"`menu-${item.name}`\"><common-icon :type=\"item.icon || ''\"/><span>{{ showTitle(item) }}</span></menu-item>\n" +
        "      </template>\n" +
        "    </template>\n" +
        "  </Submenu>"
    }
})
