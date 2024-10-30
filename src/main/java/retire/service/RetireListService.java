package retire.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jdbc.connection.ConnectionProvider;
import retire.dao.RetireDAO;
import retire.model.Retirement;

public class RetireListService {

	RetireDAO retireDAO = new RetireDAO();

	public List<Retirement> getRetirementList(HttpServletRequest req) {

	    //검색조건
	    String schType = req.getParameter("schType");
        String schText = req.getParameter("schText");

	    System.out.println(":::: RetireListService.getRetirementList ::::");

	    try(Connection conn = ConnectionProvider.getConnection()){
			List<Retirement> list = retireDAO.getRetirementList(conn,schType,schText);
			System.out.println("list : "+list.toString());
			return list;
		} catch(SQLException e) {
		    e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	public void saveRetirement(HttpServletRequest req) throws Exception {

	    System.out.println(":::: RetireListService.saveRetirement ::::");

	    String empNo = req.getParameter("empNo");
	    String retiredForm = req.getParameter("retiredForm");
	    String retiredDate = req.getParameter("retiredDate");
	    String retiredPhonenum = req.getParameter("retiredPhonenum");

	    Retirement retirement = new Retirement();

	    //jsp에서 넘어온 파라미터를 vo에 바인딩한다.
	    retirement.setEmpNo(Integer.parseInt(empNo));
	    retirement.setRetiredForm(retiredForm);
        retirement.setRetiredDate(retiredDate);
        retirement.setRetiredPhonenum(retiredPhonenum);

	    try(Connection conn = ConnectionProvider.getConnection()){
	        retireDAO.saveRetirement(conn, retirement);
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
