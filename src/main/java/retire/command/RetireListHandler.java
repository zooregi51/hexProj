package retire.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import retire.model.Retirement;
import retire.service.RetireListService;


public class RetireListHandler implements CommandHandler {

	private RetireListService retireListService = new RetireListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		
	    //요청받은 URI를 가져온다.
	    String uri = req.getRequestURI();

	    //이동할 .jsp 파일 정의
	    String returnJsp = "";

        System.out.println("요청받은 URL"+uri);
        
        try {
        
		    //HTTP METHOD GET방식일때
		    if(req.getMethod().equalsIgnoreCase("GET")) {
	
		        System.out.println("HTTP METHOD = GET ");
	
		        // 퇴직목록 이동 및 목록조회
	    	    if("/retire/retireList.do".equals(uri)) {
	
	    	        System.out.println("/retire/retireList.do 탈퇴처리 목록 이동");
	
	    	        List<Retirement> list = retireListService.getRetirementList(req);
	
	    	        req.setAttribute("list", list);
	
	    	        returnJsp = "/WEB-INF/view/retire/retireList.jsp";
	
	    	     // 퇴직처리 팝업 호출
	    	    }else if("/retire/retireState.do".equals(uri)) {
	
	    	        System.out.println("/retire/retireState.do 탈퇴처리 팝업 호출");
	
	    	        returnJsp = "/WEB-INF/view/retire/retireState.jsp";
	
	    	    }
	
	        //HTTP METHOD POST방식일때
		    }else if(req.getMethod().equalsIgnoreCase("POST")) {
	
	            System.out.println("HTTP METHOD = POST");
	
	            // 퇴직처리 서비스 호출
	           if("/retire/retireState.do".equals(uri)) {
	               retireListService.saveRetirement(req);
	               // 응답에 스크립트 전송: 부모 페이지 새로고침 후 팝업 닫기
	               res.setContentType("text/html; charset=UTF-8");
	               res.getWriter().println("<script>");
	               res.getWriter().println("    alert('퇴직처리가 완료되었습니다.');");
	               res.getWriter().println("    window.opener.location.reload();");  // 부모 페이지 새로고침
	               res.getWriter().println("    window.close();");  // 팝업 창 닫기
	               res.getWriter().println("</script>");
	               return null;  // JSP 경로 반환 없이 종료
	           }
	
		    }

        }catch (Exception e) {
        	e.printStackTrace();
			// TODO: handle exception
		}
	
        System.out.println("이동할 JSP"+returnJsp);

	    return returnJsp;

	}
}


