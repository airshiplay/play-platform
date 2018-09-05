define(["ResolveComponent"], function(ResolveComponent) {

    var routes = [];

    routes.push({
        path:'/login',
        name:'login',
        meta:{
            title:'Login-登录',
            hideInMenu:true
        },
        component:ResolveComponent("view/login/login")
    });
    routes.push({
        path: '/',
        name: '_home',
        redirect: '/home',
        component:ResolveComponent("view/main/Main"),
        meta: {
            hideInMenu: true,
            notCache: true
        },
        children: [
            {
                path: '/home',
                name: 'home',
                meta: {
                    hideInMenu: true,
                    title: '首页',
                    notCache: true
                },
                component: ResolveComponent('view/home')
            }
        ]
    });

    routes.push({
        path: '/sys',
        name:'sys',
        component: ResolveComponent("view/main/Main"),
        children: [
            {
                path: '/account',
                name: 'account',
                meta: {
                    icon: 'md-trending-up',
                    title: '用户管理'
                },
                component: ResolveComponent("account/account_index")
            }
        ]
    });


    routes.push({
        path: '/authorization',
        component: ResolveComponent("authorization/authorization_index")
    });

    routes.push({
        path: '/member',
        component: ResolveComponent("member/member_index")
    });

    routes.push({
        path: '/401',
            name: 'error_401',
        meta: {
        hideInMenu: true
    },
        component: ResolveComponent('view/error-page/401')
    });
    routes.push({
        path: '/500',
            name: 'error_500',
        meta: {
        hideInMenu: true
    },
        component: ResolveComponent('view/error-page/500')
    });
    routes.push(
        {
            path: '*',
            name: 'error_404',
            meta: {
                hideInMenu: true
            },
            component: ResolveComponent('view/error-page/404')
        });

    return routes;

});