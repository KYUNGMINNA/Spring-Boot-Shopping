<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<style>

    .container {
        height: auto;
        min-height: 55%;
        padding-bottom: 180px;;
    }


</style>
<%@ include file="layout/header.jsp" %>
<div class="container" style="text-align: center;">
    <h1></h1>
 <ul style="margin:0; padding: 0; width: 100%; line-height: 300px;">
    <li style="width: 80%; margin-bottom: 3rem; ">

        <form action="/product/productorder" method="post"  id="orderform">

            <div style="">
                <span>주문 번호를 입력하세요.</span>
                <input type="text" name="productOrderNumber" id="productOrderNumber">
                <button type="button" id="productOrderNumberBtn">전송</button>
            </div>


        </form>
        </li>
     <br/>



    </ul>
    <br/>


</div>
<h1></h1>

<%@ include file="layout/footer.jsp" %>
<script>

    $(function() {

        $('#productOrderNumberBtn').click(function () {
            var productOrderNumber = $('#productOrderNumber').val().trim();


            $.ajax({
                type:"GET",
                url:"/api/productorder/number/"+productOrderNumber,
                contentType:"application/json;charset=utf-8",
                dataType:"json"

            }).done(function (resp){
                if(resp.data !==null){
                document.getElementById('orderform').submit();

                }else{
                    alert('주문 번호를 확인해 주세요.');
                }

            }).fail(function (error){
                alert(JSON.stringify(error));
            });



        });
    });

</script>