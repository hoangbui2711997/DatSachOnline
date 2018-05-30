<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 5/28/2018
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../fragment/header.jsp" %>
<style>
    #input-inside {
        min-height: 2rem !important;
        margin-top: 3px;
    }

    span {
        cursor: pointer;
    }

    input[class^="btn"] {
        cursor: pointer;
    }
</style>
<section class="container" id="updateSach">
    <spring:url value="/sach/updatePost" var="urlUpdatePost"/>
    <spring:url value="/sach/postAnh" var="postAnh"/>
    <spring:url value="/img/" var="context"></spring:url>

    <form:form modelAttribute="sach" enctype="multipart/form-data" action="${urlUpdatePost}" method="post">
        <input type="text" name="lstTacgia" v-model="strTacGia" hidden>
        <form:input hidden="true" path="maSach"/>
        <div class="form-group py-1">
            <label class="label"><h4>Company release</h4></label>
            <form:input path="congTyPhatHanh" cssClass="form-control"/>
        </div>

        <%--<div class="form-group py-1">--%>
        <%--<label class="label"></label>--%>
        <%--<form:checkboxes path="lstAllTacGia" id="" class="form-control" items="${lstAllTacGia}" />--%>
        <%--</div>--%>

        <div class="form-group py-1">
            <label class="label"><h4>Authors</h4></label>
                <%--<form:input path="lstAllTacGia" id="" class="form-control" onfocus=""/>--%>
            <div class="form-control p-0" style="display: inline-block;">

                <ul style="min-height: 38px; list-style: none" class="mx-0 my-0 mt-1 pl-2">
                    <li v-for="tacGia in lstTextTacGia" class="alert alert-info p-1 mr-1"
                        style="border-radius: 10px; display: inline-block;">
                        {{ tacGia }} <span class="pl-2" @click="deleteTacGia(tacGia)">&times;</span>
                    </li>
                    <li><input type="text" style="display: inline-block;"
                               id="input-inside" v-model="txtSearch" class="form-control"
                    /></li>
                </ul>
            </div>
                <%--<select class="form-control">--%>
                <%--<option></option>--%>
                <%--</select>--%>
        </div>
        <div class="form-group py-1">
            <ul class="list-group">
                <li v-for="tacGia in lstSearch" class="list-group-item w-75" @click="addTacGia(tacGia)">
                    {{ tacGia }}
                </li>
            </ul>
        </div>

        <div class="form-group py-1">
            <label class="label"><h4>In Price</h4></label>
            <form:input path="giaSachNhap" type="number" id="" cssClass="form-control"/>
        </div>

        <div class="form-group py-1">
            <label class="label"><h4>Out Price</h4></label>
            <form:input path="giaSachBan" type="number" id="" cssClass="form-control"/>
        </div>

        <div class="form-group py-1">
            <label class="label"><h4>Descript about the book</h4></label>
            <form:textarea rows="20" path="gioiThieu" id="" cssClass="form-control"/>
        </div>
        <div class="form-group py-1">
            <label class="label"><h4>Dimension</h4></label>
            <form:input path="kickThuoc" id="" cssClass="form-control"/>
        </div>

        <div class="form-group py-1">
            <label class="label"><h4>Sale</h4></label>
            <form:input path="tiLeKhuyenMai" cssClass="form-control"/>
        </div>

        <div class="form-group py-1">
            <label class="label"><h4>Name Of the book</h4></label>
            <form:input path="tenSach" cssClass="form-control"/>
        </div>

        <div class="form-group py-1">
            <label class="label"><h4>Number of pages</h4></label>
            <form:input path="soTrang" cssClass="form-control"/>
        </div>

        <%--<div class="form-group py-1">--%>
        <%--<label class="label"></label>--%>
        <%--<form:input path="sachNhapBan" type="file" name="file" id="" cssClass="form-control form-control-file"/>--%>
        <%--</div>--%>

        <div class="form-group py-1">
            <label class="label"><h4>Publishing company</h4></label>
            <form:input path="nhaXuatBan" id="" cssClass="form-control"/>
        </div>

        <div class="form-group py-1">
            <label class="label"><h4>Day released</h4></label>
            <form:input path="ngayXuatBan" type="date" id="" cssClass="form-control form-control-file"/>
        </div>

        <div class="form-group py-1">
            <label class="label"><h4>Images descripted</h4></label>
            <div class="form-control">
                    <%--<input type="file" name="hinhAnhTieuBieuX" class="form-control form-control-file" multiple/>--%>
                    <%--<form method="post" @submit="" enctype="multipart/form-data" action="postAnh">--%>
                <input ref="file" class="form-control form-control-file" type="file" @change="postImage" multiple/>
                <div class="row">
                        <%--<c:forEach var="imageTieuBieu" items="${sach.hinhAnhTieuBieu.split(',')}">--%>
                        <%--<div class="col-lg-2 col-md-3 col-sm-4 col-6">--%>
                        <%--<img class="img-fluid" src="${imageTieuBieu}" alt="Card image cap"--%>
                        <%--style="max-width: 150px;">--%>
                        <%--</div>--%>
                        <%--</c:forEach>--%>
                    <form:input path="hinhAnhTieuBieu" v-model="lstHinhAnhTieuBieu" hidden="true"/>
                    <div v-for="hinhAnh in hinhAnhTieuBieu" class="col-lg-2 col-md-3 col-sm-4 col-6">

                        <img class="img-thumbnail" :src="hinhAnh" alt="Card image cap"
                             style="max-width: 150px; max-height: 150px;
                             min-height: 150px; min-width: 150px;
">
                    </div>
                </div>

                    <%--<button type="submit" class="btn btn-outline-success my-2">Push on server</button>--%>
                    <%--</form>--%>
            </div>
        </div>


        <div class="form-group py-1">
            <label class="label"><h4>Image label of the book</h4></label>
            <form:input path="biaSach" hidden="true" v-model="biaSach"></form:input>
            <input type="file" name="biaSachX" class="form-control form-control-file" ref="anhBia"
                   @change="postAnhBia"/>
            <img class="img-thumbnail" :src="biaSach" alt="Card image cap" v-if="biaSach !== ''"
                 style="max-width: 150px; max-height: 150px;
                 min-height: 150px; min-width: 150px;">
        </div>

        <button type="submit" class="btn btn-block btn-success">Update the book</button>
    </form:form>
</section>
<%@ include file="../fragment/footer.jsp" %>

<script>

    var vueData = new Vue({
        el: '#updateSach',
        data() {
            return {
                lstAllTacGia: [],
                lstTextTacGia: [],
                txtSearch: '',
                strTacGia: '',
                file: '',
                hinhAnhTieuBieu: '',
                lstHinhAnhTieuBieu: '',
                biaSach: '${sach.biaSach == null ? "" : sach.biaSach}'
            }
        },
        methods: {
            addTacGia(tenTacGia) {
                // // Khởi tạo thì chạy vào đây
                // if (typeof this.lstTextTacGia === "undefined" && typeof  this.lstAllTacGia === "undefined") {
                //     this.lstTextTacGia = vueData.lstTextTacGia
                //     this.lstAllTacGia = vueData.lstAllTacGia
                // }

                if (this.txtSearch === '' || this.lstTextTacGia.includes(tenTacGia)) {

                } else {
                    // this.txtTacGia = tenTacGia;
                    this.lstTextTacGia.push(tenTacGia)

                    console.log(this.lstAllTacGia)
                    var indexDel = this.lstAllTacGia.indexOf(tenTacGia)
                    this.lstAllTacGia.splice(indexDel, 1)

                    this.txtSearch = ''

                    this.strTacGia = this.lstTextTacGia.toString()
                }
            },
            deleteTacGia(tenTacGia) {
                var indexDel = this.lstTextTacGia.indexOf(tenTacGia)
                this.lstTextTacGia.splice(indexDel, 1)

                this.lstAllTacGia.push(tenTacGia)

                this.strTacGia = this.lstTextTacGia.toString()
            },
            getTacGia() {
                for (var i = 0; i < this.lstTextTacGia.length; i++) {
                    console.log(this.lstTextTacGia[i])
                    this.addTacGia(this.lstTextTacGia[i])
                }
            },
            postImage() {
                var formData = new FormData();
                var file = this.$refs.file.files;
                // Danh dau xem anh nao moi khong
                var track = 0

                // console.log(file)
                // console.log(formData.getAll('file'))
                if (typeof this.hinhAnhTieuBieu === 'undefined') {
                    this.hinhAnhTieuBieu = []
                }

                for (var i = 0; i < file.length; i++) {
                    if (!this.hinhAnhTieuBieu.includes('${context}' + file[i].name)) {
                        formData.append('file', file[i])
                        track = 1
                    }
                }

                // timeout 5s
                // var x = setInterval(
                //     function () {
                //         for (var i = 0; i < formData.getAll('file').length; i++) {
                //             console.log(formData.getAll('file')[i].name)
                //             this.addHinhAnh(formData.getAll('file')[i].name)
                //         }
                //     }, 500
                // )

                if (track == 1) {
                    axios.post("<spring:url value="/sach/postAnh" />", formData)
                        .then(function (value) {
                            console.log('Thanh cong')
                            // clearInterval(x)
                            for (var i = 0; i < formData.getAll('file').length; i++) {
                                vueData._data.hinhAnhTieuBieu.push('${context}' + formData.getAll('file')[i].name)
                                vueData._data.lstHinhAnhTieuBieu = vueData._data.hinhAnhTieuBieu.toString()
                            }

                        })
                        .catch(function (reason) {
                            console.log('Loi')
                        })
                    // formData.append('file', this.file)


                }
            },
            addHinhAnh(tenFile) {
                if (tenFile !== '') {
                    this.hinhAnhTieuBieu.push('${context}' + tenFile)
                    this.lstHinhAnhTieuBieu = this.hinhAnhTieuBieu.toString()
                }
            },
            postAnhBia() {
                var formData = new FormData()
                var file = this.$refs.anhBia.files[0];
                formData.append('file', file)

                console.log(this.biaSach)

                axios.post("<spring:url value="/sach/postAnh" />", formData)
                    .then(function (value) {
                        console.log('Thanh cong')
                        vueData._data.biaSach = '${context}' + file.name
                    })
                    .catch(function (reason) {
                        console.log('Loi')
                    })
            }
        },
        computed: {
            // lstHinhAnhTieuBieu() {
            //     return this.hinhAnhTieuBieu.split(',')
            // },
            lstSearch() {
                if (this.txtSearch !== '') {
                    if (this.txtSearch.includes(',')) {
                        var newTacGia = this.txtSearch.substr(0, this.txtSearch.length - 1).trim()
                        this.addTacGia(newTacGia)

                        this.txtSearch = '';
                        return;
                    }
                    // L?y chu?i search
                    // var lastIndexOf = this.txtTacGia.lastIndexOf(',');
                    // var strSearch = this.txtTacGia.subString(lastIndexOf).trim();
                    var x = []
                    // // Search in original string
                    var arrays = this.lstAllTacGia;
                    for (i in arrays) {
                        if (arrays[i] != null) {
                            if (arrays[i].toLowerCase().includes(this.txtSearch.toLowerCase())) {
                                console.log(arrays[i])
                                x.push(arrays[i])
                            }
                        }
                    }

                    // return this.lstAllTacGia.filter(function(e) {
                    //     console.log(e)
                    //     if(e != null) {
                    //         e.includes(this.txtSearch)
                    //     }
                    // })

                    // this.lstAllTacGia.forEach(function(e) {
                    //     if(e.includes(this.txtSearch)) {
                    //         x.push(e)
                    //     }
                    // })

                    return x
                } else {
                    return []
                }
            },
        },
        created() {

        },
        beforeMount() {

            axios.get('<spring:url value="/tacgia/getall" />', {})
                .then(function (response) {
                    vueData._data.lstAllTacGia = response.data
                    // Lay du lieu thanh cong
                })
                .catch(function (error) {
                    alert("Không lấy được dữ liệu")
                });

            axios.get('<spring:url value="/sach/getTacGia/${sach.maSach}" />', {})
                .then(function (response) {
                    // Truyền dữ liệu cho vue vueData
                    vueData._data.lstTextTacGia = response.data
                    for (var i = 0; i < vueData._data.lstTextTacGia.length; i++) {
                        vueData._data.lstAllTacGia.splice(vueData._data.lstAllTacGia.indexOf(vueData._data.lstTextTacGia[i]), 1)
                    }
                    vueData._data.strTacGia = vueData._data.lstTextTacGia.toString()
                })
                .catch(function (error) {
                    console.log(error)
                    alert("Error")
                });

            this.getTacGia()

            axios.get('<spring:url value="/sach/hinhAnhTieuBieu/${sach.maSach}" />', {})
                .then(function (response) {
                    // Truyền dữ liệu cho vue vueData

                    vueData._data.hinhAnhTieuBieu = response.data === "" ? [] : response.data.split(',')
                })
                .catch(function (error) {
                    console.log(error)
                    alert("Error")
                });
        }
    })
</script>

