<%--
  Created by IntelliJ IDEA.
  User: zhaoy
  Date: 2018/6/8
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>模仿天猫官网 ${p.name}</title>
</head>
<body>
    <div class="categoryPictureInProductPageDiv">
        <img class="categoryPictureInProductPage" src="img/category/${p.category.id}.jpg">
    </div>

    <div class="productPageDiv">
        <%@include file="imgAndInfo.jsp" %>
        <%@include file="productReview.jsp" %>
        <%@include file="productDetail.jsp" %>
    </div>
</body>
</html>
