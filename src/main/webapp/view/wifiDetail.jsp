<%--
  Created by IntelliJ IDEA.
  User: kgh21
  Date: 2023-05-01
  Time: 오전 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="/css/base.css?after">
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<div class="link-wrapper">
    <a href="/">홈</a>
    <a href="/view/history.jsp">위치 히스토리 목록</a>
    <a href="/view/seoulWifi.jsp">Open API 와이파이 정보 가져오기</a>
    <a href="/view/bookmarkList.jsp">북마크 보기</a>
    <a href="/view/bookmarkGroupList.jsp">북마크 그룹 관리</a>
</div>

<%-- 북마크 관련 디자인 추가--%>
<div>
    <select class="bookmark-group-selector">
        <option value="">북마크 그룹 이름 선택</option>
    </select>
    <button class="bookmark-add-btn">북마크 추가하기</button>
</div>

<table class="w-100 common-table">
    <tbody class="detail-table-body">
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">거리(Km)</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">관리번호</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">자치구</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">와이파이명</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">도로명주소</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">상세주소</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">설치위치(층)</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">설치유형</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">설치기관</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">서비스구분</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">망종류</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">설치년도</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">실내외구분</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">WIFI접속환경</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">위도</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">경도</td>
        </tr>
        <tr class="table-row">
            <td class="common-table-header w-20 detail-table-title">작업일자</td>
        </tr>
    </tbody>
</table>

<script src="../js/wifiDetail.js?after"></script>
</body>
</html>
