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

public class SalaryDao {
	private Salary convertSalary(ResultSet rs) throws SQLException{		
		return new Salary(rs.getString("salary_num"),
				new Employee(null, 
						rs.getString("name"), 
						null, 
						rs.getString("dep"), 
						null, 
						null, 
						rs.getInt("salary_salary")), 
				new Payment(rs.getInt("salary_salary"), 
						rs.getInt("salary_food"), 
						null, 
						null, 
						null, 
						null, 
						null, 
						null),
				rs.getString("salary_transfer_date"));
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
						rs.getInt("salary_holiday")),
				rs.getString("salary_transfer_date").substring(0, 11));
	}

	public ArrayList<SalarySpecification> getSpecification(Connection conn, String year, String month) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("select "
					+ "b.empform as empform, "
					+ "b.name as name, "
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
			
			if(month.length() == 1) {
				month = "0" + month;
			}
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
				rs.getInt("bonus") + rs.getInt("holiday")				
				, rs.getInt("empno"));
	}

	public ArrayList<Salary> getTransferNeeded(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("SELECT a.salary_num, b.name, b.dep, "
					+ "a.salary_salary, a.salary_food, a.salary_transfer_date "
					+ "FROM salary a, "
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
			pstmt = conn.prepareStatement("SELECT a.salary_num, b.name, b.dep, a.salary_salary, a.salary_food, a.salary_transfer_date "
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

	public ArrayList<ItemizedLedger> getItemLedger(Connection conn, String item, String year) throws SQLException {
		String stmt = "select b.empform, b.name, b.dep, "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/01' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-01\",  "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/02' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-02\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/03' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-03\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/04' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-04\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/05' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-05\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/06' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-06\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/07' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-07\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/08' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-08\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/09' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-09\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/10' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-10\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/11' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-11\" , "
				+ "(case when substr(a.salary_transfer_date, 1, 5) = '" + year
				+ "/12' then a." + "salary_" + item
				+ " end) as \"20" + year
				+ "-12\"  "
				+ "from salary a, "
				+ "employee b "
				+ "where a.salary_transfer_date is not null "
				+ "and a.salary_emp_no = b.empno "
				+ "and substr(a.salary_transfer_date, 1, 2) = ?";
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
		for(int i = 1; i <= 12; ++i) {
			String now = year + '-';
			if(i < 10)
				now += '0' + i;
			else
				now += i;
			sal.add(rs.getInt(now));
		}
		return new ItemizedLedger(rs.getString("empform"), rs.getString("empname"), rs.getString("dep"), sal);
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

	public Employee getEmployee(Connection conn, int empno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("select * from employee where salary_emp_no = ?");
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
				rs.getString("pos"), rs.getDate("hireddate"), rs.getInt("salary"));
		//(Integer empId, String empName, String empForm, String empDepart, String empPos, Date empHiredDate)
	}

	public ArrayList<Employee> getEmployeeList(Connection conn, String year, String month) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("SELECT distinct e.empform, e.empno, e.name, e.dep, e.pos "
					+ "FROM employee e "
					+ "LEFT JOIN salary s ON e.empno = s.salary_emp_no "
					+ "WHERE s.salary_num IS NULL OR "
					+ "      NOT EXISTS ( "
					+ "          SELECT 1 "
					+ "          FROM salary s2 "
					+ "          WHERE s2.salary_emp_no = e.empno "
					+ "          AND SUBSTR(s2.salary_num, 1, 7) = ?) "
					+ "order by e.empno");
			pstmt.setString(0, year + "-" + month);
			rs = pstmt.executeQuery();
			ArrayList<Employee> result = null;
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
			pstmt.setString(0, s.getSalNum());
			pstmt.setInt(1, s.getEmployee().getEmpId());
			pstmt.setInt(2, s.getSalPayment().getSalBasicSalary());
			pstmt.setInt(3, s.getSalPayment().getSalFood());
			pstmt.setInt(4, s.getSalPayment().getSalChildCare());
			pstmt.setInt(5, s.getSalPayment().getSalPositionSalary());
			pstmt.setInt(6, s.getSalPayment().getSalLongService());
			pstmt.setInt(7, s.getSalPayment().getSalHoliday());
			pstmt.setInt(8, s.getSalPayment().getSalBonus());
			
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
					+ "salary_continuos_service = ? "
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
