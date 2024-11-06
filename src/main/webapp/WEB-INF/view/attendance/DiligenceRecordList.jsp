<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>근태 기록 관리 / 勤怠記録管理</title> <!-- 페이지 제목 설정 / ページのタイトルを設定 -->
    <style>
        body {
            display: flex; /* 페이지를 두 개의 컨테이너로 나누기 위해 flex 사용 / ページを2つのコンテナに分けるためにflexを使用 */
        }
        .table-container {
            width: 70%; /* 테이블 컨테이너 너비 / テーブルコンテナの幅 */
        }
        .form-container {
            width: 30%; /* 폼 컨테이너 너비 / フォームコンテナの幅 */
            padding: 20px;
            border-left: 1px solid #ccc; /* 좌측에 구분선 추가 / 左側に区切り線を追加 */
        }
        table { 
            width: 100%; 
            border-collapse: collapse; /* 테이블 경계선을 하나로 합침 / テーブルの枠線を一つにまとめる */
        }
        th, td { 
            border: 1px solid black; 
            padding: 8px; 
            text-align: center; /* 중앙 정렬 / 中央揃え */
        }
        th { 
            background-color: #f2f2f2; /* 헤더 배경색 설정 / ヘッダーの背景色を設定 */
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold; /* 레이블을 볼드 처리 / ラベルを太字にする */
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="table-container">
        <h2>근태 기록 관리 / 勤怠記録管理</h2>
        <table>
            <thead>
                <tr>
                    <th>기록 ID / 記録ID</th>
                    <th>사원 번호 / 社員番号</th>
                    <th>근태 유형 / 勤怠項目</th>
                    <th>시작일 / 開始日</th>
                    <th>종료일 / 終了日</th>
                    <th>근태 일수 / 勤怠日数</th>
                    <th>비고 / 備考</th>
                    <th>관리 / 管理</th>
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
                        <td><button onclick="openDetails(${record.recordId})">관리 / 管理</button></td> <!-- 관리 버튼 / 管理ボタン -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="form-container">
        <h3>근태 기록 입력 / 勤怠記録入力</h3> <!-- 입력 폼 제목 / 入力フォームのタイトル -->
        <form action="/attendance/saveDiligenceRecord.do" method="post">
            <div class="form-group">
                <label for="diligenceType">근태 항목 / 勤怠項目</label> <!-- 근태 항목 선택 / 勤怠項目の選択 -->
                <select id="diligenceType" name="diligenceType">
                    <option value="">선택하세요 / 選択してください</option>
                    <option value="연차">연차 / 年次</option>
                    <option value="병가">병가 / 病気休暇</option>
                    <option value="출장">출장 / 出張</option>
                    <!-- 추가 옵션 필요시 여기에 추가 / 追加オプションが必要な場合はここに追加 -->
                </select>
            </div>
            <div class="form-group">
                <label for="startDate">기간 시작일 / 期間開始日</label> <!-- 시작일 선택 / 開始日の選択 -->
                <input type="date" id="startDate" name="startDate" required>
            </div>
            <div class="form-group">
                <label for="endDate">기간 종료일 / 期間終了日</label> <!-- 종료일 선택 / 終了日の選択 -->
                <input type="date" id="endDate" name="endDate" required>
            </div>
            <div class="form-group">
                <label for="diligenceDays">근태 일수 / 勤怠日数</label> <!-- 근태 일수 입력 / 勤怠日数の入力 -->
                <input type="number" id="diligenceDays" name="diligenceDays" required>
            </div>
            <div class="form-group">
                <label for="amount">금액(수당) / 金額(手当)</label> <!-- 금액 입력 / 金額の入力 -->
                <input type="number" id="amount" name="amount">
            </div>
            <div class="form-group">
                <label for="remarks">비고 / 備考</label> <!-- 비고 입력 / 備考の入力 -->
                <input type="text" id="remarks" name="remarks">
            </div>
            <div class="form-group">
                <button type="submit">저장 / 保存</button> <!-- 저장 버튼 / 保存ボタン -->
                <button type="reset">내용 지우기 / 内容を消去</button> <!-- 초기화 버튼 / リセットボタン -->
            </div>
        </form>
    </div>
</body>
<script>
    function openDetails(recordId) {
        // 모달 창 열기 로직 추가 예정 / モーダルウィンドウを開くロジックを追加予定
        alert("기록 ID " + recordId + "의 상세 정보 페이지로 이동합니다. / 記録ID " + recordId + "の詳細情報ページに移動します。");
    }
</script>
</html>
