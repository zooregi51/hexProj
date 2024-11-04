package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import salary.dao.SalaryDao;
import salary.model.Salary;
import salary.model.SalarySpecification;

/*
 * 급여 이체, 이체 확인 서비스
 * 
 * getEmpsTransferNeeded()
 * 이체가 필요한 사원 리스트를 리턴 시켜주는 메서드
 * 
 * transferSal()
 * 이체가 필요한 사원들을 이체 후 transferDate에 
 * 오늘 날짜를 update 후 그 숫자를 리턴하는 메서드
 * 
 * getTransferedSalary(String startDate, String endDate)
 * 시작 날짜와 끝 날짜를 받아서 해당 기간의 
 * 이체된 급여 목록을 리턴하는 메서드
 * 
 * */

/*
* 給与振替、振替確認サービス
*
* getEmpsTransferNeeded()
* 振替が必要な社員リストをリターンさせるメソッド
*
* transferSal()
* 振替が必要な社員を振替後、transferDateに
* 今日の日付をupdateした後、その数字をリターンするメソッド
*
* getTransferedSalary(String startDate, String endDate)
* 開始日と終了日を受けて、当該期間の
* 振り替えられた給与リストをリターンするメソッド
*
* */


public class SalaryTransferService {
	SalaryDao ledD = new SalaryDao();
	

	public ArrayList<Salary> getEmpsTransferNeeded() {
		try(Connection conn = ConnectionProvider.getConnection()){
			ArrayList<Salary> spec = ledD.getTransferNeeded(conn);
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public int transferSal() {
		try(Connection conn = ConnectionProvider.getConnection()){
			int spec = ledD.setTransferDate(conn);
			System.out.println(spec);
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public ArrayList<Salary> getTransferedSalary(String startDate, String endDate) {
		try(Connection conn = ConnectionProvider.getConnection()){
			ArrayList<Salary> spec = ledD.getTransfered(conn, startDate, endDate);
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
