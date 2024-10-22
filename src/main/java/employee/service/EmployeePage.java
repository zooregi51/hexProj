package employee.service;

import java.util.List;

import employee.model.Employee;

public class EmployeePage {
	private int total;
	private int currentPage;
	private List<Employee> employee;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public EmployeePage(int total,int currentPage, int size,List<Employee> employee) {
		this.total=total;
		this.currentPage=currentPage;
		this.employee=employee;
		if(total==0) {
			totalPages=0;
			startPage=0;
			endPage=0;
		}else {
			totalPages=total/size;
			if(total%size >0) {
				totalPages++;
			}
		}
		int modVal=currentPage%5;
		startPage=currentPage/5*5+1;
		if(modVal==0)startPage-=5;
		
		endPage=startPage+4;
		if(endPage>totalPages)endPage=totalPages;
	}
	
	public boolean hasNoEmployee() {
		return total==0;
	}
	public boolean hasEmployee() {
		return total>0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
}
