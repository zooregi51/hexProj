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
			pstmt = conn.prepareStatement("select * from(select rownum as rnum,a.*from" + " (select c.certificate_Id,c.certificate_Register,c.certificate_Purpose,b.empno,b.empform,b.name,b.dep,b.position,c.certificate_Date from certificate c,employee b "
					+ "where c.empno = b.empno order by c.certificate_Id desc)" + " a where rownum <= ?)where rnum >= ?");
			pstmt.setInt(1,endRow);
			pstmt.setInt(2,firstRow);
			rs=pstmt.executeQuery();
			List<Certificate> result=new ArrayList<>();
			//조인해서 불러와야함
			while(rs.next()) {
				result.add(convertCertificate(rs));
			}
			return result;
		} finally {
			// TODO: handle finally clause
		}
	}
	private Certificate convertCertificate(ResultSet rs)throws SQLException{
		return new Certificate(rs.getInt("certificate_Id"),rs.getString("certificate_Register"),rs.getString("certificate_Purpose"),rs.getInt("empno"),rs.getString("empform"),rs.getString("name"),rs.getString("dep"),rs.getString("position"),rs.getDate("certificate_Date")
				);
	}
	public Certificate selectById(Connection conn,int no)throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement("select * from certificate where certificate_Id=?");
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			Certificate certificate=null;
			if(rs.next()) {
				certificate=convertCertificate(rs);
				
			}
			return certificate;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
