define(["vuex","less!./user.less"],function (Vuex) {

    return {
        name: 'User',
        template:"  <div class=\"user-avator-dropdown\">\n" +
        "    <Dropdown @on-click=\"handleClick\">\n" +
        "      <Avatar :src=\"userAvator\"/>\n" +
        "      <Icon :size=\"18\" type=\"md-arrow-dropdown\"></Icon>\n" +
        "      <DropdownMenu slot=\"list\">\n" +
        "        <DropdownItem name=\"logout\">退出登录</DropdownItem>\n" +
        "      </DropdownMenu>\n" +
        "    </Dropdown>\n" +
        "  </div>",
        props: {
            userAvator: {
                type: String,
                default: ''
            }
        },
        methods: {
            ...Vuex.mapActions([
                'handleLogOut'
            ]),
            handleClick (name) {
                switch (name) {
                    case 'logout':
                        this.handleLogOut().then(() => {
                            this.$router.push({
                                name: 'login'
                            })
                        })
                        break
                }
            }
        }
    }
})