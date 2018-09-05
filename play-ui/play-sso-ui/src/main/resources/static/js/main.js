var baseUrl = './js';
var assetsPath = "http://localhost:9999/";
requirejs.config({
    baseUrl: baseUrl,
    paths:{
        "promise":[assetsPath+"webjars/q-1.5.1/q","https://cdn.bootcss.com/q.js/1.5.1/q"],
        "vue":[assetsPath+"webjars/vue-2.5.17/dist/vue","https://cdn.bootcss.com/vue/2.5.16/vue.min"],
        "vuex":[assetsPath+"webjars/vuex-3.0.1/dist/vuex","https://cdn.bootcss.com/vuex/3.0.1/vuex.min"],
        "vue.router":[assetsPath+"webjars/vue-router-3.0.1/dist/vue-router","https://cdn.bootcss.com/vue-router/3.0.1/vue-router.min"],
        "text":assetsPath+"webjars/requirejs/text",
        "css":assetsPath+"webjars/require-css/css.min",
        "less":assetsPath+"webjars/require-less/less",
        "lessc":assetsPath+"webjars/require-less/lessc",
        "normalize":assetsPath+"webjars/require-less/normalize",
        "js-cookie":[assetsPath+"webjars/js-cookie-2.2.0/src/js.cookie","https://cdn.bootcss.com/js-cookie/2.2.0/js.cookie.min"],
        "vue-i18n":[assetsPath+"webjars/vue-i18n-8.0.0/dist/vue-i18n","https://cdn.bootcss.com/vue-i18n/7.7.0/vue-i18n.min"],
         "iview":[assetsPath+"webjars/iview-3.0.0/dist/iview","http://unpkg.com/iview/dist/iview.min"],
        "zh_CN": [assetsPath+"webjars/iview-3.0.0/dist/locale/zh-CN",",//unpkg.com/iview/dist/locale/zh-CN"],
        "en_US":[assetsPath+"webjars/iview-3.0.0/dist/locale/en-US","//unpkg.com/iview/dist/locale/en-US"],
        "zh_TW":[assetsPath+"webjars/iview-3.0.0/dist/locale/zh-TW","//unpkg.com/iview/dist/locale/zh-TW"],
        "mockjs":[assetsPath+"webjars/Mock-1.0.0/dist/mock","https://cdn.bootcss.com/Mock.js/1.0.0/mock"],
        "axios":[assetsPath+"webjars/axios-0.19.0-beta.1/dist/axios","https://cdn.bootcss.com/axios/0.18.0/axios"]
    },
    waitSeconds: 15,
    map:{

    },
    urlArgs: "version=" + Date.now(),

    shim: {
        zh_CN:{
            exports:"zh_CN"
        },
        en_US:{
            exports:"en_US"
        },
        zh_TW:{
            exports:"zh_TW"
        },
        promise:{
            exports:"Q"
        },
        "vue": {
            exports: "Vue"
        },
        "vuex": {
            exports: "Vuex"
        },
        "vue.router": {
            exports: "VueRouter"
        },
        less: ["lessc","normalize"]

    },
    callback:function(){
    },
    deps:["vue","vue.router","vuex","promise","app"] // 默认要加载的js
});
