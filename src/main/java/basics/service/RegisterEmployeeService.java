package basics.service;

import java.sql.Connection;
import java.sql.SQLException;

import basics.dao.EmployeeDao;
import basics.model.Employee;
import jdbc.connection.ConnectionProvider;

// RegisterEmployeeService는 사원 정보를 데이터베이스에 저장하고 다음 사원 번호를 조회하는 기능을 제공합니다.
// RegisterEmployeeServiceは従業員情報をデータベースに保存し、次の従業員番号を取得する機能を提供します。

public class RegisterEmployeeService {
	private EmployeeDao employeeDao = new EmployeeDao();

	// 사원 정보를 데이터베이스에 저장하는 메서드.
	// 従業員情報をデータベースに保存するメソッド。
	public Integer employee(Employee emp) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 트랜잭션 시작.
			// トランザクション開始。
			conn.setAutoCommit(false);

			int savedEmployee = employeeDao.insert(conn, emp);
			// 사원 정보 저장.
			// 従業員情報を保存。
			if (savedEmployee == 0) {
				throw new RuntimeException("fail to insert employee");
			}

			conn.commit();
			// 성공 시 트랜잭션 커밋.
			// 成功時にトランザクションをコミット。
			return savedEmployee;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 가장 큰 empNo에 1을 더한 값을 반환하는 메서드.
	// 最大のempNoに1を足した値を返すメソッド。
	public int getNextEmpNo() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int lastEmpNo = employeeDao.getLastEmpNo(conn);
			// 가장 큰 empNo 조회.
			// 最大のempNoを取得。
			return lastEmpNo + 1;
			// 다음 사원 번호 반환.
			// 次の従業員番号を返す。
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}