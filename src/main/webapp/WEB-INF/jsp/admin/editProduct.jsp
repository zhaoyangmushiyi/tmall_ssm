<%--
  Created by IntelliJ IDEA.
  User: zhaoy
  Date: 2018/6/4
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {

        $("#addForm").submit(function () {
            if (!checkEmpty("name", "产品名称"))
                return false;
//          if (!checkEmpty("subTitle", "小标题"))
//              return false;
            if (!checkNumber("originalPrice", "原价格"))
                return false;
            if (!checkNumber("promotePrice", "优惠价格"))
                return false;
            if (!checkInt("stock", "库存"))
                return false;
            return true;
        });
    });
</script>
<html>
<head>
    <title>产品管理</title>
</head>
<body>
    <div class="workingArea">
        <ol class="breadcrumb">
            <li><a href="admin_category_list">所有分类</a> </li>
            <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a> </li>
            <li class="active">${p.name}</li>
            <li class="active">编辑产品</li>
        </ol>
            <div class="panel panel-warning editDiv">
                <div class="panel-heading">编辑产品</div>
                <div class="panel-body">
                    <form method="post" action="admin_product_update">
                        <table class="editTable">
                            <tr>
                                <td>产品名称</td>
                                <td><input id="name" name="name" type="text"
                                           value="${p.name}" class="form-control">
                                </td>
                            </tr>
                            <tr>
                                <td>产品小标题</td>
                                <td><input id="subTitle" name="subTitle" type="text"
                                           value="${p.subTitle}" class="form-control">
                                </td>
                            </tr>
                            <tr>
                                <td>原价格</td>
                                <td><input id="originalPrice" value="${p.originalPrice}" name="originalPrice" type="text"
                                            class="form-control">
                                </td>
                            </tr>
                            <tr>
                                <td>优惠价格</td>
                                <td><input id="promotePrice"  value="${p.promotePrice}" name="promotePrice" type="text"
                                           class="form-control">
                                </td>
                            </tr>
                            <tr>
                                <td>库存</td>
                                <td><input id="stock"  value="${p.stock}" name="stock" type="text"
                                           class="form-control">
                                </td>
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
