define(["components/icons/icons"],function (Icons) {
    return {
        name: 'CommonIcon',
        template:"<component :is=\"iconType\" :type=\"iconName\" :color=\"iconColor\" :size=\"iconSize\"/>",
        components: {Icons},
        props: {
            type: {
                type: String,
                required: true
            },
            color: String,
            size: Number
        },
        computed: {
            iconType () {
                return this.type.indexOf('_') === 0 ? 'Icons' : 'Icon'
            },
            iconName () {
                return this.iconType === 'Icons' ? this.getCustomIconName(this.type) : this.type
            },
            iconSize () {
                return this.size || (this.iconType === 'Icons' ? 12 : undefined)
            },
            iconColor () {
                return this.color || ''
            }
        },
        methods: {
            getCustomIconName (iconName) {
                return iconName.slice(1)
            }
        }
    };
})
