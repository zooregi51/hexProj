package certificate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import certificate.model.Certificate;
import employee.model.Employee;
import jdbc.JdbcUtil;

public class CertificateDao {
	public int selectCount(Connection conn)throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select count(*) from certificate");
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
	public List<Certificate> select(Connection conn,int firstRow, int endRow)throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement("select * from(select rownum as rnum,a.*from" + " (select * from article order by article_no desc)" + " a where rownum <= ?)where rnum >= ?");
			pstmt.setInt(1,endRow);
			pstmt.setInt(2,firstRow);
			rs=pstmt.executeQuery();
			List<Certificate> result=new ArrayList<>();
			while(rs.next()) {
				result.add(convertCertificate(rs));
			}
			return result;
		} finally {
			// TODO: handle finally clause
		}
	}
	private Certificate convertCertificate(ResultSet rs)throws SQLException{
		return new Certificate(rs.getInt("certificate_Id"),rs.getString("certificate_Register"),rs.getString("certificate_Purpose"),rs.getInt("employee_Id"),rs.getString("employee_Form"),rs.getString("employee_KrName"),rs.getString("employee_Department"),rs.getString("employee_Position"),rs.getDate("certificate_Date")
				);
	}
}
