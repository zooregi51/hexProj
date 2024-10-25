package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import salary.dao.SalaryLedgerDao;
import salary.model.Salary;
import salary.model.SalaryLedgerMonth;

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
