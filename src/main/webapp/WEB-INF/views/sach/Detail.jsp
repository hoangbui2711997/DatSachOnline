<%@ include file="../fragment/header.jsp" %>
<style>
    #chiTiet tr td:first-child {
        background: #efefef;
        font-weight: bold;
    }

    #chiTiet {
        font-size: small;
    }
</style>
<div id="detailBookX" class="container-fluid">
    <section id="detailBook" class="pt-4 container">
        <div class="el-row">
            <div class="el-col-10 pr-3">
                <div style="">
                    <img style="width: 100%; height: 25rem" :src="sach.biaSach" alt="">
                </div>
            </div>
            <div class="el-col-14 pl-3 my-3">
                <h2>{{ sach.tenSach }}</h2>
                <div class="block mt-4">
                    <el-rate
                            v-model="sach.danhGia"
                            :texts="['oops', 'disappointed', 'normal', 'good', 'great']"
                            :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                            show-text
                    >
                    </el-rate>
                </div>
                <div>
                    Tác giả: <span v-for="(tacGia,i) in sach.lstTacGia">

                <a href="#" v-if="sach.lstTacGia.length - 1 == i">{{tacGia}}</a>
                <a href="#" v-else>{{tacGia}}, </a>
            </span>
                </div>
                <hr>
                <div>
                    <div class="my-3">
                        <span class="text-muted">Giá sách:</span>
                        <span class="myCard--price" style="color: red; font-size: medium">{{sach.giaSachBan
                                                - (sach.giaSachBan * sach.tiLeKhuyenMai / 100)}}
                                        </span>
                        <br>
                        <span class="text-muted">Tiết kiệm:</span>
                        <span class="myCard--sale pl-2">{{sach.tiLeKhuyenMai}}</span>
                        <span class="text-muted small">(
                         {{sach.giaSachBan - (sach.giaSachBan - (sach.giaSachBan * sach.tiLeKhuyenMai / 100))}} đồng
                            /sách
                        )
                        </span>
                        <br>
                        <span class="text-muted">
                            Giá thị trường:
                            <small class="pl-2 myCard--price myCard--priceOld">
                                 {{sach.giaSachBan}}
                            </small>
                        </span>
                    </div>
                    <hr>
                    <div class="py-3">
                        <span class="text-muted small">
                            Số lượng:
                        </span>
                        <el-input-number
                                size="small"
                                v-model="soLuong" controls-position="right" :min="0">
                        </el-input-number>
                        <br>
                        <div class="my-3">
                            <span class="myCard--price">
                                <span class="text-muted small">Ước tính giá thanh toán của sản phẩm: </span>
                                {{ (sach.giaSachBan - (sach.giaSachBan * sach.tiLeKhuyenMai / 100)) * soLuong }}
                            </span>
                            <small class="text-muted pl-2 myCard--priceOld">{{sach.giaSachBan * soLuong}}$</small>
                        </div>
                        <div class="my-3">
                            <span class="myCard--price">
                                <span class="text-muted small">Ưu đãi: </span>
                                {{ (sach.giaSachBan - (sach.giaSachBan - (sach.giaSachBan * sach.tiLeKhuyenMai / 100))) * soLuong }}
                            </span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <form method="post">
            <el-button class="float-right" type="danger" icon="el-icon-goods" @click="addBook">Đưa vào giỏ</el-button>
        </form>
        <div class="container mt-5">
            <table id="chiTiet" class="table">
                <tr>
                    <td>Công ty phát hành</td>
                    <td>{{sach.congTyPhatHanh}}</td>
                </tr>
                <tr>
                    <td>Nhà xuất bản</td>
                    <td>{{sach.nhaXuatBan}}</td>
                </tr>
                <tr>
                    <td>Kích thước</td>
                    <td>{{sach.kickThuoc}}</td>
                </tr>
                <tr>

                    <td>Tác giả</td>
                    <td>
                <span v-for="(tacGia, index) in sach.lstTacGia">
                    <span v-if="index != sach.lstTacGia.length - 1">
                        {{ tacGia }},
                    </span>
                    <span v-else>
                        {{ tacGia}}
                    </span>
                </span>
                    </td>
                </tr>
                <tr>
                    <td>Số trang</td>
                    <td>{{sach.soTrang}}</td>
                </tr>
                <tr>
                    <td>Ngày xuất bản</td>
                    <td>{{sach.ngayXuatBan}}</td>
                </tr>
            </table>
        </div>
    </section>
</div>
<%@ include file="../fragment/footer.jsp" %>
<script>
    var tempDetail = new Vue({
        el: '#detailBook',
        data() {
            return {
                sach: {
                    maSach: ${sach.maSach},
                    soTrang: ${sach.soTrang},
                    ngayXuatBan: ${sach.ngayXuatBan},
                    giaSachBan: ${sach.giaSachBan},
                    kickThuoc: `${sach.kickThuoc}`,
                    tiLeKhuyenMai: ${sach.tiLeKhuyenMai},
                    gioiThieu: `${sach.gioiThieu}`,
                    nhaXuatBan: `${sach.nhaXuatBan}`,
                    tenSach: `${sach.tenSach}`,
                    congTyPhatHanh: `${sach.congTyPhatHanh}`,
                    biaSach: `${sach.biaSach}`,
                    danhGia: ${sach.danhGia},

                    lstTacGia: []
                },
                soLuong: ${soLuong} === null ? 0 : ${soLuong}
            }
        },
        methods: {
            addBook() {
                axios.get('<spring:url value="/store/add" />', {
                    params: {
                        'id': tempDetail.$data.sach.maSach,
                        'soLuong': tempDetail.$data.soLuong
                    }
                }).then(function (resp) {
                    console.log(resp.data)
                }).catch(function (error) {
                    console.log(error)
                })
            }
        },
        beforeCreate() {
            axios.get('<spring:url value="/sach/getTacGia/${sach.maSach}" />', {})
                .then(function (response) {
                    // Truy?n d? li?u cho vue vueData
                    tempDetail._data.sach.lstTacGia = response.data
                    console.log(response.data)
                })
                .catch(function (error) {
                    console.log(error)
                    alert("Error")
                });
        }
    })
</script>