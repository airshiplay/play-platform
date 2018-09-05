define([],function () {
    return{
        name: 'backBtnGroup',
        template:"<div>\n" +
        "    <Button size=\"large\" type=\"text\" @click=\"backHome\">返回首页</Button>\n" +
        "    <Button size=\"large\" type=\"text\">返回上一页({{ second }}s)</Button>\n" +
        "  </div>",
        data () {
            return {
                second: 5,
                timer: null
            }
        },
        methods: {
            backHome () {
                this.$router.replace({
                    name: 'home'
                })
            },
            backPrev () {
                this.$router.go(-1)
            }
        },
        mounted () {
            this.timer = setInterval(() => {
                if (this.second === 0) this.backPrev()
                else this.second--
            }, 1000)
        },
        beforeDestroy () {
            clearInterval(this.timer)
        }
    }
})