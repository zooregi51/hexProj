package basics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import basics.model.Employee;
import jdbc.JdbcUtil;

// EmployeeDao는 데이터베이스와 상호작용하여 사원 정보를 삽입하고 조회하는 기능을 제공합니다.
// EmployeeDaoは、データベースと連携して従業員情報を挿入および取得する機能を提供します。

public class EmployeeDao {

	// 사원 정보를 데이터베이스에 삽입하는 메서드.
	// 従業員情報をデータベースに挿入するメソッド。
	public int insert(Connection conn, Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			// employee 테이블에 데이터를 삽입하는 쿼리 작성. (emp_seq.NEXTVAL 시퀀스 사용)
			// employeeテーブルにデータを挿入するクエリの作成。(emp_seq.NEXTVALシーケンスを使用)
			String sql = "INSERT INTO Employee (empno, empform, name, hireddate, dep, position, registrationnum, address, phone, email, other, salary) "
					+ "VALUES (emp_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 각 필드에 맞게 Employee 객체에서 데이터를 추출하여 PreparedStatement에 세팅.
			// 各フィールドに適したEmployeeオブジェクトからデータを抽出し、PreparedStatementに設定。
			pstmt.setString(1, emp.getEmpForm());
			pstmt.setString(2, emp.getName());
			pstmt.setTimestamp(3, toTimestamp(emp.getHiredDate())); // Date를 Timestamp로 변환 후 삽입.
			// DateをTimestampに変換して挿入。
			pstmt.setString(4, emp.getDep());
			pstmt.setString(5, emp.getPosition());
			pstmt.setString(6, emp.getRegistrationNum());
			pstmt.setString(7, emp.getAddress());
			pstmt.setString(8, emp.getPhone());
			pstmt.setString(9, emp.getEmail());
			pstmt.setString(10, emp.getOther());

			if (emp.getSalary() != null) {
				pstmt.setInt(11, emp.getSalary());
			} else {
				pstmt.setNull(11, java.sql.Types.INTEGER);
			}

			return pstmt.executeUpdate(); // 쿼리 실행 후 삽입된 행 수 반환.
			// クエリを実行して挿入された行数を返す。
		} finally {
			// 리소스 해제.
			// リソース解放。
			JdbcUtil.close(pstmt);
		}
	}

	// 가장 큰 empNo 값을 조회하는 메서드.
	// 最大のempNo値を取得するメソッド。
	public int getLastEmpNo(Connection conn) throws SQLException {
		String sql = "SELECT MAX(empno) FROM employee";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1); // 가장 큰 empNo 값 반환.
				// 最大のempNo値を返す。
			}
			return 0; // 데이터베이스에 사원이 없을 경우 0 반환.
			// データベースに従業員がいない場合は0を返す。
		}
	}

	// Date를 Timestamp로 변환하는 유틸리티 메서드.
	// DateをTimestampに変換するユーティリティメソッド。
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}