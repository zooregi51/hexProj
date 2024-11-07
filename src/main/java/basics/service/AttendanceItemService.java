package basics.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import basics.dao.AttendanceItemDao;
import basics.model.AttendanceItem;
import jdbc.connection.ConnectionProvider;

// AttendanceItemService는 AttendanceItemDao를 사용하여 근태 항목에 대한
// 비즈니스 로직을 처리합니다.
// AttendanceItemServiceは、AttendanceItemDaoを使用して勤怠項目に関するビジネスロジックを処理します。


public class AttendanceItemService {
	// 데이터 접근 객체 생성.
	// データアクセスオブジェクトを生成。
	private AttendanceItemDao attendanceItemDao = new AttendanceItemDao();

	// 근태 항목 추가.
	// 勤怠項目の追加。
	public void addAttendanceItem(AttendanceItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.insertAttendanceItem(conn, item);
		} catch (SQLException e) {
			// 예외 처리.
			// 例外処理。
			throw new RuntimeException(e);
		}
	}

	// 모든 근태 항목 가져오기.
	// 全ての勤怠項目を取得。
	public List<AttendanceItem> getAttendanceItems() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return attendanceItemDao.getAllAttendanceItems(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// ID로 근태 항목 조회.
	// IDで勤怠項目を取得。
	public AttendanceItem getAttendanceItemById(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return attendanceItemDao.selectById(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 근태 항목 수정.
	// 勤怠項目の修正。
	public void updateAttendanceItem(AttendanceItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.update(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 근태 항목 삭제.
	// 勤怠項目の削除。
	public void deleteAttendanceItem(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			attendanceItemDao.delete(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}