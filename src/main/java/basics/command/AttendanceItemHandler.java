package basics.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basics.model.AttendanceItem;
import basics.service.AttendanceItemService;
import mvc.command.CommandHandler;

// AttendanceItemHandler는 사용자 요청을 처리하는 CommandHandler 인터페이스를 구현하여
// 근태 항목의 추가, 수정, 삭제 작업을 담당합니다.
// AttendanceItemHandlerは、CommandHandlerインターフェースを実装し、ユーザーリクエストを処理して
// 勤怠項目の追加、修正、削除を担当します。

public class AttendanceItemHandler implements CommandHandler {
	// 근태 항목 관련 비즈니스 로직을 처리하는 서비스 객체 생성.
	// 勤怠項目に関連するビジネスロジックを処理するサービスオブジェクトを生成。
	private AttendanceItemService attendanceItemService = new AttendanceItemService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		// 사용자가 요청한 action 파라미터 값을 가져옴.
		// 勤怠項目に関連するビジネスロジックを処理するサービスオブジェクトを生成。
		String action = req.getParameter("action");

		// 근태 항목 추가 기능.
		// 勤怠項目の追加機能。
		if ("add".equals(action)) {
			// 요청 파라미터에서 새 근태 항목의 정보를 가져옴.
			// リクエストパラメーターから新しい勤怠項目の情報を取得。
			String name = req.getParameter("name");
			String unit = req.getParameter("unit");
			String groupCategory = req.getParameter("groupCategory");
			String useFlag = req.getParameter("useFlag");

			// 새 근태 항목 객체 생성 및 추가.
			// 新しい勤怠項目オブジェクトを生成し追加。
			AttendanceItem item = new AttendanceItem(0, name, unit, groupCategory, useFlag);
			attendanceItemService.addAttendanceItem(item);

			// 요청에 성공 메시지를 추가.
			// リクエストに成功メッセージを追加。
			req.setAttribute("message", "勤怠項目が追加されました。");
		}

		// 근태 항목 수정 폼으로 이동.
		// 勤怠項目の修正フォームに移動。
		if ("edit".equals(action)) {
			// 수정할 항목의 ID를 요청 파라미터에서 가져옴.
			// 修正する項目のIDをリクエストパラメーターから取得。
			int id = Integer.parseInt(req.getParameter("id"));
			AttendanceItem selectedItem = attendanceItemService.getAttendanceItemById(id);

			// 선택된 항목을 요청에 추가하여 수정 폼에서 사용.
			// 選択した項目をリクエストに追加し、修正フォームで使用。
			req.setAttribute("selectedItem", selectedItem);
		}

		// 근태 항목 수정 저장.
		// 勤怠項目の修正保存。
		if ("update".equals(action)) {
			// 수정할 항목의 정보를 요청 파라미터에서 가져옴.
			// 修正する項目の情報をリクエストパラメーターから取得。
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String unit = req.getParameter("unit");
			String groupCategory = req.getParameter("groupCategory");
			String useFlag = req.getParameter("useFlag");

			// 수정된 항목을 업데이트.
			// 修正した項目を更新。
			AttendanceItem updatedItem = new AttendanceItem(id, name, unit, groupCategory, useFlag);
			attendanceItemService.updateAttendanceItem(updatedItem);

			// 요청에 성공 메시지를 추가.
			// リクエストに成功メッセージを追加。
			req.setAttribute("message", "勤怠項目が修正されました。");
		}

		// 근태 항목 삭제.
		// 勤怠項目の削除。
		if ("delete".equals(action)) {
			// 삭제할 항목의 ID를 요청 파라미터에서 가져옴.
			// 削除する項目のIDをリクエストパラメーターから取得。
			int id = Integer.parseInt(req.getParameter("id"));
			attendanceItemService.deleteAttendanceItem(id);

			// 요청에 성공 메시지를 추가.
			// リクエストに成功メッセージを追加。
			req.setAttribute("message", "勤怠項目が削除されました。");
		}

		// 최신 근태 항목 목록을 가져와 요청에 추가하여 화면에 표시.
		// 最新の勤怠項目リストを取得し、リクエストに追加して画面に表示。
		List<AttendanceItem> attendanceItems = attendanceItemService.getAttendanceItems();
		req.setAttribute("attendanceItems", attendanceItems);

		// 결과 뷰 페이지로 이동.
		// 結果ビューのページに移動。
		return "/WEB-INF/view/basics/attendanceItem.jsp";
	}
}