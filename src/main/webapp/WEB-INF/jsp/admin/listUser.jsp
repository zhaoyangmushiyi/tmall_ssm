<%--
  Created by IntelliJ IDEA.
  User: zhaoy
  Date: 2018/6/5
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<%--<script>--%>
    <%--$(function(){--%>
        <%--$("#addForm").submit(function () {--%>
            <%--if (!checkEmpty("name", "用户名称")) {--%>
                <%--return false;--%>
            <%--}--%>
            <%--return true;--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
    <div class="workingArea">
        <h1 class="label label-info">用户管理</h1>
        <br>
        <br>
        <div class="listDataTableDiv">
            <table class="table table-striped table-bordered table-hover table-condensed">
                <thead>
                    <tr class="success">
                        <th>ID</th>
                        <td>用户名称</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${us}" var="u">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
        <div class="pageDiv">
            <%@include file="../include/admin/adminPage.jsp"%>
        </div>
    </div>
    <%@include file="../include/admin/adminFooter.jsp"%>
</body>
</html>
