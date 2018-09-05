define(["./error-content"],function (errorContent) {
  var  error404 ="img/error-page/error-404.svg";
    return  {
        name: 'error_404',
        template:'<error-content code="404" desc="Oh~~您的页面好像飞走了~" :src="src"/>',
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
