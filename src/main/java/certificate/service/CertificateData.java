package certificate.service;

import certificate.model.Certificate;
import employee.model.Employee;

public class CertificateData {

	private Certificate certificate;
	private Employee employee;
	
	public CertificateData(Certificate certificate, Employee employee) {
		this.certificate = certificate;
		this.employee = employee;
	}
	public Certificate getCertificate() {
		return certificate;
	}
	public Employee getEmployee() {
		return employee;
	}
}
