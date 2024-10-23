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
			pstmt = conn.prepareStatement("select * from(select rownum as rnum,a.*from" + " (select * from article order by article_no desc)" + " a where rownum <= ?)where rnum >= ?");
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
		return new Employee(rs.getInt("employee_Id"),rs.getString("employee_Form"),rs.getString("employee_KrName"),rs.getString("employee_EnName"),rs.getDate("employee_Date"),rs.getDate("employee_ResignDate"),rs.getString("employee_Department"),rs.getString("employee_Position"),rs.getDate("employee_ResidentNumber"),rs.getString("employee_Address"),rs.getInt("employee_HomeNumber"),rs.getInt("employee_MobileNumber"),rs.getString("employee_Email"),rs.getString("employee_Sns"),rs.getString("employee_Ex"
				));
	}
	public Employee selectById(Connection conn,int no)throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement("select * from employee where employee_Id=?");
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			Employee employee=null;
			if(rs.next()) {
				employee = new Employee(rs.getInt("employee_Id"),rs.getString("employee_Form"),rs.getString("employee_KrName"),rs.getString("employee_EnName"),rs.getDate("employee_Date"),rs.getDate("employee_ResignDate"),rs.getString("employee_Department"),rs.getString("employee_Position"),rs.getDate("employee_ResidentNumber"),rs.getString("employee_Address"),rs.getInt("employee_HomeNumber"),rs.getInt("employee_MobileNumber"),rs.getString("employee_Email"),rs.getString("employee_Sns"),rs.getString("employee_Ex"));
			}
			return employee;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// : handle finally clause
		}
	}
}
