package salary.command;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import salary.model.Salary;
import salary.model.SalaryLedgerMonth;
import salary.service.GetSalaryLedgerService;
import salary.service.SalaryTransferService;

/*
 * 급여 이체 확인 페이지 핸들러
 * /salary/salaryTransferCheck.do 에 해당하는 요청이 들어오면 처리하는 핸들러
 * 시작 날짜 ~ 끝 날짜를 설정하면 그 기간의 급여 이체 정보를 가져와서 세팅하는 페이지
 * startDate 변수  - 시작 날짜
 * 			        요청을 받았을 때 해당 값이 없으면
 * 			        끝 날짜로부터 1년 전으로 설정
 * endDate 변수    - 끝 날짜
 *                  요청을 받았을 때 해당 값이 없으면
 *                  현재 날짜로 설정
 * AddDate 매서드   - 날짜 계산하는 메서드
 *                  날짜, 년, 달, 일을 받아 해당 값을 계산한 결과값을 리턴
 * 
 * */

/*
* 給与振替確認ページハンドラ
* /salary/salaryTransferCheck。doに該当する要請が入ってきたら処理するハンドラ
* 開始日~終了日を設定すると、その期間の給与振込情報を取得してセットするページ
* startDate変数 - 開始日
* 要請を受けたときに、該当する値がない場合は
* 終日から1年前に設定
* endDate変数 - 終了日付
* 要請を受けたときに、該当する値がない場合は
* 現在の日付に設定
* Add Date マサード - 日付を計算するメソッド
* 日付、年、月、日を受けて当該値を計算した結果値をリターン
*
* */

public class SalaryTransferCheckHandler implements CommandHandler {

	private SalaryTransferService transferSer = new SalaryTransferService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String startDate = req.getParameter("stDate");
		String endDate = req.getParameter("edDate");
		
		LocalDate now = LocalDate.now();
		if(endDate == null || endDate == "") {
			endDate = now.getYear() + "" + 
					(now.getMonthValue() >= 10 ? now.getMonthValue() : "0" + now.getMonthValue()) + 
					((now.getDayOfMonth()) >= 10 ? (now.getDayOfMonth()) : "0" + (now.getDayOfMonth()));
		}

		endDate = endDate.replaceAll("-", "");
		if(startDate == null || startDate == "") {
			startDate = AddDate(endDate, -1, 0, 0);
		}
		
		startDate = startDate.replaceAll("-", "");
		endDate = AddDate(endDate, 0, 0, 1);
		startDate = (startDate).substring(2, 4) + "-" + (startDate).substring(4, 6) + "-" + (startDate).substring(6);
		endDate = (endDate).substring(2, 4) + "-" + (endDate).substring(4, 6) + "-" + (endDate).substring(6);
		
		ArrayList<Salary> salaries = transferSer.getTransferedSalary(startDate, endDate);
		
		req.setAttribute("salaries", salaries);
		return "/WEB-INF/view/salary/salaryTransferCheck.jsp";
		
	}
	public String AddDate(String strDate, int year, int month, int day) throws Exception {
		
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
        
		Calendar cal = Calendar.getInstance();
        
		Date dt = dtFormat.parse(strDate);
        
		cal.setTime(dt);
        
		cal.add(Calendar.YEAR,  year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DATE,  day);
        
		return dtFormat.format(cal.getTime());
	}

}
