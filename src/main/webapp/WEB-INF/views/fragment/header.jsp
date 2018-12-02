<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 5/26/2018
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en
">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Title</title>
  <link rel="stylesheet" href="<spring:url value="/css/bootstrap.min.css"/>" type="text/css" />
  <link rel="stylesheet" href="<spring:url value="/css/font-awesome.css"/>" type="text/css" />
  <link rel="stylesheet" href="<spring:url value="/css/vue-element-ui.css"/>" type="text/css" />

  <style>
    section {
            background-color: white;
        }

        .myCard {
            cursor: pointer;
            padding: 5px;
            background-color: white;
            margin-top: 1rem;
            margin-bottom: 1rem;
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
            content: " đồng";
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

        .el-menu-vertical-demo:not(.el-menu--collapse) {
            width: 200px;
            min-height: 400px;
        }

        body {
            background-color: #f4f4f4;
            font-family: "Times New Roman", Times, serif;
            box-sizing: content-box;
            margin: 0;
            padding: 0;
        }

        #sachIndex,
        #detailBookX {
            background-image: url("/api/img/bookIMG.png");
            background-attachment: fixed;
            background-position: center;
            opacity: 1;
            /*max-width: 100vh;*/
            /*max-height: 100vh;*/
            background-repeat: no-repeat;
            /*font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;*/
            /*font-family: "Font Awesome\ 5 Brands";*/
        }

        .bar,
        .bar:hover {
            list-style: none;
        }

        .bar--itemBar {
            padding: 9px 30px 11px 30px;
            border-style: solid;
            border-color: white;
            border-width: 0 0 1.5px 0;
            color: #898989;
        }

        .bar--itemBar:hover,
        .bar--itemBar:hover a {
            text-decoration: none;
            border-color: #009cff;
            color: #009cff;
        }

        /*a,*/
        /*a:visited,*/
        /*a:link {*/
        /*text-decoration: none;*/
        /*color: #898989;*/
        /*}*/

        a:hover {
            text-decoration: none;
            /*font-weight: 300;*/
        }

        .section-body {
            margin-top: 2rem;
            padding-top: 1rem;
        }

        el-menu-item {
            text-transform: uppercase;
        }

        #logout {
            background-color: transparent;
        }
    </style>
</head>

<body>
  <section style="position: relative" id="header">
    <el-menu default-active=1 mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b"
      class="px-3">
      <spring:url value="/sach/index" var="index" />
      <spring:url value="/" var="context"></spring:url>
      <el-menu-item index="1" class="px-5 font-weight-bold"><a href="${context}">BOOK STORE</a></el-menu-item>
      <el-menu-item index="2" class="el-icon-goods">New</el-menu-item>
      <el-menu-item index="3">-Hot-</el-menu-item>
      <el-menu-item index="4">-The best sale-</el-menu-item>
      <el-menu-item index="5" class="el-icon-sort-down">Low</el-menu-item>
      <el-menu-item index="6" class="el-icon-sort-up">High</el-menu-item>
      <el-menu-item index="7" v-if="showSearch">
        <div class="form-inline my-2">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" ref="search" />
          <button class="btn btn-outline-success my-2 my-sm-0" @click="handleSearch">Search</button>
        </div>
      </el-menu-item>
      <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <el-menu-item index="10" class="float-right">
          <form:form action="${context}logout" method="post">
            <input type="submit" class="btn btn-link" id="logout" style="color: #fff;
                font-size: 14px; font-family: 'Times New Roman', 'Times', 'serif'; text-decoration: none;
                cursor: pointer;"
              value="Logout">
          </form:form>
        </el-menu-item>
        <el-menu-item index="9" class="el-icon-setting float-right">Profile</el-menu-item>

      </security:authorize>
      <security:authorize access="!hasAnyRole('USER', 'ADMIN')">
        <el-menu-item index="9" class="float-right">
          <a href="${context}user/login">Login</a>
        </el-menu-item>
      </security:authorize>
      <el-menu-item index="8" class="el-icon-bell float-right" @click="showCard">
        Cart
        <el-badge :value="12" class="">
        </el-badge>
      </el-menu-item>
    </el-menu>

    <el-dialog title="Giỏ hàng" :visible.sync="dialogFormVisible">
      <el-table :data="storeData" style="width: 100%; min-height: 35vh">
        <el-table-column fixed prop="tenSach" label="Tên sách" width="300">
        </el-table-column>
        <el-table-column prop="soLuong" label="Số lượng" width="140">
          <template slot-scope="scope">
            <el-input-number
                    v-model="scope.row.soLuong"
                    @change="handleChange"
                    :min="0"
                    :max="100"
                    controls-position="right"
                    size="mini"
            />
          </template>
        </el-table-column>
        <el-table-column prop="gia" label="Đơn giá" width="120">
        </el-table-column>
        <el-table-column prop="khuyenMai" label="Khuyến mãi" width="120">
        </el-table-column>
        <el-table-column fixed="right" prop="tongTien" label="Tổng tiền" width="120">
        </el-table-column>
      </el-table>
      <div class="float-right">
        Tổng tiền: <span> {{total}}$ </span>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </section>