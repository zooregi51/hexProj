package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import salary.dao.SalaryDao;
import salary.model.Employee;
import salary.model.ItemizedLedger;
import salary.model.Salary;
import salary.model.SalarySpecification;

public class SalaryManageService {
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


	public Employee getEmployee(int empno) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Employee spec = ledD.getEmployee(conn, empno);
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public ArrayList<Employee> getEmployeeList() {
		try(Connection conn = ConnectionProvider.getConnection()){
			LocalDate now = LocalDate.now();
			String year, month;
			year = now.getYear() + "";
			month = now.getMonth() + "";
			ArrayList<Employee> spec = ledD.getEmployeeList(conn, year, month);
			return spec;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public int insertSalary(Salary s) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int result = ledD.insertSalary(conn, s);
			
			conn.commit();
			return result;
		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}


	public int updateSalary(Salary s) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int result = ledD.updateSalary(conn, s);
			
			conn.commit();
			return result;
		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
