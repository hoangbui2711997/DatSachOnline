<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 5/28/2018
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../fragment/header.jsp" %>

<style>

</style>


<%--<spring:url value="/img/tuoi-tre-dang-gia-bao-nhieu-u547-d20161012-t113832-888179.u3059.d20170616.t095744.390222.jpg"--%>
<%--var="test"></spring:url>--%>

<spring:url value="/sach/detail" var="detail"></spring:url>

<%--<section class="section-body container-fluid">--%>
<%--<div class="row">--%>
<%--<div class="col-0 col-md-2"></div>--%>
<%--<div class="col-12 col-md-9">--%>
<%--<div class="text-center row w-100">--%>
<%--<c:forEach items="${lstSach}" var="item">--%>
<%--<div class="col-xl-3 col-lg-4 col-md-6 col-12" style="horiz-align: center">--%>
<%--<div class="myCard text-center">--%>
<%--<a href="${update}/${item.maSach}">--%>
<%--<img class="img-thumbnail" src="${item.biaSach}" alt="Card image cap"--%>
<%--style="min-height:210px; height: 210px; border-style: none">--%>
<%--</a>--%>
<%--<div class="myCard-body ml-3 mt-3">--%>
<%--<p class="myCard--title">${item.tenSach}</p>--%>
<%--<p class="myCard--author">--%>
<%--<c:forEach items="${item.lstTacGia}" var="tacGia">--%>
<%--${tacGia.tenTacGia}--%>
<%--</c:forEach>--%>
<%--</p>--%>
<%--<p>--%>
<%--<span class="myCard--price">${item.giaSachBan--%>
<%--- (item.giaSachBan * item.tiLeKhuyenMai / 100)}--%>
<%--</span>--%>
<%--<span>--%>
<%--<small class="text-muted pl-2 myCard--priceOld">${item.giaSachBan}$</small>--%>
<%--<span class="myCard--sale pl-2">${item.tiLeKhuyenMai}</span>--%>
<%--</span>--%>
<%--</p>--%>
<%--&lt;%&ndash;<p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>&ndash;%&gt;--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</c:forEach>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-0 col-md-1"></div>--%>
<%--</div>--%>
<%--</section>--%>

<section class="section-body container-fluid" id="sachIndex">
    <div class="row">
        <div class="col-0 col-md-2"></div>
        <div class="col-12 col-md-9">
            <div class="text-center row w-100">
                <%--<c:forEach items="${lstSach}" var="item">--%>
                <div v-for="sach in lstSearch" class="col-xl-3 col-lg-4 col-md-6 col-12" style="horiz-align: center">
                    <div class="myCard text-center">
                        <a :href="'${detail}' + '/' + sach.maSach">
                            <%--<a href="${update}/${item.maSach}">--%>
                            <img class="img-thumbnail" :src="sach.biaSach" alt="Card image cap"
                                 style="max-height: 210px; min-height:210px; height: 210px; border-style: none;
                                 min-width: 210px; max-width: 210px;
">
                        </a>
                        <%--</a>--%>
                        <div class="myCard-body ml-3 mt-3">
                            <%--<p class="myCard--title" v-if="sach.tenSach.length > 25">{{sach.tenSach.slice(0, 25) + '...'}}</p>--%>
                            <%--<p class="myCard--title" v-else>{{sach.tenSach}}</p>--%>
                            <p class="myCard--title">{{sach.tenSach}}</p>
                            <p class="myCard--author">
                                    <span v-for="(tacGia, index) in sach.lstTacGia">

                                        <span v-if="index != sach.lstTacGia.length - 1">
                                            {{ tacGia.tenTacGia }},
                                        </span>
                                        <span v-else>
                                            {{ tacGia.tenTacGia }}
                                        </span>
                                    </span>
                                <%--<c:forEach items="${item.lstTacGia}" var="tacGia">--%>
                                <%--${tacGia.tenTacGia}--%>
                                <%--</c:forEach>--%>
                            </p>
                            <p>
                                        <span class="myCard--price">{{sach.giaSachBan
                                                - (sach.giaSachBan * sach.tiLeKhuyenMai / 100)}}
                                        </span>
                                <span>
                                    <small class="text-muted pl-2 myCard--price myCard--priceOld">{{sach.giaSachBan}}</small>
                                    <span class="myCard--sale pl-2">{{sach.tiLeKhuyenMai}}</span>
                                </span>
                            </p>
                            <%--<p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>--%>
                        </div>
                    </div>
                </div>
                <%--</c:forEach>--%>
            </div>
        </div>
        <div class="col-0 col-md-1"></div>
    </div>
</section>

<%@ include file="../fragment/footer.jsp" %>

<script>
    var vmTemp = new Vue({
        el: "#sachIndex",
        data() {
            return {
                lstSach: [],
            }
        },
        methods: {},
        computed: {
            lstSearch() {
                return this.lstSach.filter(function (value) {
                    return value.tenSach
                        .includes(vm1._data.searchGlobal)
                })
            }
        },
        beforeCreate() {
            axios.get("${context}sach/getSach")
                .then(function (value) {
                    this.vmTemp._data.lstSach = value.data
                    console.log(value.data)
                })
                .catch(function (reason) {
                    console.log('exception: ' + reason)
                })
        }
    })
</script>