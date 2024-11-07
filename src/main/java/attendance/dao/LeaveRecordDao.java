package attendance.dao;

import attendance.model.LeaveRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRecordDao {
    private Connection conn;  // 데이터베이스 연결 객체 / データベース接続オブジェクト

    // 생성자: 데이터베이스 연결을 받아 초기화합니다 / コンストラクタ：データベース接続を受け取って初期化します
    public LeaveRecordDao(Connection conn) {
        this.conn = conn;
    }

    // 전체 휴가 기록을 조회하는 메서드 / 全休暇記録を取得するメソッド
    public List<LeaveRecord> selectAllRecords() throws SQLException {
        List<LeaveRecord> records = new ArrayList<>();
        String sql = "SELECT leave_id, empno, leave_type, total_days, days_used, remaining_days, leave_period, input_date, remarks FROM LeaveRecords";

        // SQL 쿼리를 실행하여 모든 휴가 기록을 조회합니다 / SQLクエリを実行して全ての休暇記録を取得します
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LeaveRecord record = new LeaveRecord(
                    rs.getInt("leave_id"),  // 휴가 ID / 休暇ID
                    rs.getInt("empno"),  // 사원 번호 / 社員番号
                    rs.getString("leave_type"),  // 휴가 유형 / 休暇の種類
                    rs.getInt("total_days"),  // 총 일수 / 総日数
                    rs.getInt("days_used"),  // 사용 일수 / 使用日数
                    rs.getInt("remaining_days"),  // 남은 일수 / 残り日数
                    rs.getString("leave_period"),  // 휴가 기간 / 休暇期間
                    rs.getDate("input_date"),  // 입력 일자 / 入力日
                    rs.getString("remarks")  // 비고 / 備考
                );
                records.add(record);  // 결과 리스트에 추가 / 結果リストに追加
            }
        }
        return records;  // 모든 휴가 기록 반환 / 全ての休暇記録を返す
    }

    // 특정 사원의 휴가 기록을 조회하는 메서드 / 特定社員の休暇記録を取得するメソッド
    public List<LeaveRecord> selectByEmployee(int empno) throws SQLException {
        List<LeaveRecord> records = new ArrayList<>();
        String sql = "SELECT leave_id, empno, leave_type, total_days, days_used, remaining_days, leave_period, input_date, remarks FROM LeaveRecords WHERE empno = ?";

        // 사원 번호를 바탕으로 SQL 쿼리를 실행하여 해당 사원의 휴가 기록을 조회합니다 / 社員番号に基づいてSQLクエリを実行し、その社員の休暇記録を取得します
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);  // 사원 번호를 쿼리 매개변수로 설정 / 社員番号をクエリパラメータとして設定
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LeaveRecord record = new LeaveRecord(
                        rs.getInt("leave_id"),  // 휴가 ID / 休暇ID
                        rs.getInt("empno"),  // 사원 번호 / 社員番号
                        rs.getString("leave_type"),  // 휴가 유형 / 休暇の種類
                        rs.getInt("total_days"),  // 총 일수 / 総日数
                        rs.getInt("days_used"),  // 사용 일수 / 使用日数
                        rs.getInt("remaining_days"),  // 남은 일수 / 残り日数
                        rs.getString("leave_period"),  // 휴가 기간 / 休暇期間
                        rs.getDate("input_date"),  // 입력 일자 / 入力日
                        rs.getString("remarks")  // 비고 / 備考
                    );
                    records.add(record);  // 결과 리스트에 추가 / 結果リストに追加
                }
            }
        }
        return records;  // 해당 사원의 휴가 기록 반환 / その社員の休暇記録を返す
    }
}
