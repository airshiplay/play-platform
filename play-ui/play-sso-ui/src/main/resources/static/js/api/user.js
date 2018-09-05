define(["axios"],function (axios) {
    return{
        login:function(userName, password ){
            const data = {
                userName,
                password
            }
            debugger
            return axios.request({
                url: 'login',
                data,
                method: 'post'
            })
        },
        getUserInfo:function (token) {
            return axios.request({
                url: 'get_info',
                params: {
                    token
                },
                method: 'get'
            })
        },
        logout:function () {
            return axios.request({
                url: 'logout',
                method: 'post'
            })
        }
    }
})