package basics.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import basics.dao.AttendanceItemDao;
import basics.model.AttendanceItem;
import jdbc.connection.ConnectionProvider;

public class AttendanceItemService {
	private AttendanceItemDao attendanceItemDao = new AttendanceItemDao();

	public void addAttendanceItem(AttendanceItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.insertAttendanceItem(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<AttendanceItem> getAttendanceItems() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return attendanceItemDao.getAllAttendanceItems(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public AttendanceItem getAttendanceItemById(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return attendanceItemDao.selectById(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateAttendanceItem(AttendanceItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.update(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteAttendanceItem(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.delete(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}