define([],function () {

    var template="<Form ref=\"loginForm\" :model=\"form\" :rules=\"rules\" @keydown.enter.native=\"handleSubmit\">\n" +
        "    <FormItem prop=\"userName\">\n" +
        "      <Input v-model=\"form.userName\" placeholder=\"请输入用户名\">\n" +
        "        <span slot=\"prepend\">\n" +
        "          <Icon :size=\"16\" type=\"ios-person\"></Icon>\n" +
        "        </span>\n" +
        "      </Input>\n" +
        "    </FormItem>\n" +
        "    <FormItem prop=\"password\">\n" +
        "      <Input type=\"password\" v-model=\"form.password\" placeholder=\"请输入密码\">\n" +
        "        <span slot=\"prepend\">\n" +
        "          <Icon :size=\"14\" type=\"md-lock\"></Icon>\n" +
        "        </span>\n" +
        "      </Input>\n" +
        "    </FormItem>\n" +
        "    <FormItem>\n" +
        "      <Button @click=\"handleSubmit\" type=\"primary\" long>登录</Button>\n" +
        "    </FormItem>\n" +
        "  </Form>";
return {
    name: 'LoginForm',
    template:template,
    props: {
        userNameRules: {
            type: Array,
            default: () => {
                return [
                    { required: true, message: '账号不能为空', trigger: 'blur' }
                ]
            }
        },
        passwordRules: {
            type: Array,
            default: () => {
                return [
                    { required: true, message: '密码不能为空', trigger: 'blur' }
                ]
            }
        }
    },
    data () {
        return {
            form: {
                userName: 'super_admin',
                password: ''
            }
        }
    },
    computed: {
        rules () {
            return {
                userName: this.userNameRules,
                password: this.passwordRules
            }
        }
    },
    methods: {
        handleSubmit () {
            this.$refs.loginForm.validate((valid) => {
                if (valid) {
                    this.$emit('on-success-valid', {
                        userName: this.form.userName,
                        password: this.form.password
                    })
                }
            })
        }
    }
}
});