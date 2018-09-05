define(["vue","vue.router","router/index","iview","store/index","config/index","locale/index","mock/index","css!../../css/icons/iconfont.css"], function(Vue,VueRouter,router,iView,store,config,i18n) {

    Vue.use(VueRouter);
    Vue.use(iView);
    /**
     * @description 全局注册应用配置
     */
    Vue.prototype.$config = config
    Vue.use(iView, {
        i18n: (key, value) => i18n.t(key, value)
    })
    var app = new Vue({
        router:router,
        store:store,
        i18n:i18n,
        el:"#app"
    });

});