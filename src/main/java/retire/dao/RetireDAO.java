package retire.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import retire.model.Retirement;

public class RetireDAO {

    //퇴직처리할 목록(사원목록) 조회!
    public List<Retirement> getRetirementList(Connection conn, String schType, String schText) throws SQLException {

        //Connection conn이란? 데이터베이스를 연결해주는 Java 인터페이스 객체

        //PreparedStatement란? SQL 실행해주는 Java 인터페이스 객체
        PreparedStatement pstmt = null;
        //ResultSet은 데이터베이스 쿼리의 결과를 행(row) 단위로 탐색할 수 있도록 해주는 Java 인터페이스 객체
        ResultSet rs = null;

        // 기본 SQL 문 (퇴직하지 않은 사원 목록 조회)
        String sql = "SELECT empno, name, hireddate, dep, position " +
                     "FROM employee WHERE retireddate IS NULL";

        // 검색 조건 추가 (사원번호 또는 사원명)
        if (schType != null && !"".equals(schText)) {
            if ("empNo".equals(schType)) {
                sql += " AND empNo = ?";
            } else if ("name".equals(schType)) {
                sql += " AND name LIKE ?";
            }
        }

        try {

            pstmt = conn.prepareStatement(sql);

            // 검색 조건에 따른 파라미터 설정
            if (schType != null && !"".equals(schText)) {
                if ("empNo".equals(schType)) {
                    pstmt.setInt(1, Integer.parseInt(schText)); // 사원번호는 숫자 타입
                } else if ("name".equals(schType)) {
                    pstmt.setString(1, "%" + schText + "%"); // 사원명은 부분 일치 검색
                }
            }

            rs = pstmt.executeQuery();
            List<Retirement> retirementList = new ArrayList<>();

            // ResultSet에서 데이터를 꺼내어 Retirement 객체 생성 및 리스트에 추가
            while (rs.next()) {
                Retirement retirement = new Retirement(); // 기본 생성자 사용

                // Setter 메서드를 통해 필드 값 설정
                retirement.setEmpNo(rs.getInt("empNo"));
                retirement.setName(rs.getString("name"));
                retirement.setHiredDate(rs.getDate("hiredDate"));
                retirement.setDep(rs.getString("dep"));
                retirement.setPosition(rs.getString("position"));

                // 리스트에 추가
                retirementList.add(retirement);
            }
            return retirementList; // 조회된 리스트 반환

        } catch (SQLException e) {
            System.err.println("Error retrieving employee list: " + e.getMessage());
            //연결한 SQL관련 객체 전부 연결해제
            JdbcUtil.close(pstmt);
            JdbcUtil.close(rs);
            JdbcUtil.close(conn);
            throw e; // 예외를 다시 던져 호출한 곳에서 처리하도록 위임
        }finally {
            //연결한 SQL관련 객체 전부 연결해제
            JdbcUtil.close(pstmt);
            JdbcUtil.close(rs);
            JdbcUtil.close(conn);
        }
    }

   //사원 탈퇴정보 저장 및 사원테이블 퇴직일자 업데이트
   public int saveRetirement(Connection conn, Retirement retirement) throws SQLException, Exception {

       PreparedStatement insertPstmt = null;
       PreparedStatement updatePstmt = null;

       //퇴직처리 정보 저장
       String insertSql = "INSERT INTO RETIREMENT (empno, retireddate, retiredform, retiredphonenum, retiredid) VALUES (?, ?, ?, ?, ?)";

       int insertResult = 0;
       int updateResult = 0;

       conn.setAutoCommit(false);  // 트랜잭션 시작

       try {

           //퇴직처리 저장값 셋팅
           insertPstmt = conn.prepareStatement(insertSql);

           insertPstmt.setInt(1, retirement.getEmpNo());
           insertPstmt.setString(2, retirement.getRetiredDate());
           insertPstmt.setString(3, retirement.getRetiredForm());
           insertPstmt.setString(4, retirement.getRetiredPhonenum());
           insertPstmt.setString(5, null);

           insertResult = insertPstmt.executeUpdate();

           if (insertResult > 0) {

               //사원테이블에 퇴직날짜 업데이트
               String updateSql = "UPDATE EMPLOYEE SET retireddate = ? WHERE empno = ?";

               //퇴직날짜 업데이트 값 셋팅
               updatePstmt = conn.prepareStatement(updateSql);
                updatePstmt.setString(1, retirement.getRetiredDate());
                updatePstmt.setInt(2, retirement.getEmpNo());

                updateResult = updatePstmt.executeUpdate();

           }
           conn.commit();  // 성공 시 커밋

       } catch (SQLException e) {
           conn.rollback();  // 실패 시 롤백

           //연결한 SQL관련 객체 전부 연결해제
            JdbcUtil.close(insertPstmt);
            JdbcUtil.close(updatePstmt);
            JdbcUtil.close(conn);
            System.err.println("Error saveRetirement: " + e.getMessage());
           throw e;

       } finally {

           //연결한 SQL관련 객체 전부 연결해제
           conn.setAutoCommit(true);  // 트랜잭션 종료
           JdbcUtil.close(insertPstmt);
           JdbcUtil.close(updatePstmt);
            JdbcUtil.close(conn);
       }

       return insertResult > 0 && updateResult > 0 ? 1 : 0;
   }


}