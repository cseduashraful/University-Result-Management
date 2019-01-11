package stermip;

public class ctAssignment {
	private course crs;
	private teacher tch;
	private boolean isResultSubmitted;
	public ctAssignment(course crs, teacher tch,boolean isResultSubmitted) {
		this.crs=crs;
		this.tch=tch;
		this.isResultSubmitted=isResultSubmitted;
	}
	public course getCrs() {
		return crs;
	}
	public teacher getTch() {
		return tch;
	}
	public void setCrs(course crs) {
		this.crs = crs;
	}
	public void setTch(teacher tch) {
		this.tch = tch;
	}
	public boolean isResultSubmitted() {
		return isResultSubmitted;
	}
	public void setResultSubmitted(boolean isResultSubmitted) {
		this.isResultSubmitted = isResultSubmitted;
	}
	
	
}
