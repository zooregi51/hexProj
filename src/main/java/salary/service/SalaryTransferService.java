package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import salary.dao.SalaryDao;
import salary.model.Salary;
import salary.model.SalarySpecification;

public class SalaryTransferService {
	SalaryDao ledD = new SalaryDao(); 
	// 뒤에 salNum을 월 emp에 null 나머지는 합한 값을 넣어서 월별 sal data를 넣으면 어떨까
	

	public ArrayList<Salary> getSalary() {
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
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
