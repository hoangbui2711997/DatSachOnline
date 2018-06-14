<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 5/26/2018
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="fragment/header.jsp"%>

<section id="test">
    <%--&lt;%&ndash;<form method="post" enctype="multipart/form-data" @submit.prevent="postImage">&ndash;%&gt;--%>
        <%--<input ref="file" class="form-control form-control-file" @change="postImage" type="file" multiple/>--%>
        <%--&lt;%&ndash;<button type="submit">&ndash;%&gt;--%>
            <%--&lt;%&ndash;submit&ndash;%&gt;--%>
        <%--&lt;%&ndash;</button>&ndash;%&gt;--%>
    <%--</form>--%>
</section>

<%@ include file="fragment/footer.jsp"%>

<%--<script>--%>
    <%--new Vue({--%>
        <%--el: "#test",--%>
        <%--methods: {--%>
            <%--postImage() {--%>
                <%--var formData = new FormData();--%>
                <%--console.log(this.$refs.file.files[0])--%>

                <%--formData.append('file', this.$refs.file.files[0])--%>

                <%--console.log(formData.getAll('file'))--%>

                <%--// var config = {--%>
                <%--//     headers: {--%>
                <%--//         'Content-Type': 'multipart/form-data;boundary=gc0p4Jq0M2Yt08jU534c0p'--%>
                <%--//     },--%>
                <%--// }--%>

                <%--axios.post('<spring:url value="/sach/postImages" />', formData)--%>
                    <%--.then(function (value) {--%>
                        <%--console.log("Thanh cong")--%>
                    <%--})--%>
                    <%--.catch(function (reason) {--%>
                        <%--console.log("That bai")--%>
                    <%--})--%>

                <%--&lt;%&ndash;axios({&ndash;%&gt;--%>
                    <%--&lt;%&ndash;method: 'post',&ndash;%&gt;--%>
                    <%--&lt;%&ndash;url: '<spring:url value="/sach/postImages" />',&ndash;%&gt;--%>
                    <%--&lt;%&ndash;data: {&ndash;%&gt;--%>
                        <%--&lt;%&ndash;filePost: formData&ndash;%&gt;--%>
                    <%--&lt;%&ndash;},&ndash;%&gt;--%>
                    <%--&lt;%&ndash;headers: {&ndash;%&gt;--%>
                        <%--&lt;%&ndash;'Content-Type': 'multipart/form-data'&ndash;%&gt;--%>
                    <%--&lt;%&ndash;}&ndash;%&gt;--%>
                <%--&lt;%&ndash;})&ndash;%&gt;--%>
                <%--&lt;%&ndash;.then(function (value) {&ndash;%&gt;--%>
                        <%--&lt;%&ndash;console.log('Thanh cong')&ndash;%&gt;--%>
                    <%--&lt;%&ndash;})&ndash;%&gt;--%>
                <%--&lt;%&ndash;.catch(function (reason) {&ndash;%&gt;--%>
                        <%--&lt;%&ndash;console.log('Loi')&ndash;%&gt;--%>
                    <%--&lt;%&ndash;})&ndash;%&gt;--%>
                <%--// formData.append('file', this.file)--%>
            <%--}--%>
        <%--}--%>
    <%--})--%>
<%--</script>--%>


