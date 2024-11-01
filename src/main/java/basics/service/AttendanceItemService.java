package basics.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import basics.dao.AttendanceItemDao;
import basics.model.AttendanceItem;
import jdbc.connection.ConnectionProvider;

public class AttendanceItemService {
	private AttendanceItemDao attendanceItemDao = new AttendanceItemDao();

	// 새로운 근태 항목을 추가하는 메서드.
	public void addAttendanceItem(AttendanceItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.insertAttendanceItem(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 모든 근태 항목을 조회하는 메서드.
	public List<AttendanceItem> getAttendanceItems() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return attendanceItemDao.getAllAttendanceItems(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 특정 ID의 근태 항목을 조회하는 메서드.
	public AttendanceItem getAttendanceItemById(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return attendanceItemDao.selectById(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 근태 항목 업데이트.
	public void updateAttendanceItem(AttendanceItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.update(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 근태 항목 삭제.
	public void deleteAttendanceItem(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.delete(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}