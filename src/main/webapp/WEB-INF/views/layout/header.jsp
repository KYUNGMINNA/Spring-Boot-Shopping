<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@2.0/nanumsquare.css">
    <style>
        html,
        body {
            font-family: 'NanumSquare', sans-serif;
            font-size: 16px;
            height: 100%;
            width: 100%;


        }

        body {
            margin: 0;
        }


        header.container::after {
            content: '';
            display: block;
            clear: both;

        }

        .header div {
            height: 124px;
        }

        .ulli li {

            display: inline-block;
            margin-right: 1rem;
            color: green;
            font-weight: 800;
            font-size: 24px;
        }

        .ulli li a {
            color: green;
        }


        .container li {
            display: inline-block;
            margin-right: 1rem;
        }

        a {
            text-decoration: none;
            color: black;
        }
        .footer {
            height: 12%;
            position: relative;
            /*transform: translatY(-100%);*/
        }
        .footer div ul li{
            display: inline-block;
            padding-top: 1rem;
            margin-right: 1rem;
        }

    </style>
</head>
<body>
<div class="header" style="background-color: #7FD1AE; height: 180px; ">

    <div class="container" style="padding-top: 2rem;" >

        <div class="logo" style="float: left; margin-left: 5rem; width: auto;">
            <a href="/">
                <img src="/image/salad.svg" alt="" width="120px;" height="120px;">
                <span  style="position:relative; left: -7.5rem; top: 1.5rem; font-weight: bolder; font-size: 2rem;">샐러드샵</span>
            </a>


      <%--      <div style="">
            <input type="text" style="background-color:#FCFAEC; border-color: #87AB7B;  width: 300px; height: 30px; position: relative; left:10rem; bottom: 3rem; font-size: 1rem;" >
            <a href="">
                <span style=" position: relative; left:1rem; bottom: 2rem;">?</span>
            </a>
            </div>--%>





        </div>
        <div>
            <a href="/product/mypage" style="position: absolute; right: 3rem; top: 5rem;">
                <img src="/image/basket.svg" alt="" width="60px;" height="40px;">
            </a>
        </div>


    </div>
</div>

<hr style="margin-top: 0px;">
<div class="ulli" style="text-align: center;">
    <ul style="margin:0; width: 100%; padding-left: 0;">
        <li style="width: 18%;"> <a href="/product/category?category=샐러드">샐러드</a></li>
        <li style="width: 18%;"> <a href="/product/category?category=도시락">도시락</a></li>
        <li style="width: 18%;"> <a href="/product/category?category=간편식">간편식</a></li>
        <li style="width: 18%;"> <a href="">할인</a></li>
    </ul>
</div>
<hr>

