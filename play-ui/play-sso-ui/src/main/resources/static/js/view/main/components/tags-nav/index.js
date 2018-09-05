define(["libs/util", "less!./tags-nav.less"], function (util) {
    var template="<div class=\"tags-nav\">\n" +
        "    <div class=\"close-con\">\n" +
        "      <Dropdown transfer @on-click=\"handleTagsOption\" style=\"margin-top:7px;\">\n" +
        "        <Button size=\"small\" type=\"text\">\n" +
        "          <Icon :size=\"18\" type=\"ios-close-circle-outline\" />\n" +
        "        </Button>\n" +
        "        <DropdownMenu slot=\"list\">\n" +
        "          <DropdownItem name=\"close-all\">关闭所有</DropdownItem>\n" +
        "          <DropdownItem name=\"close-others\">关闭其他</DropdownItem>\n" +
        "        </DropdownMenu>\n" +
        "      </Dropdown>\n" +
        "    </div>\n" +
        "    <div class=\"btn-con left-btn\">\n" +
        "      <Button type=\"text\" @click=\"handleScroll(240)\">\n" +
        "        <Icon :size=\"18\" type=\"ios-arrow-back\" />\n" +
        "      </Button>\n" +
        "    </div>\n" +
        "    <div class=\"btn-con right-btn\">\n" +
        "      <Button type=\"text\" @click=\"handleScroll(-240)\">\n" +
        "        <Icon :size=\"18\" type=\"ios-arrow-forward\" />\n" +
        "      </Button>\n" +
        "    </div>\n" +
        "    <div class=\"scroll-outer\" ref=\"scrollOuter\" @DOMMouseScroll=\"handlescroll\" @mousewheel=\"handlescroll\">\n" +
        "      <div ref=\"scrollBody\" class=\"scroll-body\" :style=\"{left: tagBodyLeft + 'px'}\">\n" +
        "        <transition-group name=\"taglist-moving-animation\">\n" +
        "          <Tag\n" +
        "            type=\"dot\"\n" +
        "            v-for=\"item in list\"\n" +
        "            ref=\"tagsPageOpened\"\n" +
        "            :key=\"`tag-nav-${item.name}`\"\n" +
        "            :name=\"item.name\"\n" +
        "            @on-close=\"handleClose\"\n" +
        "            @click.native=\"handleClick(item)\"\n" +
        "            :closable=\"item.name !== 'home'\"\n" +
        "            :color=\"item.name === value.name ? 'primary' : 'default'\"\n" +
        "          >{{ showTitleInside(item) }}</Tag>\n" +
        "        </transition-group>\n" +
        "      </div>\n" +
        "    </div>\n" +
        "  </div>";
    return {
        name: 'TagsNav',
        template:template,
        props: {
            value: Object,
            list: {
                type: Array,
                default() {
                    return []
                }
            }
        },
        data() {
            return {
                tagBodyLeft: 0,
                rightOffset: 40,
                outerPadding: 4
            }
        },
        methods: {
            handlescroll(e) {
                var type = e.type
                let delta = 0
                if (type === 'DOMMouseScroll' || type === 'mousewheel') {
                    delta = (e.wheelDelta) ? e.wheelDelta : -(e.detail || 0) * 40
                }
                this.handleScroll(delta)
            },
            handleScroll(offset) {
                const outerWidth = this.$refs.scrollOuter.offsetWidth
                const bodyWidth = this.$refs.scrollBody.offsetWidth
                if (offset > 0) {
                    this.tagBodyLeft = Math.min(0, this.tagBodyLeft + offset)
                } else {
                    if (outerWidth < bodyWidth) {
                        if (this.tagBodyLeft < -(bodyWidth - outerWidth)) {
                            this.tagBodyLeft = this.tagBodyLeft
                        } else {
                            this.tagBodyLeft = Math.max(this.tagBodyLeft + offset, outerWidth - bodyWidth)
                        }
                    } else {
                        this.tagBodyLeft = 0
                    }
                }
            },
            handleTagsOption(type) {
                if (type === 'close-all') {
                    // 关闭所有，除了home
                    let res = this.list.filter(item => item.name === 'home')
                    this.$emit('on-close', res, 'all')
                } else {
                    // 关闭除当前页和home页的其他页
                    let currentName = ''
                    let res = this.list.filter(item => {
                        if (item.name === this.value.name) currentName = item.name
                        return item.name === this.value.name || item.name === 'home'
                    })
                    this.$emit('on-close', res, 'others')
                    setTimeout(() => {
                        this.getTagElementByName(currentName)
                    }, 100)
                }
            },
            handleClose(e, name) {
                let res = this.list.filter(item => item.name !== name)
                this.$emit('on-close', res, undefined, name)
            },
            handleClick(item) {
                this.$emit('input', item)
            },
            showTitleInside(item) {
                return util.showTitle(item, this)
            },
            moveToView(tag) {
                const outerWidth = this.$refs.scrollOuter.offsetWidth
                const bodyWidth = this.$refs.scrollBody.offsetWidth
                if (bodyWidth < outerWidth) {
                    this.tagBodyLeft = 0
                } else if (tag.offsetLeft < -this.tagBodyLeft) {
                    // 标签在可视区域左侧
                    this.tagBodyLeft = -tag.offsetLeft + this.outerPadding
                } else if (tag.offsetLeft > -this.tagBodyLeft && tag.offsetLeft + tag.offsetWidth < -this.tagBodyLeft + outerWidth) {
                    // 标签在可视区域
                    this.tagBodyLeft = Math.min(0, outerWidth - tag.offsetWidth - tag.offsetLeft - this.outerPadding)
                } else {
                    // 标签在可视区域右侧
                    this.tagBodyLeft = -(tag.offsetLeft - (outerWidth - this.outerPadding - tag.offsetWidth))
                }
            },
            getTagElementByName(name) {
                this.$nextTick(() => {
                    this.refsTag = this.$refs.tagsPageOpened
                    this.refsTag.forEach((item, index) => {
                        if (name === item.name) {
                            let tag = this.refsTag[index].$el
                            this.moveToView(tag)
                        }
                    })
                })
            }
        },
        watch: {
            '$route'(to) {
                this.getTagElementByName(to.name)
            }
        },
        mounted() {
            setTimeout(() => {
                this.getTagElementByName(this.$route.name)
            }, 200)
        }
    }
})