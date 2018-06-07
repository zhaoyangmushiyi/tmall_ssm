<%--
  Created by IntelliJ IDEA.
  User: zhaoy
  Date: 2018/6/6
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    function showProductsAsideCategories(cid){
        $("div.eachCategory[cid="+cid+"]").css("background-color","white");
        $("div.eachCategory[cid="+cid+"] a").css("color","#87CEFA");
        $("div.productsAsideCategories[cid="+cid+"]").show();
    }

    function hideProductsAsideCategories(cid){
        $("div.eachCategory[cid="+cid+"]").css("background-color","#e2e2e3");
        $("div.eachCategory[cid="+cid+"] a").css("color","#000");
        $("div.productsAsideCategories[cid="+cid+"]").hide();
    }
    $(function(){
        $("div.eachCategory").mouseenter(function(){
            var cid = $(this).attr("cid");
            showProductsAsideCategories(cid);
        });
        $("div.eachCategory").mouseleave(function(){
            var cid = $(this).attr("cid");
            hideProductsAsideCategories(cid);
        });
        $("div.productsAsideCategories").mouseenter(function(){
            var cid = $(this).attr("cid");
            showProductsAsideCategories(cid);
        });
        $("div.productsAsideCategories").mouseleave(function(){
            var cid = $(this).attr("cid");
            hideProductsAsideCategories(cid);
        });

        $("div.rightMenu span").mouseenter(function(){
            var left = $(this).position().left;
            var top = $(this).position().top;
            var width = $(this).css("width");
            var destLeft = parseInt(left) + parseInt(width)/2;
            $("img#catear").css("left",destLeft);
            $("img#catear").css("top",top-20);
            $("img#catear").fadeIn(500);

        });
        $("div.rightMenu span").mouseleave(function(){
            $("img#catear").hide();
        });

        var left = $("div#carousel-of-product").offset().left;
        $("div.categoryMenu").css("left",left-20);
        $("div.categoryWithCarousel div.head").css("margin-left",left);
        $("div.productsAsideCategories").css("left",left-20);

    });
</script>

<img src="img/site/catear.png" id="catear" class="catear"/>

<div class="categoryWithCarousel">

    <div class="headbar show1">
        <div class="head ">

            <span style="margin-left:10px" class="glyphicon glyphicon-th-list"></span>
            <span style="margin-left:10px" >商品分类</span>

        </div>

        <div class="rightMenu">
            <span><a href=""><img src="img/site/chaoshi.png"/></a></span>
            <span><a href=""><img src="img/site/guoji.png"/></a></span>

            <c:forEach items="${cs}" var="c" varStatus="st">
                <c:if test="${st.count<=4}">
                <span>
                <a href="forecategory?cid=${c.id}">
                        ${c.name}
                </a></span>
                </c:if>
            </c:forEach>
        </div>

    </div>

    <div style="position: relative">
        <%@include file="categoryMenu.jsp" %>
    </div>

    <div style="position: relative;left: 0;top: 0;">
        <%@include file="productsAsideCategories.jsp" %>
    </div>

    <%@include file="carousel.jsp" %>

    <div class="carouselBackgroundDiv">
    </div>

</div>
