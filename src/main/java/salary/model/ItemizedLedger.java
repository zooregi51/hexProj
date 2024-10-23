package salary.model;

import java.util.ArrayList;

public class ItemizedLedger {
	String empForm;
	String empName;
	String dep;
	ArrayList<Integer> yearSal;
	public ItemizedLedger(String empForm, String empName, String dep, ArrayList<Integer> yearSal) {
		this.empForm = empForm;
		this.empName = empName;
		this.dep = dep;
		this.yearSal = yearSal;
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
	
}
