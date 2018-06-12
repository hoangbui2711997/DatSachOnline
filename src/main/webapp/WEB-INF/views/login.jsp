<%--&lt;%&ndash;<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>&ndash;%&gt;--%>
<%--&lt;%&ndash;<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>&ndash;%&gt;--%>
<%--&lt;%&ndash;<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>&ndash;%&gt;--%>
<%--&lt;%&ndash;<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>&ndash;%&gt;--%>
<%--&lt;%&ndash;--%>
<%--Created by IntelliJ IDEA.--%>
<%--User: dev-wu--%>
<%--Date: 10/06/2018--%>
<%--Time: 02:15--%>
<%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<!doctype html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--<meta charset="UTF-8">--%>
<%--<meta name="viewport"--%>
<%--content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">--%>
<%--<meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">--%>
<%--<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>--%>
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>--%>

<%--<title>Login</title>--%>
<%--</head>--%>
<%--<body>--%>
<%@ include file="fragment/header.jsp" %>
<section id="login">

    <spring:url var="context" value="/"/>


    <el-dialog title="Login form" :visible.sync="loginFormVisible">
        <form:form action="${context}processLogin" method="post">
            <c:if test="${param.error != null}">
                <el-alert
                        title="Sorry! You entered invalid username/password."
                        type="error"
                        show-icon
                        style="top: -20px"
                >
                </el-alert>
            </c:if>
            <div class="form-group row">
                <label for="username" class="col-2 col-form-label">User name</label>
                <div class="col-10">
                    <input class="form-control" type="text" value="" id="username"
                           name="username">
                </div>
            </div>
            <div class="form-group row">
                <label for="password" class="col-2 col-form-label">Password</label>
                <div class="col-10">
                    <input class="form-control" type="password" value="" id="password"
                           name="password">
                </div>
            </div>
            <%--<input type="submit" value="Submit" class="btn btn-primary">--%>

        <span slot="footer" class="dialog-footer">
            <div class="text-right">
                    <el-button @click="loginFormVisible = false">Cancel</el-button>
                    <el-button native-type="submit" type="primary">Confirm</el-button>
            </div>
        </span>
        </form:form>
    </el-dialog>
</section>
<%@ include file="fragment/footer.jsp" %>
<%--</body>--%>
<%--</html>--%>
<script>
    new Vue({
        el: '#login',
        data() {
            return {
                loginFormVisible: true
            }
        }
    })
</script>


