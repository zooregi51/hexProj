package attendance.service;

import attendance.dao.DiligenceDAO;
import attendance.model.DiligenceRecord;
import java.sql.Connection;
import java.util.List;

public class DiligenceService {
    private DiligenceDAO diligenceDAO; // DiligenceDAO 객체를 통해 데이터베이스와 상호작용 / DiligenceDAOオブジェクトを通じてデータベースと相互作用

    public DiligenceService(Connection conn) { // 생성자: 데이터베이스 연결을 받아 DiligenceDAO를 초기화합니다 / コンストラクタ：データベース接続を受け取りDiligenceDAOを初期化します
        this.diligenceDAO = new DiligenceDAO(conn);
    }

    public List<DiligenceRecord> getAllRecords() throws Exception { // 모든 근태 기록을 조회하여 반환 / すべての勤怠記録を取得して返します
        return diligenceDAO.getAllDiligenceRecords(); // DiligenceDAO의 getAllDiligenceRecords 메소드를 호출하여 모든 근태 기록을 가져옵니다 / DiligenceDAOのgetAllDiligenceRecordsメソッドを呼び出してすべての勤怠記録を取得します
    }
}
