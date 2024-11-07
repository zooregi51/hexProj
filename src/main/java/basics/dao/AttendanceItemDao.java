package basics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basics.model.AttendanceItem;

// AttendanceItemDao는 데이터베이스와 상호작용하여
// 근태 항목을 삽입, 조회, 수정, 삭제하는 기능을 제공합니다.
// AttendanceItemDaoは、データベースと連携して
// 勤怠項目の挿入、取得、修正、削除機能を提供します。

public class AttendanceItemDao {

	// 근태 항목 삽입.
	// 勤怠項目の挿入。
	public int insertAttendanceItem(Connection conn, AttendanceItem item) throws SQLException {
		String sql = "INSERT INTO attendance_item (id, name, unit, group_category, use_flag) VALUES (attendance_item_seq.NEXTVAL, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// 쿼리에 항목 데이터를 설정.
			// クエリに項目データを設定。
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getUnit());
			pstmt.setString(3, item.getGroupCategory());
			pstmt.setString(4, item.getUseFlag());

			// 쿼리 실행.
			// クエリを実行。
			return pstmt.executeUpdate();
		}
	}

	// ID로 특정 근태 항목 조회.
	// IDで特定の勤怠項目を取得。
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

	// 모든 근태 항목 조회.
	// 全ての勤怠項目を取得。
	public List<AttendanceItem> getAllAttendanceItems(Connection conn) throws SQLException {
		String sql = "SELECT id, name, unit, group_category, use_flag FROM attendance_item";
		List<AttendanceItem> items = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				// 조회된 모든 항목을 리스트에 추가.
				// 取得した全ての項目をリストに追加。
				items.add(new AttendanceItem(rs.getInt("id"), rs.getString("name"), rs.getString("unit"),
						rs.getString("group_category"), rs.getString("use_flag")));
			}
		}
		return items;
	}

	// 근태 항목 업데이트.
	// 勤怠項目の更新。
	public void update(Connection conn, AttendanceItem item) throws SQLException {
		String sql = "UPDATE attendance_item SET name = ?, unit = ?, group_category = ?, use_flag = ? WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getUnit());
			pstmt.setString(3, item.getGroupCategory());
			pstmt.setString(4, item.getUseFlag());
			pstmt.setInt(5, item.getId());

			// 쿼리 실행.
			// クエリを実行。
			pstmt.executeUpdate();
		}
	}

	// 근태 항목 삭제.
	// 勤怠項目の削除。
	public void delete(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM attendance_item WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);

			// 쿼리 실행.
			// クエリを実行。
			pstmt.executeUpdate();
		}
	}
}