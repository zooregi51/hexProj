package attendance.command;

import attendance.service.LeaveRecordService;
import attendance.model.LeaveRecord;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class LeaveRecordHandler implements CommandHandler {

    // 요청을 처리하는 메서드 / リクエストを処理するメソッド
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";  // 데이터베이스 URL / データベースURL
        String dbUser = "system";  // 데이터베이스 사용자 이름 / データベースユーザー名
        String dbPassword = "1234";  // 데이터베이스 비밀번호 / データベースパスワード

        // 데이터베이스 연결을 설정하고 휴가 기록을 조회합니다 / データベース接続を設定し、休暇記録を取得します
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            LeaveRecordService service = new LeaveRecordService(conn);  // 서비스 객체 생성 / サービスオブジェクトを生成
            List<LeaveRecord> leaveRecords = service.getAllLeaveRecords();  // 모든 휴가 기록을 가져옴 / すべての休暇記録を取得
            req.setAttribute("leaveRecords", leaveRecords);  // 요청 속성에 휴가 기록을 설정 / リクエスト属性に休暇記録を設定
        }

        // 휴가 기록 목록 페이지로 이동 / 休暇記録リストページに移動
        return "/WEB-INF/view/attendance/leaveRecordList.jsp";
    }
}
