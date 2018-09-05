define(["vuex","components/login-form"],function (vuex,LoginForm) {
    // var LoginForm = ResolveComponent("components/login-form");
    return {
        components: {
            LoginForm
        },
        methods:{
            ...vuex.mapActions([
                'handleLogin',
                'getUserInfo'
            ]),
            handleSubmit ({ userName, password }) {
                debugger
                this.handleLogin({ userName, password }).then(res => {
                    this.getUserInfo().then(res => {
                        this.$router.push({
                            name: 'home'
                        })
                    })
                })
            }
        },
        template:"" +
        "  <div class=\"login\">\n" +
        "    <div class=\"login-con\">\n" +
        "      <Card icon=\"log-in\" title=\"欢迎登录\" :bordered=\"false\">\n" +
        "        <div class=\"form-con\">\n" +
        "          <LoginForm @on-success-valid=\"handleSubmit\"></LoginForm>\n" +
        "          <p class=\"login-tip\">输入任意用户名和密码即可</p>\n" +
        "        </div>\n" +
        "      </Card>\n" +
        "    </div>\n" +
        "  </div>",
        data:function(){
            return {

            }
        }
    }
});