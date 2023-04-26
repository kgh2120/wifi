<%--
  Created by IntelliJ IDEA.
  User: kgh21
  Date: 2023-04-26
  Time: 오후 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>서울 와이파이 데이터</title>
    <link rel="stylesheet" href="../css/base.css">
</head>
<body>
    <div class="wifi-wrapper">
        <h1><%= request.getAttribute("count")%>개의 WIFi 정보를 정상적으로 저장하였습니다.</h1>
        <a href="/">홈으로 가기</a>
    </div>
</body>
</html>
