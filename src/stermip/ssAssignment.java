package stermip;
import Functions.*;
public class ssAssignment {
	private semester sem;
	private String ssStatus;//passed, failed, dropped,passed&improved
	private double gpa;
	private double obtainedCredit;
	public ssAssignment(){}
	public ssAssignment(String semName,int semSession, String ssStatus, double gpa, double obtainedCredit){
		this.ssStatus=ssStatus;
		this.gpa=gpa;
		this.obtainedCredit=obtainedCredit;
		this.sem=dbManager.fetchSemester(semName, semSession);
	}
	public semester getSem() {
		return sem;
	}
	public void setSem(semester sem) {
		this.sem = sem;
	}
	public String getSsStatus() {
		return ssStatus;
	}
	public void setSsStatus(String ssStatus) {
		this.ssStatus = ssStatus;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public double getObtainedCredit() {
		return obtainedCredit;
	}
	public void setObtainedCredit(double obtainedCredit) {
		this.obtainedCredit = obtainedCredit;
	}
	
}
