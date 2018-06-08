<%--
  Created by IntelliJ IDEA.
  User: zhaoy
  Date: 2018/6/8
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>模仿天猫官网-${c.name}</title>
</head>
<body>
    <div id="category">
        <div class="categoryPageDiv">
            <img src="img/category/${c.id}.jpg">
            <%@include file="sortBar.jsp"%>
            <%@include file="productsByCategory.jsp"%>
        </div>
    </div>
</body>
</html>
