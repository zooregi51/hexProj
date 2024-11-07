<%@ page contentType="text/html; charset=UTF-8" %> <!-- 페이지의 콘텐츠 타입과 인코딩 설정 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 태그 라이브러리 선언 -->
<%@include file="../includes/header.jsp"%>

    
    <style>
        table {
            width: 100%; /* 테이블의 전체 너비를 설정 / テーブルの全幅を設定 */
            border-collapse: collapse; /* 테이블 경계선을 하나로 합침 / テーブルの枠線を一つにまとめる */
        }
        th, td {
            border: 1px solid black; /* 테두리 설정 / 枠線を設定 */
            padding: 8px;
            text-align: center; /* 텍스트 중앙 정렬 / テキストを中央揃え */
        }
        th {
            background-color: #f2f2f2; /* 헤더 배경색 설정 / ヘッダーの背景色を設定 */
        }
    </style>

    <h2>휴가 조회 상세 / 休暇照会詳細</h2> <!-- 페이지 제목 표시 -->
    <table>
        <thead>
            <tr>
                <th>휴가 번호 / 休暇番号</th> <!-- 휴가 기록 ID -->
                <th>사원 번호 / 社員番号</th> <!-- 사원 번호 -->
                <th>휴가 유형 / 休暇項目</th> <!-- 휴가 유형 -->
                <th>총 일수 / 総日数</th> <!-- 휴가의 총 일수 -->
                <th>사용 일수 / 使用日数</th> <!-- 사용한 일수 -->
                <th>남은 일수 / 残日数</th> <!-- 남은 일수 -->
                <th>휴가 기간 / 休暇期間</th> <!-- 휴가 기간 -->
                <th>입력일자 / 入力日</th> <!-- 입력된 날짜 -->
                <th>비고 / 備考</th> <!-- 비고 -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${leaveRecords}"> <!-- leaveRecords 목록을 반복하여 각 기록을 테이블에 표시 -->
                <tr>
                    <td>${record.leaveId}</td> <!-- 휴가 번호 표시 -->
                    <td>${record.empno}</td> <!-- 사원 번호 표시 -->
                    <td>${record.leaveType}</td> <!-- 휴가 유형 표시 -->
                    <td>${record.totalDays}</td> <!-- 총 일수 표시 -->
                    <td>${record.daysUsed}</td> <!-- 사용 일수 표시 -->
                    <td>${record.remainingDays}</td> <!-- 남은 일수 표시 -->
                    <td>${record.leavePeriod}</td> <!-- 휴가 기간 표시 -->
                    <td>${record.inputDate}</td> <!-- 입력일자 표시 -->
                    <td>${record.remarks}</td> <!-- 비고 표시 -->
                </tr>
            </c:forEach>
        </tbody>
    </table>

<%@include file="../includes/footer.jsp"%>
