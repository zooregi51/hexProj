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

/*
 * 급여관리 서비스
 * 
 * getSalary(String empNo, String year, String month)
 * 사원번호, 년도, 달을 받아 해당 기간의 특정 사원의 급여정보를 리턴하는 메서드
 * 
 * 
 * getEmployee(int empno)
 * 사원번호를 받아 해당 사원번호에 해당되는 사원 정보를 리턴하는 메서드
 * 
 * getEmployeeList(String year, String month)
 * 연도, 달을 받아 해당 기간에 급여 정보가 없는 사원들의 목록을 리턴하는 메서드
 * 
 * insertSalary(Salary s)
 * 급여 정보를 받아 DB에 급여 정보를 입력하는 메서드
 * 
 * updateSalary(Salary s)
 * 급여 정보를 받아 DB에 급여 정보를 업데이트 하는 메서드
 * 
 * */

/*
* 給与管理サービス
*
* getSalary(String empNo, String year, String month)
* 社員番号、年度、月を受けて当該期間の特定社員の給与情報をリターンするメソッド
*
*
* getEmployee(int empno)
* 社員番号を受け取り、当該社員番号に該当する社員情報をリターンするメソッド
*
* getEmployeeList(String year, String month)
* 年度、月を受けて当該期間に給与情報がない社員のリストをリターンするメソッド
*
* insertSalary(Salary s)
* 給与情報を受け取りDBに給与情報を入力するメソッド
*
* updateSalary(Salary s)
* 給与情報を受け取りDBに給与情報をアップデートするメソッド
*
* */

public class SalaryManageService {
	SalaryDao ledD = new SalaryDao();
	

	public Salary getSalary(String empNo, String year, String month) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Salary spec = ledD.getSalary(conn, empNo, year, month);
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


	public ArrayList<Employee> getEmployeeList(String year, String month) {
		try(Connection conn = ConnectionProvider.getConnection()){
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
