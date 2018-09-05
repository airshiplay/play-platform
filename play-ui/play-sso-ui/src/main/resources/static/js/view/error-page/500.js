define(["./error-content"],function (errorContent) {
  var error404='images/error-page/error-500.svg';
   return  {
       name: 'error_500',
       template:'<error-content code="500" desc="Oh~~鬼知道服务器经历了什么~" :src="src"/>',
       components: {
           errorContent
       },
       data () {
           return {
               src: error404
           }
       }
   }
})
