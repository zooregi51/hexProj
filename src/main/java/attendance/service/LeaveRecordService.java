package attendance.service;

import attendance.dao.LeaveRecordDao;
import attendance.model.LeaveRecord;
import java.sql.Connection;
import java.util.List;

public class LeaveRecordService {
    // LeaveRecordDao 객체를 사용하여 데이터베이스와 상호작용 / LeaveRecordDaoオブジェクトを使用してデータベースと相互作用
    private LeaveRecordDao leaveRecordDao;

    // 생성자: 데이터베이스 연결을 받아 LeaveRecordDao를 초기화합니다 / コンストラクタ：データベース接続を受け取りLeaveRecordDaoを初期化します
    public LeaveRecordService(Connection conn) {
        this.leaveRecordDao = new LeaveRecordDao(conn);
    }

    // 모든 휴가 기록을 조회하여 반환 / すべての休暇記録を取得して返します
    public List<LeaveRecord> getAllLeaveRecords() throws Exception {
        // LeaveRecordDao의 selectAllRecords 메서드를 호출하여 모든 휴가 기록을 가져옵니다
        // LeaveRecordDaoのselectAllRecordsメソッドを呼び出してすべての休暇記録を取得します
        return leaveRecordDao.selectAllRecords();
    }

    // 특정 사원의 휴가 기록을 조회하여 반환 / 特定の社員の休暇記録を取得して返します
    public List<LeaveRecord> getLeaveRecordsByEmployee(int empno) throws Exception {
        // LeaveRecordDao의 selectByEmployee 메서드를 호출하여 특정 사원의 휴가 기록을 가져옵니다
        // LeaveRecordDaoのselectByEmployeeメソッドを呼び出して特定の社員の休暇記録を取得します
        return leaveRecordDao.selectByEmployee(empno);
    }
}
