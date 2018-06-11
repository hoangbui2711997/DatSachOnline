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
<!doctype html>
<html lang="en
">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Title</title>
    <link rel="stylesheet"
          href="<spring:url value="/css/bootstrap.min.css"/>"
          type="text/css"/>
    <%--<link rel="stylesheet"--%>
          <%--href="<spring:url value="/css/bootstrap-vue.css"/>"--%>
          <%--type="text/css"/>--%>
    <link rel="stylesheet"
          href="<spring:url value="/css/font-awesome.css"/>"
          type="text/css"/>
    <link rel="stylesheet"
          href="<spring:url value="/css/vue-element-ui.css"/>"
          type="text/css"/>

    <style>
        .el-menu-vertical-demo:not(.el-menu--collapse) {
            width: 200px;
            min-height: 400px;
        }

        body {
            font-family: "Times New Roman", Times, serif;
            box-sizing: content-box;
            margin:0;
            padding:0;
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
    </style>
</head>
<body>
<div id="global">
<section style="position: relative" id="header">
    <el-menu
            default-active=1
            <%--class="el-menu-demo"--%>
            mode="horizontal"
            <%--@select="handleSelect"--%>
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            class="px-3"

    >
        <spring:url value="/sach/index" var="index" />
        <spring:url value="/" var="index"></spring:url>
        <el-menu-item index="1" class="px-5 font-weight-bold"><a href="${index}">BOOK STORE</a></el-menu-item>
        <el-menu-item index="2">NEW</el-menu-item>
        <el-menu-item index="3">HOT</el-menu-item>
        <el-menu-item index="4">THE BEST SALE</el-menu-item>
        <el-menu-item index="5">LOW</el-menu-item>
        <el-menu-item index="6">HIGH</el-menu-item>
        <el-menu-item index="7">FILTER</el-menu-item>
        <el-menu-item index="8">
            <form class="form-inline my-2">
                <input class="form-control mr-sm-2" type="text" placeholder="Search" v-model="searchGlobal"
                       @change="handleSearch">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                <%--<el-button icon="el-icon-search" circle type="submit"></el-button>--%>
            </form>
        </el-menu-item>
        <el-menu-item index="9" class="ml-4">Profile</el-menu-item>
        <el-menu-item index="10">
            <form:form action="${index}logout" method="post">
                <input type="submit" class="btn" style="background-color: rgb(84, 92, 100);
                 color: #fff; font-family: 'Times New Roman', 'Times', 'serif'; text-decoration: none;
                 cursor: pointer;
" value="Logout"></input>
            </form:form>
        </el-menu-item>

    <%--<el-submenu index="2">--%>
            <%--<template slot="title">Workspace</template>--%>
            <%--<el-menu-item index="2-1">item one</el-menu-item>--%>
            <%--<el-menu-item index="2-2">item two</el-menu-item>--%>
            <%--<el-menu-item index="2-3">item three</el-menu-item>--%>
            <%--<el-submenu index="2-4">--%>
                <%--<template slot="title">item four</template>--%>
                <%--<el-menu-item index="2-4-1">item one</el-menu-item>--%>
                <%--<el-menu-item index="2-4-2">item two</el-menu-item>--%>
                <%--<el-menu-item index="2-4-3">item three</el-menu-item>--%>
            <%--</el-submenu>--%>
        <%--</el-submenu>--%>
        <%--<el-menu-item index="3" disabled>Info</el-menu-item>--%>
        <%--<el-menu-item index="4"><a href="https://www.ele.me" target="_blank">Orders</a></el-menu-item>--%>
    </el-menu>

    <%--<nav class="navbar navbar-toggleable-md navbar-light bg-faded mt-5" style="max-height: 25px">--%>
        <%--<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"--%>
                <%--data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"--%>
                <%--aria-label="Toggle navigation">--%>
            <%--<span class="navbar-toggler-icon"></span>--%>
        <%--</button>--%>
        <%--<a class="navbar-brand p-2" href="#">Sach Store</a>--%>

        <%--<div class="collapse navbar-collapse" id="navbarNavDropdown">--%>
            <%--<ul class="navbar-nav bar text-center">--%>
                <%--<li class="nav-item bar--itemBar" data-order="newest">--%>
                    <%--<a href="javascript:void(0);">HÀNG MỚI</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item bar--itemBar" data-order="top_seller">--%>
                    <%--<a href="javascript:void(0);">BÁN CHẠY</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item bar--itemBar" data-order="discount_percent,desc">--%>
                    <%--<a href="javascript:void(0);">GIẢM GIÁ NHIỀU</a>--%>
                <%--</li>--%>

                <%--<li class="nav-item bar--itemBar" data-order="price,asc">--%>
                    <%--<a href="javascript:void(0);">GIÁ THẤP</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item bar--itemBar" data-order="price,desc">--%>
                    <%--<a href="javascript:void(0);">GIÁ CAO</a>--%>
                <%--</li>--%>

                <%--<li class="nav-item bar--itemBar" data-order="position">--%>
                    <%--<a href="javascript:void(0);">CHỌN LỌC</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<form class="form-inline">--%>
                        <%--<input class="form-control mr-sm-2" type="text" placeholder="Search" v-model="searchGlobal"--%>
                               <%--@change="handleSearch">--%>
                        <%--<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
                    <%--</form>--%>
                <%--</li>--%>
            <%--</ul>--%>
            <%--&lt;%&ndash;<div id="" class="col-xl-3 col-lg-4 col-md-6 col-sm-8 col-10 p-2">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<a href="javascript:void(0);">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input class="search-box" type="text" name="q" placeholder="Tìm trong Sách tiếng Việt "&ndash;%&gt;--%>
            <%--&lt;%&ndash;value="">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<button class="btn btn-primary">Tìm kiếm</button>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--</div>--%>
    <%--</nav>--%>
</section>

<%--<div id="" class="p-2 w-100 container h-100" style="background-color: whitesmoke">--%>
<%--<div class="float-right h-100">--%>
<%--<input class="search-box" type="text" name="q" placeholder="Tìm trong Sách tiếng Việt "--%>
<%--value="">--%>
<%--<button class="btn btn-primary">Tìm kiếm</button>--%>
<%--</div>--%>
<%--</div>--%>