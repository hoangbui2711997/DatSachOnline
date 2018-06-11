<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 5/27/2018
  Time: 4:03 PM
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

<section class="section-body container" id="createSach">
    <spring:url value="/sach/create" var="urlPostCreate"/>
    <spring:url value="/img/" var="context" />
    <form:form modelAttribute="sach" enctype="multipart/form-data" action="${urlPostCreate}" method="post">
        <input type="text" name="lstTacgia" v-model="strTacGia" hidden>
        <div class="form-group">
            <label class="label"><h4>Company release</h4></label>
            <form:input path="congTyPhatHanh" cssClass="form-control"/>
        </div>

        <%--<div class="form-group">--%>
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

        <div class="form-group">
            <label class="label"><h4>In Price</h4></label>
            <form:input path="giaSachNhap" type="number" id="" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label class="label"><h4>Out Price</h4></label>
            <form:input path="giaSachBan" type="number" id="" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label class="label"><h4>Descript about the book</h4></label>
            <form:textarea rows="20" path="gioiThieu" id="" cssClass="form-control"/>
        </div>
        <div class="form-group">
            <label class="label"><h4>Dimension</h4></label>
            <form:input path="kickThuoc" id="" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label class="label"><h4>Sale</h4></label>
            <form:input path="tiLeKhuyenMai" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label class="label"><h4>Name Of the book</h4></label>
            <form:input path="tenSach" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label class="label"><h4>Number of pages</h4></label>
            <form:input path="soTrang" cssClass="form-control"/>
        </div>

        <%--<div class="form-group">--%>
            <%--<label class="label"></label>--%>
            <%--<form:input path="sachNhapBan" type="file" name="file" id="" cssClass="form-control form-control-file"/>--%>
        <%--</div>--%>

        <div class="form-group">
            <label class="label"><h4>Publishing company</h4></label>
            <form:input path="nhaXuatBan" id="" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label class="label"><h4>Day released</h4></label>
            <form:input path="ngayXuatBan" type="date" id="" cssClass="form-control form-control-file"/>
        </div>

        <form:input path="hinhAnhTieuBieu" v-model="lstHinhAnhTieuBieu" hidden="true"/>
        <form:input path="biaSach" v-model="biaSach" hidden="true"/>

        <div class="form-group">
            <label class="label"><h4>Images descripted</h4></label>

        </div>

        <div class="form-group">
            <label class="label"><h4>Images descripted</h4></label>
            <input type="file" name="hinhAnhTieuBieuX" ref="files" class="form-control form-control-file" multiple
                   @change="handleFiles"
            />
            <div class="row">
            <div v-for="hinhAnh in hinhAnhTieuBieu" class="col-lg-2 col-md-3 col-sm-4 col-6"
                 @click="deleteImage(hinhAnh)"
            >

                <img class="img-thumbnail" :src="hinhAnh" alt="Card image cap"
                     style="max-width: 150px; max-height: 150px;
                             min-height: 150px; min-width: 150px;
">
            </div>
            </div>
        </div>

        <div class="form-group">
            <label class="label"><h4>Image label of the book</h4></label>
            <%--<form:input path="biaSach" type="file" cssClass="form-control form-control-file" onchange="handlePostFile"></form:input>--%>
            <input type="file" name="biaSachX" class="form-control form-control-file" ref="file"
                   @change="handleFile"/>
            <img class="img-thumbnail" :src="biaSach" alt="Card image cap" v-if="biaSach !== ''"
                 style="max-width: 150px; max-height: 150px;
                 min-height: 150px; min-width: 150px;">
        </div>

        <button type="submit" class="btn btn-block btn-success">Create the book</button>
    </form:form>
</section>

<%@ include file="../fragment/footer.jsp" %>
<script>
    var vueData = new Vue({
        el: '#createSach',
        data() {
            return {
                lstAllTacGia: [],
                lstTextTacGia: [],
                txtSearch: '',
                strTacGia: '',
                filesList: [],
                hinhAnhTieuBieu: [],
                lstHinhAnhTieuBieu: '',
                biaSach: ''
            }
        },
        methods: {
            addTacGia(tenTacGia) {
                if (this.txtSearch === '' || this.lstTextTacGia.includes(tenTacGia)) {

                } else {
                    // this.txtTacGia = tenTacGia;
                    this.lstTextTacGia.push(tenTacGia)

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
            },
            handleFiles() {
                var formData = new FormData();
                var files = this.$refs.files.files;
                // Danh dau xem anh nao moi khong
                var track = 0

                // console.log(file)
                // console.log(formData.getAll('file'))
                if (typeof this.hinhAnhTieuBieu === 'undefined') {
                    this.hinhAnhTieuBieu = []
                }

                for (var i = 0; i < files.length; i++) {
                    if (!this.hinhAnhTieuBieu.includes('${context}' + files[i].name)) {
                        formData.append('file', files[i])
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
            handleFile() {
                var formData = new FormData()
                var file = this.$refs.file.files[0];
                formData.append('file', file)

                axios.post("<spring:url value="/sach/postAnh" />", formData)
                    .then(function (value) {
                        console.log('Thanh cong')
                        // clearInterval(x)
                        <%--vueData._data.hinhAnhTieuBieu.push('${context}' + file.name)--%>
                        vueData._data.biaSach =  '${context}' + file.name
                    })
                    .catch(function (reason) {
                        console.log('Loi')
                    })
            },
            deleteImage(hinhAnh) {
                var indexDel = this.hinhAnhTieuBieu.indexOf(hinhAnh)
                this.hinhAnhTieuBieu.splice(indexDel, 1)
                this.lstHinhAnhTieuBieu = this.hinhAnhTieuBieu.toString()
            }
        },
        computed: {
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
        beforeCreate() {
            axios.get('<spring:url value="/tacgia/getall" />', {})
                .then(function (response) {
                    vueData._data.lstAllTacGia = response.data

                    console.log(response.data)
                })
                .catch(function (error) {
                    alert("Không lấy được dữ liệu")
                });
        }
    })

</script>