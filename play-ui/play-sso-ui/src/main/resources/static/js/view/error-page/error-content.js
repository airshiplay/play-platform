define(["./back-btn-group","css!../../../css/error-page/error.css"],function (backBtnGroup) {

    return {
        name: 'error_content',
        template:"  <div class=\"error-page\">\n" +
        "    <div class=\"content-con\">\n" +
        "      <img :src=\"src\" alt=\"404\">\n" +
        "      <div class=\"text-con\">\n" +
        "        <h4>{{ code }}</h4>\n" +
        "        <h5>{{ desc }}</h5>\n" +
        "      </div>\n" +
        "      <back-btn-group class=\"back-btn-group\"></back-btn-group>\n" +
        "    </div>\n" +
        "  </div>",
        components: {
            backBtnGroup
        },
        props: {
            code: String,
            desc: String,
            src: String
        }
    }
});