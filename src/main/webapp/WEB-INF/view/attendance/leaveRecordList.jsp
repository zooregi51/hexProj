<%@ page contentType="text/html; charset=UTF-8" %> <!-- 페이지 콘텐츠 타입을 UTF-8로 설정하여 인코딩 문제를 방지합니다 / ページのコンテンツタイプをUTF-8に設定し、エンコーディングの問題を防ぎます -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL의 core 태그 라이브러리를 사용하기 위해 선언합니다 / JSTLのcoreタグライブラリを使用するために宣言します -->

<html>
<head>
    <title>휴가 조회 / 休暇照会</title> <!-- 페이지의 제목을 '휴가 조회'로 설정합니다 / ページのタイトルを「休暇照会」に設定します -->
    <style>
        table {
            width: 100%; /* 테이블이 페이지의 100% 너비를 차지하도록 설정합니다 / テーブルがページの100％の幅を占めるように設定します */
            border-collapse: collapse; /* 테이블 테두리를 병합하여 깔끔하게 표시합니다 / テーブルの境界線を統合して見やすくします */
        }
        th, td {
            border: 1px solid black; /* 테이블 헤더와 셀에 검정색 테두리를 설정합니다 / テーブルヘッダーとセルに黒の境界線を設定します */
            padding: 8px; /* 셀 안의 텍스트와 테두리 간의 간격을 설정합니다 / セル内のテキストと境界線との間のスペースを設定します */
            text-align: center; /* 텍스트를 가운데 정렬합니다 / テキストを中央揃えにします */
        }
        th {
            background-color: #f2f2f2; /* 테이블 헤더의 배경색을 연회색으로 설정하여 구분을 줍니다 / テーブルヘッダーの背景色をライトグレーに設定し、区別します */
        }
    </style>
    <script>
        // 사원 번호를 클릭했을 때 상세 정보 페이지로 이동하는 함수 / 社員番号をクリックしたときに詳細情報ページに移動する関数
        function openDetail(empno) {
            window.location.href = "/attendance/leaveRecordDetail.do?empno=" + empno; /* 클릭한 사원 번호를 쿼리 매개변수로 전달하여 URL에 추가합니다 / クリックした社員番号をクエリパラメータとしてURLに追加します */
        }
    </script>
</head>
<body>
    <h2>휴가 조회 / 休暇照会</h2> <!-- 페이지에 표시되는 제목으로, '휴가 조회'를 표시합니다 / ページに表示されるタイトルとして「休暇照会」を表示します -->
    <table>
        <thead>
            <tr>
                <th>휴가 번호 / 休暇番号</th> 
                <th>사원 번호 / 社員番号</th> 
                <th>휴가 유형 / 休暇の種類</th> 
                <th>총 일수 / 総日数</th> 
                <th>사용 일수 / 使用日数</th> 
                <th>남은 일수 / 残り日数</th> 
            </tr>
        </thead>
        <tbody>
            <!-- leaveRecords 리스트의 각 항목을 순회하여 테이블 행에 표시합니다 / leaveRecordsリストの各項目を繰り返し、テーブル行に表示します -->
            <c:forEach var="record" items="${leaveRecords}">
                <tr onclick="openDetail(${record.empno})" style="cursor: pointer;"> <!-- 각 행을 클릭 가능하도록 하여 사원 번호 클릭 시 상세 정보로 이동합니다 / 各行をクリック可能にし、社員番号をクリックすると詳細情報に移動します -->
                    <td>${record.leaveId}</td> 
                    <td>${record.empno}</td>
                    <td>${record.leaveType}</td> 
                    <td>${record.totalDays}</td> 
                    <td>${record.daysUsed}</td> 
                    <td>${record.remainingDays}</td> 
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
