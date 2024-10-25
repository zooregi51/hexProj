package mvc.dao.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import jdbc.JdbcUtil;
import mvc.model.employee.Employee;

public class EmployeeDao {

	// 사원 정보를 데이터베이스에 삽입하는 메서드
	public int insert(Connection conn, Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			// employee 테이블에 데이터를 삽입하는 쿼리 작성 (emp_seq.NEXTVAL 시퀀스 사용)
			String sql = "INSERT INTO Employee (empno, empform, name, hireddate, dep, position, registrationnum, address, phone, email, other, salary) "
					+ "VALUES (emp_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 각 필드에 맞게 Employee 객체에서 데이터를 추출하여 PreparedStatement에 세팅.
			pstmt.setString(1, emp.getEmpForm());
			pstmt.setString(2, emp.getName());
			pstmt.setTimestamp(3, toTimestamp(emp.getHiredDate())); // Date를 Timestamp로 변환 후 삽입
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

			return pstmt.executeUpdate(); // 쿼리 실행 후 삽입된 행 수 반환
		} finally {
			// 리소스 해제
			JdbcUtil.close(pstmt);
		}
	}

	// 가장 큰 empNo 값을 조회하는 메서드
	public int getLastEmpNo(Connection conn) throws SQLException {
		String sql = "SELECT MAX(empno) FROM employee";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1); // 가장 큰 empNo 값 반환
			}
			return 0; // 데이터베이스에 사원이 없을 경우 0 반환
		}
	}

	// Date를 Timestamp로 변환하는 유틸리티 메서드
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}