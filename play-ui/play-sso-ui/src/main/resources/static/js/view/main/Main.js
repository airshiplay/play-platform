define(["store/index","./components/side-menu/side-menu-index",
    "./components/header-bar/header-bar","./components/language/index","./components/user/index","./components/fullscreen/index","./components/tags-nav/index",
    "vuex","libs/util","text!view/main/main.html","less!./main.less"],
    function (store,SideMenu,HeaderBar,Language,User,Fullscreen,TagsNav,Vuex,util,template) {
       var minLogo="img/logo-min.jpg";
       var     maxLogo="img/logo.jpg";
    return{
        name: 'Main',
        template:template,
        data:function(){
            return {
                collapsed: false,
                minLogo,
                maxLogo,
                isFullscreen: false
            }
        },
        components: {
            SideMenu,HeaderBar,Language,User,Fullscreen,TagsNav
        },
        computed:{
            menuList () {
                return [
                    {
                        name: "1",
                        icon: 'ios-book',
                        meta: {
                            title: '文档',
                            href: 'https://lison16.github.io/iview-admin-doc/#/',
                        }
                    },
                    {
                        name: "sys",
                        icon: 'logo-buffer',
                        meta: {
                            title: '组件'
                        },
                        children: [
                            {
                                name: "account",icon: '_qq',
                                meta: {
                                    title: 'QQ群'
                                }
                            },
                            {
                                name: "2-1",icon: 'md-grid',
                                meta: {
                                    title: 'QQ群'
                                }
                            }
                        ]
                    }

                ];
            },
            tagNavList () {
                return this.$store.state.app.tagNavList
            },
            tagRouter () {
                return this.$store.state.app.tagRouter
            },
            userAvator () {
                return this.$store.state.user.avatorImgPath
            },
            cacheList () {
                return this.tagNavList.length ? this.tagNavList.filter(item => !(item.meta && item.meta.notCache)).map(item => item.name) : []
            },
            // menuList () {
            //     return this.$store.getters.menuList
            // },
            local () {
                return this.$store.state.app.local
            }
        },
        methods:{
            ...Vuex.mapMutations([
                'setBreadCrumb',
                'setTagNavList',
                'addTag',
                'setLocal'
            ]),
            ...Vuex.mapActions([
                'handleLogin'
            ]),
            turnToPage (name) {
                if (name.indexOf('isTurnByHref_') > -1) {
                    window.open(name.split('_')[1])
                    return
                }
                this.$router.push({
                    name: name
                })
            },
            handleCollapsedChange (state) {
                this.collapsed = state
            },
            handleCloseTag (res, type, name) {
                const nextName = util.getNextName(this.tagNavList, name)
                this.setTagNavList(res)
                let openName = ''
                if (type === 'all') {
                    this.turnToPage('home')
                    openName = 'home'
                } else if (this.$route.name === name) {
                    this.$router.push({ name: nextName })
                    openName = nextName
                }
                this.$refs.sideMenu.updateOpenName(openName)
            },
            handleClick (item) {
                this.turnToPage(item.name)
            }
        },
        watch: {
            '$route' (newRoute) {
                this.setBreadCrumb(newRoute.matched)
                this.setTagNavList(util.getNewTagList(this.tagNavList, newRoute))
            }
        },
        mounted () {
            /**
             * @description 初始化设置面包屑导航和标签导航
             */
            this.setTagNavList()
            this.addTag(this.$store.state.app.homeRoute)
            this.setBreadCrumb(this.$route.matched)
            // 设置初始语言
            this.setLocal(this.$i18n.locale)
            // 文档提示
            // this.$Notice.info({
            //     title: '想快速上手，去看文档吧',
            //     duration: 0,
            //     render: (h) => {
            //         return h('p', {
            //             style: {
            //                 fontSize: '13px'
            //             }
            //         }, [
            //             '点击',
            //             h('a', {
            //                 attrs: {
            //                     href: 'https://lison16.github.io/iview-admin-doc/#/',
            //                     target: '_blank'
            //                 }
            //             }, 'iview-admin2.0文档'),
            //             '快速查看'
            //         ])
            //     }
            // })
        }
    }
});