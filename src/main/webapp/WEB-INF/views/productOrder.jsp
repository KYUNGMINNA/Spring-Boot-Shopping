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
    <H1>주문내역</H1>
 <ul style="margin:0; padding: 0; width: 100%;">
    <li style="width: 80%; margin-bottom: 3rem">
            <div style="text-align: left; width: 20%; height: 50%; float: left;">
                <!-- {oneProduct.productImage}-->
                    <img src="/api/product/productImage=${ordernumber.data.product.productImage}" alt="" width="100%;" height=100%;">

            </div>


            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
                <span style="font-weight: bolder; font-size: 2rem;">    ${ordernumber.data.product.productTitle}</span>
            </div>

            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
                <span style="font-weight: bolder; font-size: 2rem;">가격 :<fmt:formatNumber pattern="###,###,###" value="${ordernumber.data.product.productPrice}" />원</span>

            </div>
            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
            <span style="font-weight: bolder; font-size: 2rem;">선택 수량 :   ${ordernumber.data.productOrderCount} </span>
            </div>

            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">

                <span style="font-weight: bolder; font-size: 2rem;" id="orderPrice">
                    주문 금액 :<fmt:formatNumber pattern="###,###,###" value="${ordernumber.data.product.productPrice *ordernumber.data.productOrderCount}" />원
                </span>
            </div>

            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">

                <span style="font-weight: bolder; font-size: 2rem;">
                    주문 번호 :${ordernumber.data.productOrderNumber}
                </span>
            </div>

        </li>
            <br/>
        <li style="width: 80%;">


            <table border="1" width="80%" style="text-align: center; margin-bottom: 3rem">
                <tbody>
                <tr>
                    <td><span>주문자 이름</span></td>
                    <td colspan="2"><input type="text" style="width:100%;" id="productOrderName" value="${ordernumber.data.productOrderName}" readonly> </td>
                </tr>
                <tr>
                    <td><span>주문자 연락처</span></td>
                    <td colspan="2"><input type="text"  style="width:100%;" id="productOrderPhone" value="${ordernumber.data.productOrderPhone}" readonly></td>
                </tr>
                <tr>
                    <td rowspan="3"><span>주문자 주소</span></td>
                    <td colspan="2" style="text-align: left;">
                        <input type="text" style="width: 30%;  min-height: 100%; margin-right: 3rem;" id="productOrderAddress" value="${ordernumber.data.productOrderAddress}" readonly>

                </tr>
                <tr>
                    <td><input type="text" style="width:100%;" id="productOrderAddress1" value="${ordernumber.data.productOrderAddress1}" readonly></td>
                </tr>
                <tr>
                    <td><input type="text" style="width:100%;" id="productOrderAddress2" value="${ordernumber.data.productOrderAddress2}" readonly></td>
                </tr>

                </tbody>
            </table >
        </li>





    </ul>
    <br/>


</div>
<h1></h1>

<%@ include file="layout/footer.jsp" %>
<script>

    $(function(){




    })


</script>