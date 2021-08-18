<%--
  Created by IntelliJ IDEA.
  User: stell
  Date: 2021-08-18
  Time: 오후 4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>list page</h1>

<h4>${pageMaker}</h4>

<ul>
    <c:forEach items="${dtolist}" var="dtolist">
    <li>${dtolist}</li>
    </c:forEach>
</ul>

<hr>

<style>
    .pagelist{
        list-style: none;
        display: flex;
        flex-direction: row;
    }
    .pagelist li{
        margin-left: 0.3em ;
        background-color: lightgray;
    }
</style>

<ul class="pagelist">

    <c:if test="${pageMaker.prev}">
        <li><a href="/board/list?page=${pageMaker.start -1 }&size=${pageMaker.size}">Prev</a></li>
    </c:if>

    <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="page">
    <li><a href="/board/list?page=${page}&size=${pageMaker.size}">${page}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next}">
        <li><a href="/board/list?page=${pageMaker.end +1 }&size=${pageMaker.size}"> Next</a></li>
    </c:if>
</ul>
</body>
</html>
