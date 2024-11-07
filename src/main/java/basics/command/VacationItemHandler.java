package basics.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basics.model.VacationItem;
import basics.service.VacationItemService;
import mvc.command.CommandHandler;

// VacationItemHandler는 휴가 항목을 관리하는 CommandHandler로, 추가, 수정, 삭제 기능을 제공합니다.
// VacationItemHandlerは休暇項目を管理するCommandHandlerであり、追加、修正、削除機能を提供します。

public class VacationItemHandler implements CommandHandler {
	private VacationItemService vacationItemService = new VacationItemService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		String action = req.getParameter("action");

		// 휴가 항목 추가.
		// 休暇項目の追加。
		if ("add".equals(action)) {
			String name = req.getParameter("name");
			String period = req.getParameter("period");
			String useFlag = req.getParameter("useFlag");
			VacationItem item = new VacationItem(0, name, period, useFlag);
			vacationItemService.addVacationItem(item);
			req.setAttribute("message", "休暇項目が追加されました。");
		}

		// 휴가 항목 수정 폼으로 이동.
		// 休暇項目の修正フォームに移動。
		if ("edit".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			VacationItem selectedItem = vacationItemService.getVacationItemById(id);
			req.setAttribute("selectedItem", selectedItem);
		}

		// 휴가 항목 수정 저장.
		// 休暇項目の修正保存。
		if ("update".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String period = req.getParameter("period");
			String useFlag = req.getParameter("useFlag");
			VacationItem updatedItem = new VacationItem(id, name, period, useFlag);
			vacationItemService.updateVacationItem(updatedItem);
			req.setAttribute("message", "休暇項目が修正されました。");
		}

		// 휴가 항목 삭제.
		// 休暇項目の削除。
		if ("delete".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			vacationItemService.deleteVacationItem(id);
			req.setAttribute("message", "休暇項目が削除されました。");
		}

		// DB에서 최신 휴가 항목 목록을 다시 가져와 요청에 추가.
		// データベースから最新の休暇項目リストを取得してリクエストに追加。
		List<VacationItem> vacationItems = vacationItemService.getVacationItems();
		req.setAttribute("vacationItems", vacationItems);

		return "/WEB-INF/view/basics/vacationItem.jsp";
	}
}