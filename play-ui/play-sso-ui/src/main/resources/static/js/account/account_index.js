define(["axios","text!../../pages/account/account_index.html","css!../../css/account/account_index.css"], function(axios,template) {

    var data = {
        list:[
            {id:"001",name:"小王"},
            {id:"002",name:"大王"},
            {id:"003",name:"老王"}
        ]
    };

    var methods = {
        say:function(item){
            alert("我居然是"+item.name);
        }
    };
    axios.get('/user?ID=12345')
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });

    return {
        methods:methods,
        template:template,
        data:function(){
            return data
        }
    }


});