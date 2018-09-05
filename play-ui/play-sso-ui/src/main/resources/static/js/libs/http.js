define(["axios"],function (axios) {
    axios.interceptors.request.use(function(config){

    },function(error){
        return Promise.reject(error)
    })
    axios.interceptors.response.use(function(response) {
        return response;
    }, function(error) {
        return Promise.reject(error);
    })
})