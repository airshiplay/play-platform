define(["api/user","libs/util"],function(api,util){
    return {
        state: {
            userName: '',
            userId: '',
            avatorImgPath: '',
            token: util.getToken(),
            access: ''
        },
        mutations: {
            setAvator (state, avatorPath) {
                state.avatorImgPath = avatorPath
            },
            setUserId (state, id) {
                state.userId = id
            },
            setUserName (state, name) {
                state.userName = name
            },
            setAccess (state, access) {
                state.access = access
            },
            setToken (state, token) {
                state.token = token
                util.setToken(token)
            }
        },
        actions: {
            // 登录
            handleLogin ({ commit }, {userName, password}) {
                userName = userName.trim()

                return new Promise((resolve, reject) => {
                    api.login({
                        userName,
                        password
                    }).then(res => {
                        debugger
                        const data = res.data
                        commit('setToken', data.token)
                        resolve()
                    }).catch(err => {
                        reject(err)
                    })
                })
            },
            // 退出登录
            handleLogOut ({ state, commit }) {
                return new Promise((resolve, reject) => {
                    api.logout(state.token).then(() => {
                        commit('setToken', '')
                        commit('setAccess', [])
                        resolve()
                    }).catch(err => {
                        reject(err)
                    })
                    // 如果你的退出登录无需请求接口，则可以直接使用下面三行代码而无需使用logout调用接口
                    // commit('setToken', '')
                    // commit('setAccess', [])
                    // resolve()
                })
            },
            // 获取用户相关信息
            getUserInfo ({ state, commit }) {
                debugger
                return new Promise((resolve, reject) => {
                    api.getUserInfo(state.token).then(res => {
                        const data = res.data
                        commit('setAvator', data.avator)
                        commit('setUserName', data.user_name)
                        commit('setUserId', data.user_id)
                        commit('setAccess', data.access)
                        resolve(data)
                    }).catch(err => {
                        reject(err)
                    })
                })
            }
        }
    }
})