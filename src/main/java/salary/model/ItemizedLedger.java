package salary.model;

import java.util.ArrayList;

/*
 * ItemizedLedger Model
 * 항목별 대장 model
 * 
 * String empForm              사원의 형태(정규직/계약직)
 * String empName              사원 이름
 * String dep                  사원의 부서
 * String pos                  사원의 직위
 * ArrayList<Integer> yearSal  사원의 1월 부터 12월 까지의 특정 급여의 리스트
 * 
 * */

/*
* ItemizedLedger Model
* 項目別台帳モデル
*
* String empForm             社員の形態(正規職/契約職)
* String empName             社員名
* String dep                 社員の部署
* String pos                 社員の職位
* ArrayList<Integer>yearSal  社員の1月から12月までの特定給与のリスト
*
* */

public class ItemizedLedger {
	private String empForm;
	private String empName;
	private String dep;
	private String pos;
	private ArrayList<Integer> yearSal;
	public ItemizedLedger(String empForm, String empName, String dep, ArrayList<Integer> yearSal, String pos) {
		this.empForm = empForm;
		this.empName = empName;
		this.dep = dep;
		this.yearSal = yearSal;
		this.pos = pos;
	}
	public String getEmpForm() {
		return empForm;
	}
	public String getEmpName() {
		return empName;
	}
	public String getDep() {
		return dep;
	}
	public ArrayList<Integer> getYearSal() {
		return yearSal;
	}
	public String getPos() {
		return pos;
	}
	
}
