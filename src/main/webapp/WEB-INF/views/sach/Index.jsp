<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 5/28/2018
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../fragment/header.jsp" %>

<style>
    body {
        background-color: #f4f4f4;
    }

    section {
        background-color: white;
    }

    .myCard {

        cursor: pointer;
        padding: 5px;
    }

    .myCard--title {
        font-size: medium;
        font-weight: 300;

        text-overflow: ellipsis;
        overflow: hidden;
    }

    .myCard--author {
        font-size: small;
        color: darkgray;
        font-weight: bold;
    }

    .myCard--price {
        font-weight: bold;
        font-size: small;
    }

    .myCard--price::after {
        content: "$";
        text-decoration: underline;
    }

    .myCard--priceOld {
        text-decoration: line-through;
    }

    .myCard--sale {
        color: red;
    }

    .myCard--sale:before {
        content: '-';
    }

    .myCard--sale:after {
        content: '%';
    }

    .myCard:hover {
        box-shadow: 3px 5px 20px lightgray;
    }

    .myCard-body {
        border-style: solid;
        border-width: 0px;
        border-top-width: 10px;
        border-top: lightgray;
        font-style: normal;
        font-stretch: normal;
        color: #4b5257;
    }
</style>

<spring:url value="/img/tuoi-tre-dang-gia-bao-nhieu-u547-d20161012-t113832-888179.u3059.d20170616.t095744.390222.jpg"
            var="test"></spring:url>

<spring:url value="/sach/update" var="update"></spring:url>

<section class="section-body container-fluid">
    <div class="row">
        <div class="col-0 col-md-2"></div>
        <div class="col-12 col-md-9">
            <div class="text-center row w-100">
                <c:forEach items="${lstSach}" var="item">
                    <div class="col-xl-3 col-lg-4 col-md-6 col-12" style="horiz-align: center">
                        <div class="myCard text-center">
                            <a href="${update}/${item.maSach}">
                                <img class="img-thumbnail" src="${item.biaSach}" alt="Card image cap"
                                     style="min-height:210px; height: 210px; border-style: none">
                            </a>
                            <div class="myCard-body ml-3 mt-3">
                                <p class="myCard--title">${item.tenSach}</p>
                                <p class="myCard--author">
                                    <c:forEach items="${item.lstTacGia}" var="tacGia">
                                        ${tacGia.tenTacGia}
                                    </c:forEach>
                                </p>
                                <p>
                                        <span class="myCard--price">${item.giaSachBan
                                                - (item.giaSachBan * item.tiLeKhuyenMai / 100)}
                                        </span>
                                    <span>
                                    <small class="text-muted pl-2 myCard--priceOld">${item.giaSachBan}$</small>
                                    <span class="myCard--sale pl-2">${item.tiLeKhuyenMai}</span>
                                </span>
                                </p>
                                    <%--<p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>--%>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
        <div class="col-0 col-md-1"></div>
    </div>
</section>

<%@ include file="../fragment/footer.jsp" %>
