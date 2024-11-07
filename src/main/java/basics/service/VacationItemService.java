package basics.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import basics.dao.VacationItemDao;
import basics.model.VacationItem;
import jdbc.connection.ConnectionProvider;

// VacationItemService는 VacationItemDao를 사용하여 휴가 항목을 관리하는 비즈니스로직을 처리합니다. 
// VacationItemServiceはVacationItemDaoを使用して休暇項目を管理するビジネスロジックを処理します。

public class VacationItemService {
	private VacationItemDao vacationItemDao = new VacationItemDao();

	// 새로운 휴가 항목을 추가하는 메서드
	// 新しい休暇項目を追加するメソッド
	public void addVacationItem(VacationItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			vacationItemDao.insertVacationItem(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 모든 휴가 항목을 조회하는 메서드
	// すべての休暇項目を取得するメソッド
	public List<VacationItem> getVacationItems() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return vacationItemDao.getAllVacationItems(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 특정 ID의 휴가 항목을 조회하는 메서드
	// 指定されたIDの休暇項目を取得するメソッド
	public VacationItem getVacationItemById(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return vacationItemDao.selectById(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 휴가 항목 업데이트
	// 休暇項目の更新
	public void updateVacationItem(VacationItem item) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			vacationItemDao.update(conn, item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 휴가 항목 삭제
	// 休暇項目の削除
	public void deleteVacationItem(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			vacationItemDao.delete(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}