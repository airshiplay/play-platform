
    define(["less!./sider-trigger.less"],function () {
        return {
            name: 'siderTrigger',
            template:"<a @click=\"handleChange\" type=\"text\" :class=\"['sider-trigger-a', collapsed ? 'collapsed' : '']\"><Icon :type=\"icon\" :size=\"size\" /></a>",
            props: {
                collapsed: Boolean,
                icon: {
                    type: String,
                    default: 'navicon-round'
                },
                size: {
                    type: Number,
                    default: 26
                }
            },
            methods: {
                handleChange () {
                    this.$emit('on-change', !this.collapsed)
                }
            }
        }
    })
