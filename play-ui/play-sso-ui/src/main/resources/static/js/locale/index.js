// import Vue from 'vue'
// import VueI18n from 'vue-i18n'
// import customZhCn from './lang/zh-CN'
// import customZhTw from './lang/zh-TW'
// import customEnUs from './lang/en-US'
// import zhCnLocale from 'iview/src/locale/lang/zh-CN'
// import enUsLocale from 'iview/src/locale/lang/en-US'
// import zhTwLocale from 'iview/src/locale/lang/zh-TW'

define(["vue","vue-i18n","./lang/zh-CN","./lang/zh-TW","./lang/en-US","iview",
        "zh_CN","en_US","zh_TW"],
    function (Vue,VueI18n,customZhCn,customZhTw,customEnUs,iView,zhCnLocale,enUsLocale,zhTwLocale) {
        Vue.use(VueI18n)
        Vue.use(iView);
// 自动根据浏览器系统语言设置语言
        const navLang = navigator.language
        const localLang = (navLang === 'zh-CN' || navLang === 'en-US') ? navLang : false
        let lang = window.localStorage.lang || localLang || 'zh-CN'

        Vue.config.lang = lang

// vue-i18n 6.x+写法
        Vue.locale = () => {}
        const messages = {
            'zh-CN': Object.assign(iview.langs['zh-CN'], customZhCn),
            // 'zh-TW': Object.assign(iview.langs['zh-TW'], customZhTw),
            // 'en-US': Object.assign(iview.langs['en-US'], customEnUs)

            //
            // 'zh-TW': Object.assign(zhTwLocale, customZhTw),
            // 'en-US': Object.assign(enUsLocale, customEnUs)
        }
        const i18n = new VueI18n({
            locale: lang,
            messages
        })
        return i18n;
})



// export default i18n

// vue-i18n 5.x写法
// Vue.locale('zh-CN', Object.assign(zhCnLocale, customZhCn))
// Vue.locale('en-US', Object.assign(zhTwLocale, customZhTw))
// Vue.locale('zh-TW', Object.assign(enUsLocale, customEnUs))
