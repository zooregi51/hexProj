package attendance.dao;

import attendance.model.DiligenceRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiligenceDAO {
    private Connection conn; // 데이터베이스 연결 객체 / データベース接続オブジェクト

    public DiligenceDAO(Connection conn) {
        this.conn = conn; // 데이터베이스 연결 설정 / データベース接続設定
    }

    public List<DiligenceRecord> getAllDiligenceRecords() throws SQLException {
        List<DiligenceRecord> records = new ArrayList<>(); // 근태 기록 리스트 생성 / 勤怠記録リストを生成
        String sql = "SELECT * FROM DiligenceRecords"; // 근태 기록 테이블에서 모든 데이터 조회 / 勤怠記録テーブルからすべてのデータを取得

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) { // SQL 문 실행 및 결과 가져오기 / SQL文を実行して結果を取得
            while (rs.next()) { // 결과 집합에서 다음 행으로 이동 / 結果セットで次の行に移動
                DiligenceRecord record = new DiligenceRecord(
                    rs.getInt("RecordID"), // 기록 ID 가져오기 / 記録IDを取得
                    rs.getInt("EmployeeID"), // 사원 번호 가져오기 / 社員番号を取得
                    rs.getString("DiligenceType"), // 근태 유형 가져오기 / 勤怠項目を取得
                    rs.getDate("StartDate"), // 시작일 가져오기 / 開始日を取得
                    rs.getDate("EndDate"), // 종료일 가져오기 / 終了日を取得
                    rs.getInt("DiligenceDays"), // 근태 일수 가져오기 / 勤怠日数を取得
                    rs.getString("Remarks") // 비고 가져오기 / 備考を取得
                );
                records.add(record); // 리스트에 근태 기록 추가 / リストに勤怠記録を追加
            }
        }
        return records; // 근태 기록 리스트 반환 / 勤怠記録リストを返す
    }
}
