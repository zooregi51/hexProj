package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import salary.model.Employee;
import salary.model.ItemizedLedger;
import salary.model.Payment;
import salary.model.Salary;
import salary.model.SalarySpecification;

/*
 * getSpecification(Connection conn, String year, String month)
 * DB와 연결된 상태 정보, 연도, 달을 받아 DB에 특정 기간에 대한
 * 사원들의 정보와 사원들의 급여 정보를 쿼리문에 대한 결과 값으로 받고
 * 그것을 SalarySpecification 객체로 전환 시켜 생성된 리스트를 반환한다.
 * 
 * convertSpecification(ResultSet rs)
 * 쿼리의 결과 값을 받아 SalarySpecification 객체로 전환시키는 메서드
 * 
 * getTransferNeeded(Connection conn)
 * DB와 연결된 상태 정보를 받아 아직 급여 이체 정보가 null값인 급여 정보를 결과 값으로 받고
 * 그것을 Salary 객체로 전환시켜 생성된 리스트를 반환한다.
 * 
 * setTransferDate(Connection conn)
 * DB와 연결된 상태 정보를 받아 아직 급여 이체 정보가 null값인 급여 정보를 
 * 현재 날짜로 UPDATE 시키는 쿼리문을 실행시키는 매서드
 * 리턴값으로는 몇 개의 급여 정보가 update되었는지에 대한 숫자값
 * 
 * getTransfered(Connection conn, String startDate, String endDate)
 * DB와 연결된 상태 정보, 시작 날짜, 끝 날짜를 받아
 * 해당 기간에 급여 이체 정보가 있는 급여 정보 리스트를 결과 값으로 받고
 * 그것을 Salary 객체로 전환시켜 생성된 리스트를 반환한다.
 * 
 * convertSalary(ResultSet rs)
 * 쿼리의 결과 값을 받아 Salary 객체로 전환시키는 메서드
 * 
 * getItemLedger(Connection conn, String item, String year)
 * DB와 연결된 상태 정보, 급여 항목, 년도를 받아
 * 해당 년도의 모든 사원의 1월 부터 12월까지의 특정 급여 정보를 결과 값으로 받고 
 * 그것을 ItemizedLedger 객체로 전환시켜 생성된 리스트를 반환한다.
 * 
 * convertItemizeLedger(ResultSet rs, String year)
 * 쿼리의 결과 값을 받아 ItemizedLedger 객체로 전환시키는 메서드
 * 
 * getSalary(Connection conn, String empNo, String year, String month)
 * DB와 연결된 상태 정보, 사원 번호, 년도, 월을 입력 받아
 * 해당 기간의 특정 사원의 정보와 급여 정보를 결과 값으로 받고
 * 그것을 Salary 객체로 전환시켜 생성된 객체를 반환한다.
 * 
 * convertSalarySpecificationDetail(ResultSet rs)
 * 쿼리의 결과 값을 받아 Salary 객체로 전환시키는 메서드
 * 
 * getEmployee(Connection conn, int empno)
 * DB와 연결된 상태 정보, 사원 번호를 받아
 * 해당 사원 번호의 사원 정보를 결과 값으로 받고
 * 그것을 Employee 객체로 전환시켜 생성된 객체를 반환한다.
 * 
 * convertEmployee(ResultSet rs)
 * 쿼리의 결과 값을 받아 Employee 객체로 전환시키는 메서드
 * 
 * getEmployeeList(Connection conn, String year, String month)
 * DB와 연결된 상태 정보, 년도, 달을 받아
 * 해당 기간의 급여 정보가 없는 사원 목록을 결과 값으로 받고
 * 그것을 Employee 객체로 전환시켜 생성된 객체를 반환한다.
 * 
 * insertSalary(Connection conn, Salary s)
 * 급여 정보를 받아
 * 해당 정보를 Salary 테이블에 입력하고
 * 몇 개의 쿼리가 입력되었는지에 대한 결과 값을 리턴시키는 메서드
 * 
 * updateSalary(Connection conn, Salary s)
 * 급여 정보를 받아
 * 해당 급여 정보의 급여 번호와 일치하는 급여 번호의 정보를 Salary 테이블에 업데이트하고
 * 몇 개의 쿼리가 변경되었는지에 대한 결과 값을 리턴시키는 메서드
 * 
 * */

/*
* getSpecification(Connection conn, String year, String month)
* DBと接続された状態情報、年度、月を受けてDBに特定期間に対する
* 社員の情報と社員の給与情報をクエリ文に対する結果値として受け取り
* それをSalarySpecificationオブジェクトに切り替えて生成されたリストを返す。
*
* convertSpecification(ResultSet rs)
* クエリーの結果値を受けてSalarySpecificationオブジェクトに切り替えるメソッド
*
* getTransferNeeded(Connection conn)
* DBと連結された状態情報を受け取り、まだ給与振替情報がnull値である給与情報を結果値として受け取り
* それをSalaryオブジェクトに切り替えて生成されたリストを返す。
*
* setTransferDate(Connection conn)
* DBと接続された状態情報を受け取り、まだ給与振替情報がnull値である給与情報を
* 現在の日付でUPDATEさせるクエリ文を実行させるメソッド
* リターン値としては、いくつの給与情報がupdateされたかに対する数値
*
* getTransfered(Connection conn, String startDate, String endDate)
* DBと接続された状態情報、開始日、終了日を受け取る
* 当該期間に給与振替情報がある給与情報リストを結果値として受け取り
* それをSalaryオブジェクトに切り替えて生成されたリストを返す。
*
* convertSalary(ResultSet rs)
* クエリーの結果値を受けてSalaryオブジェクトに切り替えるメソッド
*
* getItemLedger(Connection conn, String item, String year)
* DBと連結された状態情報、給与項目、年度を受けて
* 当該年度の全社員の1月から12月までの特定給与情報を結果値として受け取り
* それをItemized Ledgerオブジェクトに切り替えて生成されたリストを返す。
*
* convertItemizeLedger(ResultSet rs, String year)
* クエリーの結果値を受けてItemized Ledgerオブジェクトに切り替えるメソッド
*
* getSalary(Connection conn, String empNo, String year, String month)
* DBと接続された状態情報、社員番号、年度、月を入力してもらい
* 当該期間の特定社員の情報と給与情報を結果値として受け取り
* それをSalaryオブジェクトに切り替えて生成されたオブジェクトを返す。
*
* convertSalarySpecificationDetail(ResultSet rs)
* クエリーの結果値を受けてSalaryオブジェクトに切り替えるメソッド
*
* getEmployee(Connection conn, int empno)
* DBと接続された状態情報、社員番号を受信し
* 当該社員番号の社員情報を結果値として受け
* それをEmployeeオブジェクトに切り替えて生成されたオブジェクトを返す。
*
* convertEmployee(ResultSet rs)
* クエリーの結果値を受けてEmployeeオブジェクトに切り替えるメソッド
*
* getEmployeeList(Connection conn, String year, String month)
* DBと接続された状態情報、年度、月を受けて
* 当該期間の給与情報がない社員の一覧を結果値として受け取り
* それをEmployeeオブジェクトに切り替えて生成されたオブジェクトを返す。
*
* insertSalary(Connection conn, Salary s)
* 給与情報を受け取り
* 当該情報をSalaryテーブルに入力し
* いくつのクエリが入力されたかの結果値をリターンさせるメソッド
*
* updateSalary(Connection conn, Salary s)
* 給与情報を受け取り
* 当該給与情報の給与番号と一致する給与番号の情報をSalaryテーブルに更新し
* いくつのクエリが変更されたかに対する結果値をリターンさせるメソッド
*
* */


public class SalaryDao {
	
	

	public ArrayList<SalarySpecification> getSpecification(Connection conn, String year, String month) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("select "
					+ "b.empform as empform, "
					+ "b.name as name, "
					+ "b.dep as dep, "
					+ "a.salary_emp_no as empno, "
					+ "a.salary_salary as salary, "
					+ "a.salary_food as food, "
					+ "a.salary_childcare as childcare, "
					+ "a.salary_position_allowance as position, "
					+ "a.salary_continuos_service as continuos, "
					+ "a.salary_nightduty as nightduty, "					
					+ "a.salary_bonus as bonus, "					
					+ "a.salary_holiday as holiday "					
					+ "from salary a, "
					+ "employee b "
					+ "where substr(salary_num, 1, 7) = ? "
					+ "and a.salary_emp_no = b.empno");
			
			pstmt.setString(1, year + '-' + month);
			rs = pstmt.executeQuery();
			ArrayList<SalarySpecification> result = new ArrayList<SalarySpecification>();
			while(rs.next()) {
				result.add(convertSpecification(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private SalarySpecification convertSpecification(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return new SalarySpecification(rs.getString("empform"), 
				rs.getString("name"), 
				rs.getInt("salary") + rs.getInt("food") + 
				rs.getInt("childcare") + rs.getInt("position") + 
				rs.getInt("continuos") + rs.getInt("nightduty") + 
				rs.getInt("bonus") + rs.getInt("holiday"), rs.getString("dep")				
				, rs.getInt("empno"));
	}

	public ArrayList<Salary> getTransferNeeded(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("select * "
					+ "from salary a, "
					+ "employee b "
					+ "where salary_transfer_date is null "
					+ "and a.salary_emp_no = b.empno");
			
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

	public int setTransferDate(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("update salary "
					+ "    set salary_transfer_date = sysdate "
					+ "    where salary_transfer_date is null");
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	public ArrayList<Salary> getTransfered(Connection conn, String startDate, String endDate) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("select * "
					+ "FROM salary a, "
					+ "employee b "
					+ "where a.salary_transfer_date is not null "
					+ "and a.salary_emp_no = b.empno "
					+ "and a.salary_transfer_date >= ? "
					+ "and a.salary_transfer_date <= ?");
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
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
	
	private Salary convertSalary(ResultSet rs) throws SQLException{		
		return new Salary(rs.getString("salary_num"),
				new Employee(rs.getInt("empno"), 
						rs.getString("name"), 
						rs.getString("empform"), 
						rs.getString("dep"), 
						rs.getString("position"), 
						rs.getDate("hireddate"), 
						rs.getInt("salary")), 
				new Payment(rs.getInt("salary_salary"), 
						rs.getInt("salary_food"), 
						rs.getInt("salary_childcare"), 
						rs.getInt("salary_position_allowance"), 
						rs.getInt("salary_continuos_service"), 
						rs.getInt("salary_nightduty"), 
						rs.getInt("salary_bonus"), 
						rs.getInt("salary_holiday")),
				rs.getString("salary_transfer_date"));
	}

	public ArrayList<ItemizedLedger> getItemLedger(Connection conn, String item, String year) throws SQLException {
		String stmt = "SELECT "
    + "b.empno AS empno, "
    + "b.empform AS empform, "
    + "b.name AS name, "
    + "b.dep AS dep, "
    + "b.position AS pos, "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-01' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-01\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-02' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-02\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-03' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-03\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-04' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-04\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-05' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-05\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-06' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-06\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-07' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-07\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-08' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-08\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-09' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-09\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-10' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-10\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-11' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-11\", "
    + "SUM(CASE WHEN substr(a.salary_num, 3, 5) = '" + year + "-12' THEN a.salary_" + item + " ELSE 0 END) AS \"20" + year + "-12\" "
    + "FROM salary a "
    + "JOIN employee b ON a.salary_emp_no = b.empno "
    + "WHERE "
    + "    a.salary_transfer_date IS NOT NULL "
    + "    AND substr(a.salary_num, 3, 2) = ? "
    + "GROUP BY "
    + "    b.empno, b.empform, b.name, b.dep, b.position "
    + "ORDER BY "
    + "    b.empno";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement(stmt);
			pstmt.setString(1, year);
			rs = pstmt.executeQuery();
			ArrayList<ItemizedLedger> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertItemizeLedger(rs, year));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private ItemizedLedger convertItemizeLedger(ResultSet rs, String year) throws SQLException {
		ArrayList<Integer> sal = new ArrayList<>();
		year = "20" + year;
		for(int i = 1; i <= 12; ++i) {
			String now = year + '-';
			if(i < 10)
				now += "0" + i;
			else
				now += i;
			sal.add(rs.getInt(now));
		}
		return new ItemizedLedger(rs.getString("empform"), rs.getString("name"), rs.getString("dep"), sal, rs.getString("pos"));
	}

	public Salary getSalary(Connection conn, String empNo, String year, String month) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("select * "
					+ "from salary a, "
					+ "employee b "
					+ "where salary_emp_no = ? "
					+ "and salary_emp_no = b.empno "
					+ "and substr(salary_num, 1, 7) = ?");
			pstmt.setInt(1, Integer.parseInt(empNo));
			if(month.length() == 1) {
				month = "0" + month;
			}
			pstmt.setString(2, year + "-" + month);
			rs = pstmt.executeQuery();
			Salary result = null;
			while(rs.next()) {
				result = convertSalarySpecificationDetail(rs);
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Salary convertSalarySpecificationDetail(ResultSet rs) throws SQLException{		
		return new Salary(rs.getString("salary_num"),
				new Employee(rs.getInt("empno"), 
						rs.getString("name"), 
						rs.getString("empform"), 
						rs.getString("dep"), 
						rs.getString("position"), 
						rs.getDate("hireddate"), 
						rs.getInt("salary")), 
				new Payment(rs.getInt("salary_salary"), 
						rs.getInt("salary_food"), 
						rs.getInt("salary_childcare"), 
						rs.getInt("salary_position_allowance"), 
						rs.getInt("salary_continuos_service"), 
						rs.getInt("salary_nightduty"), 
						rs.getInt("salary_bonus"), 
						rs.getInt("salary_holiday")));
	}

	public Employee getEmployee(Connection conn, int empno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("select * from employee where empno = ?");
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			Employee result = null;
			while(rs.next()) {
				result = convertEmployee(rs);
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Employee convertEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getInt("empno"), rs.getString("name"), rs.getString("empform"), rs.getString("dep"), 
				rs.getString("position"), rs.getDate("hireddate"), rs.getInt("salary"));
		//(Integer empId, String empName, String empForm, String empDepart, String empPos, Date empHiredDate)
	}

	public ArrayList<Employee> getEmployeeList(Connection conn, String year, String month) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("SELECT distinct empform, empno, name, dep, position, hireddate, salary "
					+ "FROM employee "
					+ "LEFT JOIN salary ON empno = salary_emp_no "
					+ "WHERE salary_num IS NULL OR "
					+ "      NOT EXISTS ( "
					+ "          SELECT 1 "
					+ "          FROM salary s "
					+ "          WHERE s.salary_emp_no = empno "
					+ "          AND SUBSTR(s.salary_num, 1, 7) = ?) "
					+ "order by empno");
			pstmt.setString(1, year + "-" + month);
			rs = pstmt.executeQuery();
			ArrayList<Employee> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertEmployee(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int insertSalary(Connection conn, Salary s) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into salary(salary_num, salary_emp_no, salary_salary, salary_food, salary_childcare, "
					+ "salary_position_allowance, salary_continuos_service, salary_holiday, salary_bonus) values "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, s.getSalNum());
			pstmt.setInt(2, s.getEmployee().getEmpId());
			pstmt.setInt(3, s.getSalPayment().getSalBasicSalary());
			pstmt.setInt(4, s.getSalPayment().getSalFood());
			pstmt.setInt(5, s.getSalPayment().getSalChildCare());
			pstmt.setInt(6, s.getSalPayment().getSalPositionSalary());
			pstmt.setInt(7, s.getSalPayment().getSalLongService());
			pstmt.setInt(8, s.getSalPayment().getSalHoliday());
			pstmt.setInt(9, s.getSalPayment().getSalBonus());
			
			return pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);			
		}
	}

	public int updateSalary(Connection conn, Salary s) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update salary "
					+ "set "
					+ "salary_salary = ?, "
					+ "salary_food = ?, "
					+ "salary_childcare = ?, "
					+ "salary_position_allowance = ?, "
					+ "salary_continuos_service = ?, "
					+ "salary_holiday = ?, "
					+ "salary_bonus = ? "
					+ "where "
					+ "salary_num = ?");
			pstmt.setInt(1, s.getSalPayment().getSalBasicSalary());
			pstmt.setInt(2, s.getSalPayment().getSalFood());
			pstmt.setInt(3, s.getSalPayment().getSalChildCare());
			pstmt.setInt(4, s.getSalPayment().getSalPositionSalary());
			pstmt.setInt(5, s.getSalPayment().getSalLongService());
			pstmt.setInt(6, s.getSalPayment().getSalHoliday());
			pstmt.setInt(7, s.getSalPayment().getSalBonus());
			pstmt.setString(8, s.getSalNum());
			
			return pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);			
		}
	}
}
