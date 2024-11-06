package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import salary.dao.SalaryLedgerDao;
import salary.model.Salary;
import salary.model.SalaryLedgerMonth;

/*
 * 급여대장 Service
 * 
 * getYearLedgerMonth(int year)
 * 연도를 받아 해당 연도의 급여 대장 리스트를 리턴하는 매서드
 * 
 * getDetailLedger(String yearMonth)
 * 연+달을 받아 해당 기간의 사원들의 세부 급여 내역 리스트를 리턴하는 매서드
 * 
 * */

/*
* 給与台帳サービス
*
* getYearLedgerMonth(int year)
* 年度を受けて当該年度の給与台帳リストをリターンするマッサッド
*
* getDetailLedger(String yearMonth)
* 年+月を受け取り、該当期間の社員の詳細給与内訳リストをリターンするメソッド
*
* */

public class GetSalaryLedgerService {
	SalaryLedgerDao ledD = new SalaryLedgerDao(); 
	
	// 년도를 받아 특정 년도의 급여대장을 가져옴
	public ArrayList<SalaryLedgerMonth> getYearLedgerMonth(int year){
		try(Connection conn = ConnectionProvider.getConnection()){
			ArrayList<SalaryLedgerMonth> ledgerMonth = ledD.selectLedgerMonth(conn, year);
			
			return ledgerMonth;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 년도 + 달 String을 받아 해당 기간 사원들의 세부 급여대장을 가져옴
	public ArrayList<Salary> getDetailLedger(String yearMonth){
		
		try(Connection conn = ConnectionProvider.getConnection()){
			ArrayList<Salary> monthMem = ledD.selectLedgerDetail(conn, yearMonth);
			return monthMem;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
