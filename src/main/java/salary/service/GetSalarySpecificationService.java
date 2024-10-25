package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import salary.dao.SalaryDao;
import salary.model.ItemizedLedger;
import salary.model.Salary;
import salary.model.SalarySpecification;

public class GetSalarySpecificationService {
	SalaryDao ledD = new SalaryDao(); 
	// 뒤에 salNum을 월 emp에 null 나머지는 합한 값을 넣어서 월별 sal data를 넣으면 어떨까
	
	public int getMonthPaymentTotal(Salary monthMem) {
		return (monthMem.getSalPayment().getSalBasicSalary()
				+ monthMem.getSalPayment().getSalBonus()
				+ monthMem.getSalPayment().getSalChildCare()
				+ monthMem.getSalPayment().getSalFood()
				+ monthMem.getSalPayment().getSalHoliday()
				+ monthMem.getSalPayment().getSalLongService()
				+ monthMem.getSalPayment().getSalOncall()
				+ monthMem.getSalPayment().getSalPositionSalary());
	}
	

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
			ArrayList<ItemizedLedger> spec = ledD.getItemLedger(conn, item, year);
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
