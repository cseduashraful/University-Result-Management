package stermip;

public class csAssignment {
	private semester sem;
	private course crs;
	private boolean isResultSubmitted;
	public csAssignment(semester sem, course crs, boolean isResultSubmitted) {
		// TODO Auto-generated constructor stub
		this.crs=crs;
		this.isResultSubmitted=isResultSubmitted;
		this.sem=sem;
	}
	public semester getSem() {
		return sem;
	}
	public void setSem(semester sem) {
		this.sem = sem;
	}
	public course getCrs() {
		return crs;
	}
	public void setCrs(course crs) {
		this.crs = crs;
	}
	public boolean isResultSubmitted() {
		return isResultSubmitted;
	}
	public void setResultSubmitted(boolean isResultSubmitted) {
		this.isResultSubmitted = isResultSubmitted;
	}
	
}
