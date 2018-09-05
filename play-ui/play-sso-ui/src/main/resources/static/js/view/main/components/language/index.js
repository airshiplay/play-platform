define([],function () {

    var template="<div>\n" +
        "    <Dropdown trigger=\"click\" @on-click=\"selectLang\">\n" +
        "      <a href=\"javascript:void(0)\">\n" +
        "        {{ title }}\n" +
        "        <Icon :size=\"18\" type=\"md-arrow-dropdown\" />\n" +
        "      </a>\n" +
        "      <DropdownMenu slot=\"list\">\n" +
        "        <DropdownItem v-for=\"(value, key) in localList\" :name=\"key\" :key=\"`lang-${key}`\">{{ value }}</DropdownItem>\n" +
        "      </DropdownMenu>\n" +
        "    </Dropdown>\n" +
        "  </div>";
    return{
        name: 'Language',
        template:template,
        props: {
            lang: String
        },
        data () {
            return {
                langList: {
                    'zh-CN': '语言',
                    'zh-TW': '語言',
                    'en-US': 'Lang'
                },
                localList: {
                    'zh-CN': '中文简体',
                    'zh-TW': '中文繁体',
                    'en-US': 'English'
                }
            }
        },
        watch: {
            lang (lang) {
                this.$i18n.locale = lang
            }
        },
        computed: {
            title () {
                return this.langList[this.lang]
            }
        },
        methods: {
            selectLang (name) {
                this.$emit('on-lang-change', name)
            }
        }
    }
})
