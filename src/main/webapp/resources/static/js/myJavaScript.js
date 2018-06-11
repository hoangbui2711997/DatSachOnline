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
            searchGlobal: ''
        }
    },
    methods: {
        handleSearch() {
        }
    }
})

var vm = new Vue({
    el: '#sachList',
    data() {
        return {
            lstSach: [],
            dialogVisible: false,
            txtSearch: ''
        }
    },
    methods: {
        doDelete(sach) {
            axios({
                method: 'get',
                url: '/api/admin/doDeleteSach/' + sach.maSach,
            }).then(function (response) {
                console.log(response)
                if (response.data === 1) {
                    var indexDel = this.vm._data.lstSach.indexOf(sach)
                    console.log(indexDel)
                    this.vm._data.lstSach.splice(indexDel, 1)
                }
            })
        }
    },
    computed: {
        lstSearch() {
            // if (vm1._data.searchGlobal !== '') {
            //     return this.lstSach.filter(function (e) {
            //         // console.log(e.tenSach.includes(this.txtSearch))
            //         return e.tenSach.includes(vm1._data.searchGlobal)
            //     })
            // } else {
            //     console.log(this.lstSach)
            //     return this.lstSach
            // }

            return this.lstSach.filter(function (value) {
                return value.tenSach.includes(vm1._data.searchGlobal)
            })
        }
    },
    beforeCreate() {
        axios.get("/api/admin/getSach").then(function (response) {
                this.vm._data.lstSach = response.data
                console.log(response.data)
            }
        ).catch(function (reason) {
            console.log('loi roi ' + reason)
        })
    }
})