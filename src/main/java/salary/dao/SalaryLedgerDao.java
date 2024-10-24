package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import salary.model.Employee;
import salary.model.Payment;
import salary.model.Salary;
import salary.model.SalaryLedgerMonth;

public class SalaryLedgerDao {
	public ArrayList<SalaryLedgerMonth> selectLedgerMonth(Connection conn, int year)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select substr(salary_num, 1, 7), count(*), sum(salary_salary), sum(salary_food) from salary  "
					+ "where substr(salary_num, 1, 4) = ? "
					+ "group by substr(salary_num, 1, 7) "
					+ "order by substr(salary_num, 1, 7)");
			
			pstmt.setString(1, year + "");
			rs = pstmt.executeQuery();
			ArrayList<SalaryLedgerMonth> result = new ArrayList<SalaryLedgerMonth>();
			while(rs.next()) {
				result.add(convertLedgerMonth(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private SalaryLedgerMonth convertLedgerMonth(ResultSet rs) throws SQLException {
		return new SalaryLedgerMonth(rs.getString("substr(salary_num, 0, 7)"), rs.getString("substr(salary_num, 0, 7)"), rs.getString("substr(salary_num, 0, 7)") + "-05", rs.getInt("count(*)"), rs.getInt("sum(salary_salary)") + rs.getInt("sum(salary_food)"));
	}

	public ArrayList<Salary> selectLedgerDetail(Connection conn, String yearMonth)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			// join하는 구문으로 수정할 필요 있음
			// 구분, 성명, 입사일 부서, 직위
			pstmt = conn.prepareStatement("select b.empform, b.name, b.hireddate, b.dep, a.salary_emp_no, a.salary_salary, a.salary_food  "
					+ "from salary a, "
					+ "employee b "
					+ "where substr(a.salary_num, 0, 7) = ? "
					+ "and a.salary_emp_no = b.empno");
			
			pstmt.setString(1, yearMonth);
			rs = pstmt.executeQuery();
			ArrayList<Salary> result = new ArrayList<Salary>();
			while(rs.next()) {
				result.add(convertSalary(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Salary convertSalary(ResultSet rs) throws SQLException {
		return new Salary(rs.getString("salary_num"), new Employee(rs.getInt("salary_emp_no"), rs.getString("name"), rs.getString("empform"), rs.getString("dep"), null, rs.getDate("hireddate"), rs.getInt("salary")), new Payment(rs.getInt("salary_salary"), rs.getInt("salary_food"), null, null, null, null, null, null));
	}
}
