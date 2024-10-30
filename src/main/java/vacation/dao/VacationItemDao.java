package vacation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vacation.model.VacationItem;

public class VacationItemDao {

	// 휴가 항목을 추가하는 메서드.
	public int insertVacationItem(Connection conn, VacationItem item) throws SQLException {
		String sql = "INSERT INTO vacation_item (id, name, period, use_flag) "
				+ "VALUES (vacation_item_seq.NEXTVAL, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getPeriod());
			pstmt.setString(3, item.getUseFlag());
			return pstmt.executeUpdate();
		}
	}

	// 특정 ID에 해당하는 휴가 항목을 조회하는 메서드.
	public VacationItem selectById(Connection conn, int id) throws SQLException {
		String sql = "SELECT id, name, period, use_flag FROM vacation_item WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new VacationItem(rs.getInt("id"), rs.getString("name"), rs.getString("period"),
							rs.getString("use_flag"));
				}
				return null;
			}
		}
	}

	// 휴가 항목 목록을 조회하는 메서드.
	public List<VacationItem> getAllVacationItems(Connection conn) throws SQLException {
		String sql = "SELECT id, name, period, use_flag FROM vacation_item";
		List<VacationItem> items = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				items.add(new VacationItem(rs.getInt("id"), rs.getString("name"), rs.getString("period"),
						rs.getString("use_flag")));
			}
		}
		return items;
	}

	// 휴가 항목 업데이트.
	public void update(Connection conn, VacationItem item) throws SQLException {
		String sql = "UPDATE vacation_item SET name = ?, period = ?, use_flag = ? WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getPeriod());
			pstmt.setString(3, item.getUseFlag());
			pstmt.setInt(4, item.getId());
			pstmt.executeUpdate();
		}
	}

	// 휴가 항목 삭제.
	public void delete(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM vacation_item WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}
}