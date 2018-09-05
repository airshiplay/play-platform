define([],function(){
    return {
        login:function(req){
            debugger
            req = JSON.parse(req.body)
            return {
                code: 200,
                data: {token: "ddd"},
                msg: ''
            }
        }
    }
})