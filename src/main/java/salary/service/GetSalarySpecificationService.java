package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import salary.dao.SalaryDao;
import salary.model.ItemizedLedger;
import salary.model.Salary;
import salary.model.SalarySpecification;

/*
 * 급여 명세서, 상세 급여 대장 Service
 * 
 * getSalary(String empNo, String year, String month)
 * 사원번호, 년도, 월을 받아 해당 기간 특정 사원의 급여 정보를 리턴하는 매서드
 * 
 * getSalarySpecification(String year, String month)
 * 년도, 월을 받아 해당 기간의 사원들의 급여 정보들을 리턴하는 매서드
 * 
 * getItemLedger(String item, String year)
 * 급여항목, 년도를 받아 해당 년도의 사원들의 특정 급여항목에 대한 월별 정보를 리턴하는 매서드
 * 
 * */

/*
* 給与明細書、詳細給与台帳サービス
*
* getSalary(String empNo, String year, String month)
* 社員番号、年度、月を受けて当該期間特定社員の給与情報をリターンするメソッド
*
* getSalarySpecification(String year, String month)
* 年度、月を受けて当該期間の社員の給与情報をリターンするメソッド
*
* getItemLedger(String item, String year)
* 給与項目、年度を受けて当該年度の社員の特定給与項目に対する月別情報をリターンするメソッド
*
* */

public class GetSalarySpecificationService {
	SalaryDao ledD = new SalaryDao();

	public Salary getSalary(String empNo, String year, String month) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Salary spec = ledD.getSalary(conn, empNo, year, month);
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<SalarySpecification> getSalarySpecification(String year, String month) {
		try(Connection conn = ConnectionProvider.getConnection()){
			ArrayList<SalarySpecification> spec = ledD.getSpecification(conn, year, month);
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}


	public ArrayList<ItemizedLedger> getItemLedger(String item, String year) {
		try(Connection conn = ConnectionProvider.getConnection()){
			ArrayList<ItemizedLedger> spec = ledD.getItemLedger(conn, item, year.substring(2, 4));
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
