package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import salary.model.Employee;
import salary.model.Payment;
import salary.model.Salary;
import salary.model.SalarySpecification;

public class SalaryDao {
	public List<Salary> select(Connection conn, int firstRow, int endRow)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* from\r\n"
					+ " (select * from Employee order by emp_no desc)\r\n"
					+ " a where rownum <= ?) where rnum >= ?");
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			
			rs = pstmt.executeQuery();
			// query 결과 할수있는 방법
			// 1. 급여 따로 emp 따로 2번을 한다.
			// 2. 급여, emp join해서 다 같이 받는다. << 요걸 해야 될듯
			// 그럼 저 밑에서 convert할 때 인자를 두 개 할 필요없이 하나만 해도대겠지
			// 기본적으로 employment랑 join을 하는 query를 만들어야
			
			List<Salary> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertSalary(rs, null)); // employee 결과값 넣을것
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Salary selectById(Connection conn, String id)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("");
			
			rs = pstmt.executeQuery();
			// query 결과 할수있는 방법
			// 1. 급여 따로 emp 따로 2번을 한다.
			// 2. 급여, emp join해서 다 같이 받는다. << 요걸 해야 될듯
			// 그럼 저 밑에서 convert할 때 인자를 두 개 할 필요없이 하나만 해도대겠지
			
			Salary result = convertSalary(rs, rs);
			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Salary convertSalary(ResultSet rs, ResultSet emrs) throws SQLException{
		
		Payment p = new Payment(rs.getInt("salBasicSal"), rs.getInt("salFood"), 
				rs.getInt("salChildCare"), rs.getInt("salPositionSal"), 
				rs.getInt("salLongSer"), rs.getInt("salOncall"), 
				rs.getInt("salBonus"), rs.getInt("salHoliday"));
		
		Employee e = new Employee(emrs.getString("empNum"), emrs.getString("empName"),
				emrs.getString("empForm"), emrs.getString("empDepart"), 
				emrs.getString("empPos"), emrs.getDate("empHireDate"));
		
		return new Salary(rs.getInt("salNum"),	e,  p, d);
	}

	public ArrayList<SalarySpecification> getSpecification(Connection conn, String year, String month) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 해당 월의 인원들 급여 내역 출력
		try {
			pstmt = conn.prepareStatement("select b.empform, b.name, a.salary_emp_no, a.salary_salary, a.salary_food \r\n"
					+ "from salary a,\r\n"
					+ "employee b\r\n"
					+ "where substr(salary_num, 0, 7) = ?\r\n"
					+ "and a.salary_emp_no = b.empno;");
			
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
		return new SalarySpecification(rs.getString("empform"), rs.getString("empname"), rs.getInt("salary_salary") + rs.getInt("salary_food"), rs.getInt("salary_emp_no"));
	}
}
