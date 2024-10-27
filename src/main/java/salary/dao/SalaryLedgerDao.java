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
	public ArrayList<SalaryLedgerMonth> selectLedgerMonth(Connection conn, String year)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select substr(salary_num, 1, 7) as yearmonth, count(*) as empcnt, max(salary_transfer_date) as transfer_date, "
					+ "sum(salary_salary) as salary, sum(salary_food) as food, "
					+ "sum(salary_childcare) as childcare, sum(salary_position_allowance) as position_allowance, sum(salary_continuos_service) as continuos_service"
					+ ", sum(salary_nightduty) as nightduty, "
					+ "sum(salary_bonus) as bonus, sum(salary_holiday) as holiday from salary  "
					+ "where substr(salary_num, 1, 4) = ? "
					+ "group by substr(salary_num, 1, 7) "
					+ "order by substr(salary_num, 1, 7)");
			
			pstmt.setString(1, year);
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
		return new SalaryLedgerMonth(rs.getString("yearmonth"), rs.getString("yearmonth"), 
				rs.getDate("transfer_date"), rs.getInt("empcnt"), 
				rs.getInt("salary") + rs.getInt("food") + rs.getInt("childcare") + 
				rs.getInt("position_allowance") + rs.getInt("continuos_service") 
				+ rs.getInt("nightduty") + rs.getInt("bonus") + 
				rs.getInt("holiday"));
	}

	public ArrayList<Salary> selectLedgerDetail(Connection conn, String yearMonth)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			// join하는 구문으로 수정할 필요 있음
			// 구분, 성명, 입사일 부서, 직위
			pstmt = conn.prepareStatement("select a.salary_num as salary_num, b.empform as empform, b.name as name, b.hireddate as hireddate, b.dep as dep, "
					+ "a.salary_emp_no as emp_no, a.salary_salary as salary, a.salary_food as food, "
					+ "a.SALARY_CHILDCARE as childcare, a.SALARY_POSITION_ALLOWANCE as position_allowance, a.SALARY_CONTINUOS_SERVICE as continuos_service, "
					+ "a.SALARY_NIGHTDUTY as nightduty, a.SALARY_BONUS as bonus, a.SALARY_HOLIDAY as holiday "
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
		return new Salary(rs.getString("salary_num"), new Employee(rs.getInt("emp_no"), 
				rs.getString("name"), rs.getString("empform"), rs.getString("dep"), null, rs.getDate("hireddate"), 
				rs.getInt("salary")), 
				new Payment(rs.getInt("salary"), rs.getInt("food"), rs.getInt("childcare"), 
						rs.getInt("position_allowance"), rs.getInt("continuos_service"), 
						rs.getInt("nightduty"), rs.getInt("bonus"), rs.getInt("holiday"))
				, null);
	}
}
