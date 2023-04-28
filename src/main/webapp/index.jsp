<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
            <a href="/view/wifi.jsp">Open API 와이파이 정보 가져오기</a>
        </div>
        <div>
            <label for="LAT">LAT</label>
            <input id="LAT" type="number" >
            <label for="LNT">LNT</label>
            <input id="LNT" type="number" >
            <button id="my-location-btn">내 위치 가져오기</button>
            <button id="near_wifi_btn">근처 WIFI 정보 보기</button>
        </div>
        <table class="common-table w-100">
            <thead class="common-table-header">
                <tr>
                    <th>거리(Km)</th>
                    <th>관리번호</th>
                    <th>자치구</th>
                    <th>와이파이명</th>
                    <th>도로명주소</th>
                    <th>상세주소</th>
                    <th>설치위치(층)</th>
                    <th>설치유형</th>
                    <th>설치기관</th>
                    <th>서비스구분</th>
                    <th>망종류</th>
                    <th>설치년도</th>
                    <th>실내외구분</th>
                    <th>WIFI접속환경</th>
                    <th>경도</th>
                    <th>위도</th>
                    <th>작업일자</th>
                </tr>
            </thead>
            <tbody class="wifi-body">

            </tbody>
        </table>
        <div class="wifi-table-before-retrieve flex">위치 정보를 입력한 후에 조회해주세요.</div>

    <script src="js/home.js?after"></script>
    </body>
</html>