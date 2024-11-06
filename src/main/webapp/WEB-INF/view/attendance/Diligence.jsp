<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>근태 기록 관리</title>
    <style>
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>근태 기록 관리</h2>
    <table>
        <thead>
            <tr>
                <th>기록 ID</th>
                <th>사원 번호</th>
                <th>근태 유형</th>
                <th>시작일</th>
                <th>종료일</th>
                <th>근태 일수</th>
                <th>비고</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${diligenceRecords}">
                <tr>
                    <td>${record.recordId}</td>
                    <td>${record.employeeId}</td>
                    <td>${record.diligenceType}</td>
                    <td>${record.startDate}</td>
                    <td>${record.endDate}</td>
                    <td>${record.diligenceDays}</td>
                    <td>${record.remarks}</td>
                    <td><button onclick="openDetails(${record.recordId})">관리</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
<script>
    function openDetails(recordId) {
        // 모달 창 열기 로직 추가 예정
        alert("기록 ID " + recordId + "의 상세 정보 페이지로 이동합니다.");
    }
</script>
</html>
