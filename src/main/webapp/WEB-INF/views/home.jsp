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


    <div> <!--productList.body.data.id -->
    <h2 style="font-weight: bolder; font-size: 2rem">샐러드</h2>
    <a href="/product/category?category=샐러드">

        <h3 style="text-align: right; position: relative; right:10%">더보기</h3>
    </a>
    </div>
        <ul style="margin:0; padding: 0; width: 100%;">

        <c:forEach var="salad" items="${saladList.body.data}">
        <li style="width: 20%;">
            <a href="/product/productselect?category=${salad.productCategory}&title=${salad.productTitle}">
                <img src="/api/product/productImage=${salad.productImage}" alt="zzz" width="100%;" height="150px;">
            </a>
            <div>
                <a href="" style="display: block;">
                    <span style="font-size: 1.1rem; font-weight: bold;">${salad.productTitle}</span>
                </a>
            </div>
            <div>
                <span style=" font-weight: bold;"><fmt:formatNumber pattern="###,###,###" value="${salad.productPrice}" />원</span>
            </div>

        </li>
        </c:forEach>
              </ul>
              <br/>

              <div>
                  <h2 style="font-weight: bolder; font-size: 2rem">도시락</h2>
                  <a href="/product/category?category=도시락">

                      <h3 style="text-align: right; position: relative; right:10%">더보기</h3>
                  </a>   </div>
              <ul style="margin:0; padding: 0; width: 100%;">


                  <c:forEach var="lunchBox" items="${lunchBoxList.body.data}">
                      <li style="width: 20%;">
                          <a href="/product/productselect?category=${lunchBox.productCategory}&title=${lunchBox.productTitle}">
                              <img src="/api/product/productImage=${lunchBox.productImage}" alt="zzz" width="100%;" height="150px;">
                          </a>
                          <div>
                              <a href="" style="display: block;">
                                  <span style="font-size: 1.1rem; font-weight: bold;">${lunchBox.productTitle}</span>
                              </a>
                          </div>
                          <div>
                              <span style=" font-weight: bold;"><fmt:formatNumber pattern="###,###,###" value="${lunchBox.productPrice}" />원</span>
                          </div>

                      </li>
                  </c:forEach>

              </ul>
              <br/>

              <div>
                  <h2 style="font-weight: bolder; font-size: 2rem">간편식</h2>
                  <a href="/product/category?category=간편식">

                      <h3 style="text-align: right; position: relative; right:10%">더보기</h3>
                  </a>  </div>
              <ul style="margin:0; padding: 0; width: 100%;">


                  <c:forEach var="convenience" items="${convenienceList.body.data}">
                      <li style="width: 20%;">
                          <a href="/product/productselect?category=${convenience.productCategory}&title=${convenience.productTitle}">
                              <img src="/api/product/productImage=${convenience.productImage}" alt="zzz" width="100%;" height="150px;">
                          </a>
                          <div>
                              <a href="" style="display: block;">
                                  <span style="font-size: 1.1rem; font-weight: bold;">${convenience.productTitle}</span>
                              </a>
                          </div>
                          <div>
                              <span style=" font-weight: bold;"><fmt:formatNumber pattern="###,###,###" value="${convenience.productPrice}" />원</span>
                          </div>

                      </li>
                  </c:forEach>




              </ul>
</div>


<%@ include file="layout/footer.jsp"%>
