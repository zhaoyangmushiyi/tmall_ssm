<%--
  Created by IntelliJ IDEA.
  User: zhaoy
  Date: 2018/6/4
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function(){
        $("#addForm").submit(function () {
            if (!checkEmpty("name", "属性管理")) {
                return false;
            }
            return true;
        });
    });
</script>
<html>
<head>
    <title>属性管理</title>
</head>
<body>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a> </li>
        <li><a href="admin_property_list?cid=${p.category.id}">${p.category.name}</a> </li>
        <li class="active">编辑属性</li>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">编辑属性</div>
        <div class="panel-body">
            <form method="post" action="admin_property_update">
                <table class="addTable">
                    <tr>
                        <td>属性名称</td>
                        <td><input id="name" name="name" type="text" value="${p.name}" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input name="id" type="hidden" value="${p.id}">
                            <input name="cid" type="hidden" value="${p.category.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp"%>
</body>
</html>