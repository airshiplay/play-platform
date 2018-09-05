define([],function () {
    return {
        props: {
            parentItem: {
                type: Object,
                default: () => {}
            },
            theme: String,
            iconSize: Number
        },
        computed: {
            parentName () {
              // debugger
                return this.parentItem.name
            },
            children () {
                // debugger
                return this.parentItem.children
            },
            textColor () {
                return this.theme === 'dark' ? '#fff' : '#495060'
            }
        }
    }
})
