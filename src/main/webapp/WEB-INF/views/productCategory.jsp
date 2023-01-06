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


     <div>

        <!--model.addAttribute("paging",page);
        model.addAttribute("productList",productpage); -->
        <h2 style="font-weight: bolder; font-size: 2rem">${productList.content[0].productCategory}</h2>
    </div>
    <ul style="margin:0; padding: 0; width: 100%;">
        <c:forEach var="product" items="${productList.content}">
            <li style="width: 20%;">
                <a href="/product/productselect?category=${productList.content[0].productCategory}&title=${product.productTitle}">
                    <img src="/api/product/productImage=${product.productImage}" alt="" width="100%;" height="150px;">
                </a>
                <div>
                    <a href="/product/productselect?category=${productList.content[0].productCategory}&title=${product.productTitle}">
                        <span style="font-size: 1.1rem; font-weight: bold;">${product.productTitle}</span>
                    </a>
                </div>
                <div>
                    <span style="font-weight: bold;"><fmt:formatNumber pattern="###,###,###" value="${product.productPrice}" />원</span>
                </div>
            </li>
        </c:forEach>
    </ul>

    <ul>
        <c:choose>
            <c:when test="${productList.first || productList.number<=10}">
                <li class="page-item disabled" style="display: none; margin-right: 10px;">
                    <a class="page-link" href="?category=${productList.content[0].productCategory}&page=${productList.number-1}">Previous</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item" style="margin-right: 10px;">
                    <a class="page-link" href="?category=${productList.content[0].productCategory}&page=${productList.number-1}">Previous</a>
                </li>
            </c:otherwise>
        </c:choose>

        <c:forEach var="listno" begin="${paging[2]}" end="${paging[3]}">
            <li class="page-item ${paging[0] ==listno ? 'active' :''}" id="pageNum">
                <a class="page-link" href="?category=${productList.content[0].productCategory}&page=${listno-1}"  style="margin-right: 10px;">${listno}</a>
            </li>
        </c:forEach>


        <c:choose>
            <c:when test="${productList.last || productList.number<=10}">
                <li class="page-item disabled" style="display: none">
                    <a class="page-link" href="?category=${productList.content[0].productCategory}&page=${productList.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="??category=${productList.content[0].productCategory}&page=${productList.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>

</div>
<%@ include file="layout/footer.jsp" %>
