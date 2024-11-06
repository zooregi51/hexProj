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

/*
 * selectLedgerMonth(Connection conn, String year)
 * DB와 연결된 상태 정보, 년도를 입력 받아
 * 사원들의 해당 년도의 각 달의 급여 정보의 합을 결과 값으로 받고
 * 그것을 SalaryLedgerMonth라는 객체로 전환 시켜 생성된 객체들의 리스트를 반환한다.
 * 
 * convertLedgerMonth(ResultSet rs)
 * 쿼리의 결과 값을 받아 SalaryLedgerMonth 객체로 전환시키는 메서드
 * 
 * selectLedgerDetail(Connection conn, String yearMonth)
 * DB와 연결된 상태 정보, 년도를 입력 받아
 * 해당 기간의 사원들의 급여 정보들을 결과 값으로 받고
 * 그것을 Salary 객체로 전환시켜 생성된 객체들의 리스트를 반환한다.
 * 
 * convertSalary(ResultSet rs)
 * 쿼리의 결과 값을 받아 Salary 객체로 전환시키는 메서드
 * 
 * */

/*
* selectLedgerMonth(Connection conn, String year)
* DBと接続された状態情報、年を入力してもらい
* 社員の当該年度の各月の給与情報の合計を結果値として受け取り
* それをSalary Ledger Monthというオブジェクトに切り替えて生成されたオブジェクトのリストを返す。
*
* convertLedgerMonth(ResultSet rs)
* クエリーの結果値を受けてSalaryLedgerMonthオブジェクトに切り替えるメソッド
*
* selectLedgerDetail(Connection conn, String yearMonth)
* DBと接続された状態情報、年を入力してもらい
* 当該期間の社員の給与情報を結果値として受け取り
* それをSalaryオブジェクトに切り替えて生成されたオブジェクトのリストを返す。
*
* convertSalary(ResultSet rs)
* クエリーの結果値を受けてSalaryオブジェクトに切り替えるメソッド
*
* */

public class SalaryLedgerDao {
	public ArrayList<SalaryLedgerMonth> selectLedgerMonth(Connection conn, int year)throws SQLException{
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
			
			pstmt.setString(1, Integer.toString(year));
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
