package basics.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basics.model.AttendanceItem;
import basics.service.AttendanceItemService;
import mvc.command.CommandHandler;

public class AttendanceItemHandler implements CommandHandler {
	private AttendanceItemService attendanceItemService = new AttendanceItemService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		String action = req.getParameter("action");

		// 근태 항목 추가
		if ("add".equals(action)) {
			String name = req.getParameter("name");
			String unit = req.getParameter("unit");
			String groupCategory = req.getParameter("groupCategory");
			String useFlag = req.getParameter("useFlag");

			AttendanceItem item = new AttendanceItem(0, name, unit, groupCategory, useFlag);
			attendanceItemService.addAttendanceItem(item);
			req.setAttribute("message", "근태 항목이 추가되었습니다.");
		}

		// 근태 항목 수정 폼으로 이동
		if ("edit".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			AttendanceItem selectedItem = attendanceItemService.getAttendanceItemById(id);
			req.setAttribute("selectedItem", selectedItem);
		}

		// 근태 항목 수정 저장
		if ("update".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String unit = req.getParameter("unit");
			String groupCategory = req.getParameter("groupCategory");
			String useFlag = req.getParameter("useFlag");

			AttendanceItem updatedItem = new AttendanceItem(id, name, unit, groupCategory, useFlag);
			attendanceItemService.updateAttendanceItem(updatedItem);
			req.setAttribute("message", "근태 항목이 수정되었습니다.");
		}

		// 근태 항목 삭제
		if ("delete".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			attendanceItemService.deleteAttendanceItem(id);
			req.setAttribute("message", "근태 항목이 삭제되었습니다.");
		}

		// DB에서 최신 근태 항목 목록을 가져와 요청에 추가
		List<AttendanceItem> attendanceItems = attendanceItemService.getAttendanceItems();
		req.setAttribute("attendanceItems", attendanceItems);

		return "/WEB-INF/view/basics/attendanceItem.jsp";
	}
}