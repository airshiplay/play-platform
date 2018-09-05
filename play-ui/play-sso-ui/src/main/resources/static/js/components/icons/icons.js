define([],function () {
        return {
            name: 'Icons',
            template:" <i :class=\"`iconfont icon-${type}`\" :style=\"styles\"></i>",
            props: {
                type: {
                    type: String,
                    required: true
                },
                color: {
                    type: String,
                    default: '#5c6b77'
                },
                size: {
                    type: Number,
                    default: 16
                }
            },
            computed: {
                styles () {
                    return {
                        fontSize: `${this.size}px`,
                        color: this.color
                    }
                }
            }
        }
})
