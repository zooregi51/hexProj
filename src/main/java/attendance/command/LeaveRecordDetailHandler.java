package attendance.command;

import attendance.service.LeaveRecordService;
import attendance.model.LeaveRecord;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class LeaveRecordDetailHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL / データベースのURL
        String dbUser = "system"; // 데이터베이스 사용자 이름 / データベースのユーザー名
        String dbPassword = "1234"; // 데이터베이스 비밀번호 / データベースのパスワード

        int empno = Integer.parseInt(req.getParameter("empno")); // 사원 번호를 요청 파라미터에서 가져와 정수로 변환 / 社員番号をリクエストパラメータから取得して整数に変換

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) { // 데이터베이스 연결을 생성하여 leaveRecords 목록을 가져옵니다 / データベース接続を作成してleaveRecordsリストを取得します
            LeaveRecordService service = new LeaveRecordService(conn); // LeaveRecordService 객체 생성 / LeaveRecordServiceオブジェクトを生成
            List<LeaveRecord> leaveRecords = service.getLeaveRecordsByEmployee(empno); // 특정 사원의 기록을 가져오는 메서드 / 特定の社員の記録を取得するメソッド
            req.setAttribute("leaveRecords", leaveRecords); // 요청에 leaveRecords 속성 설정 / リクエストにleaveRecords属性を設定
        }

        return "/WEB-INF/view/attendance/leaveRecordDetail.jsp"; // JSP 페이지 경로 반환 / JSPページのパスを返す
    }
}
