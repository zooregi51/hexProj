package basics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basics.model.AttendanceItem;

public class AttendanceItemDao {

	public int insertAttendanceItem(Connection conn, AttendanceItem item) throws SQLException {
		String sql = "INSERT INTO attendance_item (id, name, unit, group_category, use_flag) VALUES (attendance_item_seq.NEXTVAL, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getUnit());
			pstmt.setString(3, item.getGroupCategory());
			pstmt.setString(4, item.getUseFlag());
			return pstmt.executeUpdate();
		}
	}

	public AttendanceItem selectById(Connection conn, int id) throws SQLException {
		String sql = "SELECT id, name, unit, group_category, use_flag FROM attendance_item WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new AttendanceItem(rs.getInt("id"), rs.getString("name"), rs.getString("unit"),
							rs.getString("group_category"), rs.getString("use_flag"));
				}
				return null;
			}
		}
	}

	public List<AttendanceItem> getAllAttendanceItems(Connection conn) throws SQLException {
		String sql = "SELECT id, name, unit, group_category, use_flag FROM attendance_item";
		List<AttendanceItem> items = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				items.add(new AttendanceItem(rs.getInt("id"), rs.getString("name"), rs.getString("unit"),
						rs.getString("group_category"), rs.getString("use_flag")));
			}
		}
		return items;
	}

	public void update(Connection conn, AttendanceItem item) throws SQLException {
		String sql = "UPDATE attendance_item SET name = ?, unit = ?, group_category = ?, use_flag = ? WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getUnit());
			pstmt.setString(3, item.getGroupCategory());
			pstmt.setString(4, item.getUseFlag());
			pstmt.setInt(5, item.getId());
			pstmt.executeUpdate();
		}
	}

	public void delete(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM attendance_item WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}
}