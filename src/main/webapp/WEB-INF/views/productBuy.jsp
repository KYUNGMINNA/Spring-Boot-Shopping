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

    <ul style="margin:0; padding: 0; width: 100%;">
        <li style="width: 80%; margin-bottom: 3rem">
            <div style="text-align: left; width: 20%; height: 25%; float: left;">
                <!-- {oneProduct.productImage}-->
                    <img src="/api/product/productImage=${oneProduct.data.productImage}" alt="" width="100%;" height=100%;">

            </div>


            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
                <span style="font-weight: bolder; font-size: 2rem;"> ${oneProduct.data.productTitle}</span>
            </div>

            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
                <span style="font-weight: bolder; font-size: 2rem;">가격 :<fmt:formatNumber pattern="###,###,###" value="${oneProduct.data.productPrice}" />원</span>

            </div>
            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
            <span style="font-weight: bolder; font-size: 2rem;">선택 수량 : <%= Integer.valueOf(request.getParameter("count"))%></span>
            </div>

            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">  <%--<%= Integer.valueOf(request.getParameter("count"))%> --%>

                <span style="font-weight: bolder; font-size: 2rem;" id="orderPrice">

                </span>
            </div>


        </li>
            <br/>
        <li style="width: 80%;">


            <table border="1" width="80%" style="text-align: center; margin-bottom: 3rem">
                <tbody>
                <tr>
                    <td><span>주문자 이름</span></td>
                    <td colspan="2"><input type="text" style="width:100%;" id="productOrderName"> </td>
                </tr>
                <tr>
                    <td><span>주문자 연락처</span></td>
                    <td colspan="2"><input type="text"  style="width:100%;" id="productOrderPhone"></td>
                </tr>
                <tr>
                    <td rowspan="3"><span>주문자 주소</span></td>
                    <td colspan="2" style="text-align: left;"><input type="text" style="width: 30%;  min-height: 100%; margin-right: 3rem;" id="productOrderAddress"><input type="button" style="width: 30%; height: 100%;" value="주소 찾기"> </td>
                </tr>
                <tr>
                    <td><input type="text" style="width:100%;" id="productOrderAddress1"></td>
                </tr>
                <tr>
                    <td><input type="text" style="width:100%;" id="productOrderAddress2"></td>
                </tr>

                </tbody>
            </table >

            <button class="purchase">구매하기</button>
        </li>





    </ul>
    <br/>


</div>
<h1></h1>

<%@ include file="layout/footer.jsp" %>
<script>

    $(function(){


        var s=<%= Integer.valueOf(request.getParameter("count"))%> *${oneProduct.data.productPrice};
        $('#orderPrice').text("결재 금액:"+(<%= Integer.valueOf(request.getParameter("count"))%> *${oneProduct.data.productPrice}).toLocaleString('ko-KR')+"원");



        $('.purchase').click(function (){

            let data={
                product:{
                    productTitle:'${oneProduct.data.productTitle}',
                    productImage:'${oneProduct.data.productImage}',
                    productPrice:'${oneProduct.data.productPrice}',
                    productContent:'${oneProduct.data.productContent}',
                    productCategory:'${oneProduct.data.productCategory}'
                },
                productOrderCount:<%= Integer.valueOf(request.getParameter("count"))%>,
                productOrderName:$("#productOrderName").val(),
                productOrderPhone: $("#productOrderPhone").val(),
                productOrderAddress:$("#productOrderAddress").val(),
                productOrderAddress1:$("#productOrderAddress1").val(),
                productOrderAddress2:$("#productOrderAddress2").val()
            };


            $.ajax({
                type:"POST",
                url:"/api/productorder",
                data:JSON.stringify(data),  //json 문자열로 변환 --http body 데이터임
                contentType:"application/json;charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
                dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 버퍼로와서 문자열
                                //, 생긴게(json)이라면 =>javascript 오브젝트로 변경해줌
            }).done(function (resp){
                console.log(resp.data)
                var form = document.createElement("form");
                form.action = "/product/productorder";
                form.method = "post";

                var input4=document.createElement("input");
                input4.setAttribute("type","hidden");
                input4.setAttribute("name","productOrderNumber");
                input4.setAttribute("value",resp.data.productOrderNumber);
                form.appendChild(input4);

                document.body.appendChild(form);
                form.submit();


            }).fail(function (error){
                alert(JSON.stringify(error));
            })

        });




    })








</script>