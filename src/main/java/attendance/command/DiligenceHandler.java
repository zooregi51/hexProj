package attendance.command;

import attendance.model.DiligenceRecord;
import attendance.service.DiligenceService;
import mvc.command.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DiligenceHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL 설정 / データベースURL設定
        String dbUser = "system"; // 데이터베이스 사용자명 설정 / データベースユーザー名設定
        String dbPassword = "1234"; // 데이터베이스 비밀번호 설정 / データベースパスワード設定

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) { // 데이터베이스 연결 생성 / データベース接続を生成
            DiligenceService service = new DiligenceService(conn); // DiligenceService 인스턴스 생성 / DiligenceService インスタンス生成
            List<DiligenceRecord> diligenceRecords = service.getAllRecords(); // 모든 근태 기록을 가져옴 / すべての勤怠記録を取得
            req.setAttribute("diligenceRecords", diligenceRecords); // 근태 기록을 요청 속성에 추가 / 勤怠記録をリクエスト属性に追加
        }
        
        return "/WEB-INF/view/attendance/DiligenceRecordList.jsp"; // 근태 기록 관리 페이지로 이동 / 勤怠記録管理ページに移動
    }
}
