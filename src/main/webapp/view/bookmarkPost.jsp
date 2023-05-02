<%--
  Created by IntelliJ IDEA.
  User: kgh21
  Date: 2023-05-01
  Time: 오전 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="../css/base.css?after">
</head>
<body>
<h1>북마크 그룹</h1>
<div class="link-wrapper">
    <a href="/">홈</a>
    <a href="/view/history.jsp">위치 히스토리 목록</a>
    <a href="/view/seoulWifi.jsp">Open API 와이파이 정보 가져오기</a>
    <a href="#">북마크 보기</a>
    <a href="/view/bookmarkGroupList.jsp">북마크 그룹 관리</a>
</div>

<table class="w-100 common-table">
    <tbody class="detail-table-body">
    <tr class="table-row">
        <td class="common-table-header w-20 detail-table-title">북마크 이름</td>
        <td><input class="bookmark-post-name-input" type="text" placeholder="북마크 이름"/></td>
    </tr>
    <tr class="table-row">
        <td class="common-table-header w-20 detail-table-title">순서</td>
        <td><input class="bookmark-post-order-input" type="number" placeholder="순서"/></td>
    </tr>
    </tbody>
</table>
<div class="bookmark-group-post-action-wrapper">

</div>


<script src="/js/bookmarkPost.js?after"></script>
</body>
</html>
