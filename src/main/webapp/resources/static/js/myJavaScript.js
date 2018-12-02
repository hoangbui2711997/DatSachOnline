var vmSideBar = new Vue({
    el: '#sideBar',
    data: {
        isCollapse: false
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath)
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath)
        },
        toggleOpen() {
            this.isCollapse = false
            console.log(this.isCollapse)
        },
        toggleClose() {
            this.isCollapse = true
            console.log(this.isCollapse)
        }
    }
})
//
// var global = new Vue({
//     el: '#global'
// })
var vm1 = new Vue({
    el: '#header',
    data() {
        return {
            searchGlobal: '',
            showSearch: true,
            dialogFormVisible: false,
            storeData: [],
            total: null
        }
    },
    methods: {
        handleSearch() {
            // console.log(this.$refs.search)
            this.searchGlobal = this.$refs.search.value
            console.log(this.$refs.search.value)
        },
        showCard() {
            axios
                .get('/api/store/getTotal', {})
                .then((resp) => vm1.$data.total = resp.data)
                .catch((error) => console.log('Loi: ' + error))

            axios
                .get('/api/store/getGioHang', {})
                .then((resp) => vm1.$data.storeData = resp.data
                )
                .catch((error) => console.log('Loi: ' + error))

            this.dialogFormVisible = true;
        },
        handleChange() {
            console.log('change')
        }
    }
})