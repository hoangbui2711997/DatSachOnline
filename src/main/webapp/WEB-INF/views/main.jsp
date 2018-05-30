<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 5/26/2018
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="fragment/header.jsp"%>

<section id="test">
    <%--<form method="post" enctype="multipart/form-data" @submit.prevent="postImage">--%>
        <input ref="file" class="form-control form-control-file" @change="postImage" type="file" multiple/>
        <%--<button type="submit">--%>
            <%--submit--%>
        <%--</button>--%>
    </form>
</section>

<%@ include file="fragment/footer.jsp"%>

<script>
    new Vue({
        el: "#test",
        methods: {
            postImage() {
                var formData = new FormData();
                console.log(this.$refs.file.files[0])

                formData.append('file', this.$refs.file.files[0])

                console.log(formData.getAll('file'))

                // var config = {
                //     headers: {
                //         'Content-Type': 'multipart/form-data;boundary=gc0p4Jq0M2Yt08jU534c0p'
                //     },
                // }

                axios.post('<spring:url value="/sach/postAnh" />', formData)
                    .then(function (value) {
                        console.log("Thanh cong")
                    })
                    .catch(function (reason) {
                        console.log("That bai")
                    })

                <%--axios({--%>
                    <%--method: 'post',--%>
                    <%--url: '<spring:url value="/sach/postAnh" />',--%>
                    <%--data: {--%>
                        <%--filePost: formData--%>
                    <%--},--%>
                    <%--headers: {--%>
                        <%--'Content-Type': 'multipart/form-data'--%>
                    <%--}--%>
                <%--})--%>
                <%--.then(function (value) {--%>
                        <%--console.log('Thanh cong')--%>
                    <%--})--%>
                <%--.catch(function (reason) {--%>
                        <%--console.log('Loi')--%>
                    <%--})--%>
                // formData.append('file', this.file)
            }
        }
    })
</script>


