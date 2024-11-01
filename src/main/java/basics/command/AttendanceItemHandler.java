package basics.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basics.model.VacationItem;
import basics.service.VacationItemService;
import mvc.command.CommandHandler;

public class AttendanceItemHandler implements CommandHandler {
	private AttendanceItemService attendanceItemService = new AttendanceItemService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		String action = req.getParameter("action");

		// 휴가 항목 추가.
		if ("add".equals(action)) {
			String name = req.getParameter("name");
			String period = req.getParameter("period");
			String useFlag = req.getParameter("useFlag");
			VacationItem item = new VacationItem(0, name, period, useFlag);
			attendanceItemService.addAttendanceItem(item);
			req.setAttribute("message", "휴가 항목이 추가되었습니다.");
		}

		// 휴가 항목 수정 폼으로 이동.
		if ("edit".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			VacationItem selectedItem = attendanceItemService.getAttendanceItemById(id);
			req.setAttribute("selectedItem", selectedItem);
		}

		// 휴가 항목 수정 저장.
		if ("update".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String period = req.getParameter("period");
			String useFlag = req.getParameter("useFlag");
			VacationItem updatedItem = new VacationItem(id, name, period, useFlag);
			attendanceItemService.updateAttendanceItem(updatedItem);
			req.setAttribute("message", "휴가 항목이 수정되었습니다.");
		}

		// 휴가 항목 삭제.
		if ("delete".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			attendanceItemService.deleteAttendanceItem(id);
			req.setAttribute("message", "휴가 항목이 삭제되었습니다.");
		}

		// DB에서 최신 휴가 항목 목록을 다시 가져와 요청에 추가.
		List<AttendanceItem> attendanceItems = attendanceItemService.getAttendanceItems();
		req.setAttribute("attendanceItems", attendanceItems);

		return "/WEB-INF/view/basics/vacationItem.jsp";
	}
}