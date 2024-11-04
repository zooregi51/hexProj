package basics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basics.model.AttendanceItem;
import basics.model.VacationItem;

public class AttendanceItemDao {

	// 근태 항목을 추가하는 메서드.
		public int insertAttendanceItem(Connection conn, AttendanceItem item) throws SQLException {
			String sql = "INSERT INTO AttendanceItem_item (id, name, unit, group, vacation, use_flag) "
					+ "VALUES (vacation_item_seq.NEXTVAL, ?, ?, ?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, item.getName());
				pstmt.setString(2, item.getUnit());
				pstmt.setString(3, item.getGroup());
				pstmt.setString(4, item.getVacation().getName());
				pstmt.setString(5, item.getUseFlag());
				return pstmt.executeUpdate();
			}
		}

		// 특정 ID에 해당하는 근태 항목을 조회하는 메서드.
		public AttendanceItem selectById(Connection conn, int id) throws SQLException {
			String sql = "SELECT id, name, period, use_flag FROM vacation_item WHERE id = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return new AttendanceItem(rs.getInt("id"), rs.getString("name"), rs.getString("period"),
								rs.getString("use_flag"));
					}
					return null;
				}
			}
		}

		// 근태 항목 목록을 조회하는 메서드.
		public List<AttendanceItem> getAllAttendanceItems(Connection conn) throws SQLException {
			String sql = "SELECT id, name, unit, group, vacation, use_flag FROM attendance_item";
			List<AttendanceItem> items = new ArrayList<>();
			try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					items.add(new AttendanceItem(rs.getInt("id"), rs.getString("name"), rs.getString("unit"), rs.getString("group"),
							rs.getString("vacation().getName"), rs.getString("use_flag")));
				}
			}
			return items;
		}

		// 근태 항목 업데이트.
		public void update(Connection conn, AttendanceItem item) throws SQLException {
			String sql = "UPDATE attendance_item SET name = ?, unit = ?, group = ?, vacation = ?, use_flag = ? WHERE id = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, item.getName());
				pstmt.setString(2, item.getUnit());
				pstmt.setString(3, item.getGroup());
				pstmt.setString(4, item.getVacation().getName());
				pstmt.setString(5, item.getUseFlag());
				pstmt.executeUpdate();
			}
		}

		// 근태 항목 삭제.
		public void delete(Connection conn, int id) throws SQLException {
			String sql = "DELETE FROM attendance_item WHERE id = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			}
		}
	}
