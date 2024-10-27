package employee.dao;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import employee.model.*;
import jdbc.JdbcUtil;

public class EmployeeDao {

	
	public int selectCount(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			// TODO: handle finally clause
		}
	}
	
	public List<Employee> select(Connection conn,int firstRow, int endRow)throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement("select * from(select rownum as rnum,a.*from" + " (select * from employee order by empno desc)" + " a where rownum <= ?)where rnum >= ?");
			pstmt.setInt(1,endRow);
			pstmt.setInt(2,firstRow);
			rs=pstmt.executeQuery();
			List<Employee> result=new ArrayList<>();
			while(rs.next()) {
				result.add(convertEmployee(rs));
			}
			return result;
		} finally {
			// TODO: handle finally clause
		}
	}
	private Employee convertEmployee(ResultSet rs)throws SQLException{
		return new Employee(rs.getInt("empno"),rs.getString("empform"),rs.getString("name"),rs.getDate("hireddate"),rs.getDate("retireddate"),rs.getString("dep"),rs.getString("position"),rs.getString("registrationnum"),rs.getString("address"),rs.getString("phone"),rs.getString("email"),rs.getString("other"),rs.getInt("salary")
				);
	}
	public Employee selectById(Connection conn,int no)throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement("select * from employee where empno=?");
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			Employee employee=null;
			if(rs.next()) {
				employee = convertEmployee(rs);
			}
			return employee;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// : handle finally clause
		}
	}
}
