<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dev-wu
  Date: 06/06/2018
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../fragment/header.jsp" %>

<section class="section-body container-fluid">
    <div class="row">
        <div class="col-2">
            <%@ include file="../fragment/sideBar.jsp" %>
        </div>
        <el-col :span="18" id="sachList">
            <el-row>
                <el-col :span="24">
                    <a href="<spring:url value="/sach/create" />" target="_blank">
                        <button class="btn btn-primary">Create new</button>
                    </a>
                    <el-pagination
                            background
                            layout="prev, pager, next"
                            :page-size="5"
                            :total="lstSearch.length"
                            class="text-right"
                            @current-change="currentChange"
                    >
                    </el-pagination>
                </el-col>
                <el-col :span="24">
                    <table v-if="lstSach != null"
                           class="table table-hover table-striped mt-2 text-center">
                        <thead class="bg-info" style="font-weight: bold;">
                        <tr>
                            <td style="max-width: 10rem">
                                Name
                            </td>
                            <td>
                                In-price
                            </td>
                            <td>
                                Out-price
                            </td>
                            <td>
                                Number of pages
                            </td>
                            <td>
                                Sale
                            </td>
                            <td>
                                Day-released
                            </td>
                            <td>

                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr :id="sach.maSach" v-for="sach in lstSearchPagging" name="maSach">
                            <td style="max-width: 10rem;" class="">
                                {{ sach.tenSach }}
                            </td>
                            <td>
                                {{ sach.giaSachNhap }}
                            </td>
                            <td>
                                {{sach.giaSachBan}}
                            </td>
                            <td>
                                {{sach.soTrang}}
                            </td>
                            <td>
                                {{sach.tiLeKhuyenMai}}
                            </td>
                            <td>
                                {{sach.ngayXuatBan}}
                            </td>
                            <td>
                                <a :href="'<spring:url value="/sach/update/" />' + sach.maSach">
                                    <button class="btn btn-sm btn-warning">
                                        Edit
                                    </button>
                                </a>
                                <button class="btn btn-sm btn-danger" @click="doDelete(sach)">
                                    Delete
                                </button>
                            </td>
                        </tr>
                        <%--</c:forEach>--%>
                        </tbody>
                    </table>
                </el-col>
            </el-row>
        </el-col>
    </div>
</section>

<%@ include file="../fragment/footer.jsp" %>
<script>
    var vm = new Vue({
        el: '#sachList',
        data() {
            return {
                lstSach: [],
                dialogVisible: false,
                txtSearch: '',
                currentPage: 1
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
            },
            currentChange(data) {
                this.currentPage = data
                console.log(this.currentPage)
            }
        },
        computed: {
            lstSearchPagging() {
                var currentItem = 5 * (this.currentPage - 1);
                return this.lstSearch.slice(currentItem, (currentItem + 5))
            },
            lstSearch() {
                return this.lstSach.filter(function (value) {
                    return value.tenSach
                        .includes(vm1._data.searchGlobal)
                })
            }
        },
        beforeCreate() {
            axios.get("/api/admin/getSach").then(function (response) {
                    this.vm._data.lstSach = response.data
                    // console.log(response.data)
                }
            ).catch(function (reason) {
                console.log('loi roi ' + reason)
            })
        }
    })
</script>