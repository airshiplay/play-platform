define(["libs/util","router/routers"],function(util,routers){
    return {
        state:{
            breadCrumbList: [],
            tagNavList: [],
            homeRoute: util.getHomeRoute(routers),
            local: ''
        },
        getters: {
            menuList: (state, getters, rootState) => util.getMenuByRouter(routers, rootState.user.access)
        },
        mutations:{
            setBreadCrumb (state, routeMetched) {
                debugger
                state.breadCrumbList = util.getBreadCrumbList(routeMetched, state.homeRoute)
            },
            setTagNavList (state, list) {
                if (list) {
                    state.tagNavList = [...list]
                    util.setTagNavListInLocalstorage([...list])
                } else state.tagNavList = util.getTagNavListFromLocalstorage()
            },
            addTag (state, item, type = 'unshift') {
                if (state.tagNavList.findIndex(tag => tag.name === item.name) < 0) {
                    if (type === 'push') state.tagNavList.push(item)
                    else state.tagNavList.unshift(item)
                    util.setTagNavListInLocalstorage([...state.tagNavList])
                }
            },
            setLocal (state, lang) {
                state.local = lang
            }
        }
    }
})