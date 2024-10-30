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
			//사원 전체 수를 세는 쿼리
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
	public int selectCountHired(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee where retireddate Is null");
			//재직자 수를 세는 쿼리
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
	public int selectCountPermanent(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee where empform = '정규직'");
			//정규직 수를 세는 쿼리
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
	public int selectCountContract(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee where empform = '계약직'");
			//계약직 수를 세는 쿼리
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
	public int selectCountTemporary(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee where empform = '임시직'");
			//임시직 수를 세는 쿼리
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
	public int selectCountDispatched(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee where empform = '파견직'");
			//파견직 수를 세는 쿼리
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
	public int selectCountCommissioned(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee where empform = '위촉직'");
			//위촉직 수를 세는 쿼리
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
	public int selectCountDailyJob(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee where empform = '일용직'");
			//일용직 수를 세는 쿼리
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
	public int selectCountRetired(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from employee where retireddate Is not null");
			//퇴사자 수를 세는 쿼리
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
	public List<Employee> select(Connection conn,int firstRow, int endRow,String searchForm)throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			if(searchForm.equals("재직자")){
				pstmt = conn.prepareStatement("select * from(select rownum as rnum,a.*from" + " (select * from employee where retireddate is null order by empno desc)" + " a where rownum <= ?)where rnum >= ?");
			}else if(searchForm.equals("퇴직자")) {
				pstmt = conn.prepareStatement("select * from(select rownum as rnum,a.*from" + " (select * from employee where retireddate is not null order by empno desc)" + " a where rownum <= ?)where rnum >= ?");		
			}else {
				pstmt = conn.prepareStatement("select * from(select rownum as rnum,a.*from" + " (select * from employee where empform='"+searchForm+"' order by empno desc)" + " a where rownum <= ?)where rnum >= ?");
			}
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
