define([],function () {
    return{
        showTitle :function  (item, vm) {
            return vm.$config.useI18n ? vm.$t(item.name) : ((item.meta && item.meta.title) || item.name)
        }
    }
})
