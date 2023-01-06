<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<style>

    .container {
        height: auto;
        min-height: 55%;
        padding-bottom: 180px;
    ;
    }


</style>
<%@ include file="layout/header.jsp"%>
<div class="container" style="text-align: center;">
  <div>
    <h2 style="font-weight: bolder; font-size: 2rem">${oneProduct.data.productCategory}</h2>

    </div>
        <ul style="margin:0; padding: 0; width: 100%;">


        <li style="width: 80%;">
            <div style="text-align: left; width: 50%; height: 25%; float: left;">
            <a href="">
                <img src="/api/product/productImage=${oneProduct.data.productImage}" alt="" width="100%;" height=100%;">
            </a>
            </div>


            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
                   <span style="font-weight: bolder; font-size: 2rem;"> ${oneProduct.data.productTitle}</span>
            </div>

            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
            </div>

            <div style="text-align: left; position:relative; left: 2rem; margin-bottom: 2rem;">
                <div>
                    <div>
                        <button type="button" class="count_minus count_btn" style="width: 4rem; height: 2rem; font-size: 1.5rem;">-</button>
                        <input   class=" values" type="text" value="1" style="width: 5rem; height: 2rem; text-align: center; font-weight: bold; font-size: 1.5rem;">
                        <button type="button" class="count_plus count_btn" style="width: 4rem; height: 2rem; font-size: 1.5rem;">+</button>
                    </div>
                </div>
            </div>

            <div style="text-align: left; position:relative; left: 7rem;">
                <a  class="purcahseBtn" href="">
                <span style="font-size:2rem; background-color: #F0EEE3; border: 2px solid gray; background-color: #CCFBE5; " >구매</span>
                </a>

            </div>

        </li>
        </ul>
    <br/>


</div>


<%@ include file="layout/footer.jsp"%>

<script>
    $(function() {
        var value = $('.values').val();

        $('.count_minus').click(function () {
            if (value <= 1) {
                $('.values').val(1);
                return;
            }
            value = parseInt(value) - 1;
            $('.values').val(value);
        });

        $('.count_plus').click(function () {

            if (value >= 9) {
                $('.values').val(9);
                return;
            }
            value = parseInt(value) + 1;
            $('.values').val(value);
        });


        $('.purcahseBtn').click(function(e){
            e.preventDefault();

           location.href="/product/purchase?category=${oneProduct.data.productCategory}&title=${oneProduct.data.productTitle}&count="+value;
        });
    });
</script>
