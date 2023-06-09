<%--
  Created by IntelliJ IDEA.
  User: kgh21
  Date: 2023-05-02
  Time: 오후 4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="/css/base.css?after">
</head>
<body>
<h1>북마크 목록</h1>
<div class="link-wrapper">
    <a href="/">홈</a>
    <a href="/view/history.jsp">위치 히스토리 목록</a>
    <a href="/view/seoulWifi.jsp">Open API 와이파이 정보 가져오기</a>
    <a href="/view/bookmarkList.jsp">북마크 보기</a>
    <a href="/view/bookmarkGroupList.jsp">북마크 그룹 관리</a>
</div>

<table class="common-table w-100">
    <thead class="common-table-header ">
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>와이파이명</th>
            <th>등록일자</th>
            <th>비고</th>
        </tr>
    </thead>
    <tbody class="bookmark-table-body text-center">

    </tbody>
</table>

<script src="/js/bookmarkList.js?after"></script>
</body>
</html>
