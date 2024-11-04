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
	private int hiredNum;
	private int permanentNum;
	private int contractNum;
	private int temporaryNum;
	private int dispatchedNum;
	private int commissionedNum;
	private int dailyjobNum;
	private int retiredNum;
	
	public EmployeePage(int total,int currentPage, int size,List<Employee> employee,int hirednum,int permanentNum,int contractNum,
			int temporaryNum, int dispatchedNum, int commissionedNum,int dailyjobNum,int retiredNum) {
		this.total=total;
		this.currentPage=currentPage;
		this.employee=employee;
		this.hiredNum=hirednum;
		this.permanentNum=permanentNum;
		this.contractNum=contractNum;
		this.temporaryNum=temporaryNum;
		this.dispatchedNum=dispatchedNum;
		this.commissionedNum=commissionedNum;
		this.dailyjobNum=dailyjobNum;
		this.retiredNum=retiredNum;
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
	
	public int getPermanentNum() {
		return permanentNum;
	}

	public int getContractNum() {
		return contractNum;
	}

	public int getTemporaryNum() {
		return temporaryNum;
	}

	public int getDispatchedNum() {
		return dispatchedNum;
	}

	public int getCommissionedNum() {
		return commissionedNum;
	}

	public int getDailyjobNum() {
		return dailyjobNum;
	}

	public int getRetiredNum() {
		return retiredNum;
	}
	public int getHiredNum() {
		return hiredNum;
	}

	public boolean hasNoEmployees() {
		return total==0;
	}
	public boolean hasEmployees() {
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
