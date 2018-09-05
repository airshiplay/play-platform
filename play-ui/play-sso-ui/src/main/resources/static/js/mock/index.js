define(["mockjs","config/index","./login"],function (Mock,config,login) {

    // debugger
    if(config.env === 'development'){
        Mock.mock('http://localhost:9999/login', function(){
            // debugger
            return "";
        })

        Mock.mock(/\/get_info/, function (req) {
            const params = getParams(req.url)
            return {
                code: 200,
                data:{},
                msg: ''
            }
        })
    }
    return Mock
})