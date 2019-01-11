package stermip;

import java.util.ArrayList;

public class course {
	private double credit;
	private String type;
	private String courseArea;
	private String courseName;
	private markDistributionSystem mds;
	private String courseId;
	public course(){
		
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}	
	
	public void setCourseArea(String courseArea) {
		this.courseArea = courseArea;
	}	
	public markDistributionSystem getMds() {
		return mds;
	}
	public void setMds(markDistributionSystem mds) {
		this.mds = mds;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public String getCourseArea() {
		return courseArea;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public ArrayList<String> nameOfMarkingSectors(){
		ArrayList<String>x = new ArrayList<String>();
		mds.markingSectors(x);
		return x;
		
	}
	
}
