package vacation.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import vacation.model.VacationItem;
import vacation.service.VacationItemService;

public class VacationItemHandler implements CommandHandler {
	private VacationItemService vacationItemService = new VacationItemService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		String action = req.getParameter("action");

		// 휴가 항목 추가.
		if ("add".equals(action)) {
			String name = req.getParameter("name");
			String period = req.getParameter("period");
			String useFlag = req.getParameter("useFlag");
			VacationItem item = new VacationItem(0, name, period, useFlag);
			vacationItemService.addVacationItem(item);
			req.setAttribute("message", "휴가 항목이 추가되었습니다.");
		}

		// 휴가 항목 수정 폼으로 이동.
		if ("edit".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			VacationItem selectedItem = vacationItemService.getVacationItemById(id);
			req.setAttribute("selectedItem", selectedItem);
		}

		// 휴가 항목 수정 저장.
		if ("update".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String period = req.getParameter("period");
			String useFlag = req.getParameter("useFlag");
			VacationItem updatedItem = new VacationItem(id, name, period, useFlag);
			vacationItemService.updateVacationItem(updatedItem);
			req.setAttribute("message", "휴가 항목이 수정되었습니다.");
		}

		// 휴가 항목 삭제.
		if ("delete".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			vacationItemService.deleteVacationItem(id);
			req.setAttribute("message", "휴가 항목이 삭제되었습니다.");
		}

		// DB에서 최신 휴가 항목 목록을 다시 가져와 요청에 추가.
		List<VacationItem> vacationItems = vacationItemService.getVacationItems();
		req.setAttribute("vacationItems", vacationItems);

		return "/WEB-INF/view/vacationItem.jsp";
	}
}