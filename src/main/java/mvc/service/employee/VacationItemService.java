package mvc.service.employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import mvc.dao.employee.VacationItemDao;
import mvc.model.employee.VacationItem;

public class VacationItemService {
	private VacationItemDao vacationItemDao = new VacationItemDao();

	// 새로운 휴가 항목을 추가하는 메서드 (VacationItem 객체를 인수로 받음)
	public void addVacationItem(VacationItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			vacationItemDao.insertVacationItem(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 모든 휴가 항목을 조회하는 메서드
	public List<VacationItem> getVacationItems() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return vacationItemDao.getAllVacationItems(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 특정 ID의 휴가 항목을 조회하는 메서드
	public VacationItem getVacationItemById(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return vacationItemDao.selectById(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 휴가 항목 업데이트
	public void updateVacationItem(VacationItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			vacationItemDao.update(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 휴가 항목 삭제
	public void deleteVacationItem(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			vacationItemDao.delete(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}