package stermip;
import Functions.*;
import java.util.ArrayList;

public class semester implements subject{
	private ArrayList<ctAssignment>cts;
	private ArrayList<students>stus;
	private String semName;
	private String semStatus;
	private int semSession;
	public semester(String semName, int semSession, String semStatus) {
		this.semName=semName;
		this.semSession=semSession;
		this.semStatus = semStatus;
		cts = new ArrayList<ctAssignment>();
		stus = new ArrayList<students>();
	}
	public semester(String semName, int semSession, String semStatus, ArrayList<ctAssignment>cts,ArrayList<students>stus) {
		this.semName=semName;
		this.semSession=semSession;
		this.semStatus = semStatus;
		this.cts=cts;
		this.stus=stus;
	}
	public ArrayList<ctAssignment> getCts() {
		 cts=dbManager.fetchCtAssignment(semName, semSession);
		 return cts;
	}
	public String getSemName() {
		return semName;
	}
	public int getSemSession() {
		return semSession;
	}
	public void addCtAssignment(ctAssignment ct){
		cts.add(ct);
	}
	public ctAssignment getCtAssignmentAt(int i){
		return cts.get(i);
	}
	public void removeCtAssignment(ctAssignment ct){
		cts.remove(ct);
	}
	public String getSemStatus() {
		return semStatus;
	}
	public void setSemStatus(String semStatus) {
		String ntf = this.getSemStatus();
		this.semStatus = semStatus;
		notifyStudents(ntf);
	}
	 private String prereq(){
		 if(semName.equalsIgnoreCase("1st Year 2nd Semester")) return "1st Year 1st Semester";
		 if(semName.equalsIgnoreCase("2nd Year 1st Semester")) return "1st Year 2nd Semester";
		 if(semName.equalsIgnoreCase("2nd Year 2nd Semester")) return "2nd Year 1st Semester";
		 if(semName.equalsIgnoreCase("3rd Year 1st Semester")) return "2nd Year 2nd Semester";
		 if(semName.equalsIgnoreCase("3rd Year 2nd Semester")) return "3rd Year 1st Semester";
		 if(semName.equalsIgnoreCase("4th Year 1st Semester")) return "3rd Year 2nd Semester";
		 if(semName.equalsIgnoreCase("4th Year 2nd Semester")) return "4th Year 1st Semester";
		 return "none";
		 
	 }
	 public boolean isQualified(String reg_id){
		 ArrayList<ssAssignment>asts = dbManager.fetchSsAssignment(reg_id);
		 String prereqSem = prereq();
		 System.out.println(prereqSem);
		 if(prereqSem.equalsIgnoreCase("none")) {
			 for(int i = 0;i<asts.size();i++){
				 System.out.println("Entered here"); 
				 ssAssignment ast = asts.get(i);
				 if(ast.getSsStatus().equalsIgnoreCase("passed")||ast.getSsStatus().equalsIgnoreCase("passed & improved")){
					 return false;
				 }
			 }
			 System.out.println("true Returned");
			 return true;
		 }
		 for(int i = 0;i<asts.size();i++){
			 ssAssignment ast = asts.get(i);
			 if(ast.getSsStatus().equalsIgnoreCase("passed")||ast.getSsStatus().equalsIgnoreCase("passed & improved")){
				 if(ast.getSem().getSemName().equals(prereqSem)) return true;
			 }
		 }
		 return false;
	 }
	
	
	@Override
	public boolean admitStudent(students stu) {
		// TODO Auto-generated method stub
		try{
			if(isQualified(stu.getReg_id())){
				System.out.println("Qualified");
				if(dbManager.insertSsAssignment(stu.getReg_id(), semName, semSession, "current",0,0)){
					stus.add(stu);
					stu.update("Admission at "+this.semName+", "+this.semSession+" is successful!!");
					stu.update(cts);
				
				return true;
				}else return false;
			}
			return false;
		}catch(Exception e){
			System.out.println("Exception in admitStudent");
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public void cancelAdmission(students stu) {
		// TODO Auto-generated method stub
		stus.remove(stu);
		if(stus.indexOf(stu)>=0)stu.update("Successsfully Canceled  Registration from "+this.semName+","+this.semSession+".");
		else stu.update("You Never admitted to "+this.semName+","+this.semSession+".");		
	}
	@Override
	public void notifyStudents(String ntf) {
		// TODO Auto-generated method stub
		for(int i = 0;i<stus.size();i++){
			students stu = stus.get(i);
			stu.update(this.getSemName()+", "+this.semSession+" status has been changed from '"+ntf+"' to '"+this.getSemStatus()+"'.");
		}
	}
	public ArrayList<students> getStus() {
		ArrayList<String>ids =dbManager.fetchStudentsOf(semName, semSession);
		stus = new ArrayList<students>();
		for(int i=0;i<ids.size();i++){
			stus.add((students)dbManager.fetchUser(ids.get(i)));
		}
		return stus;
	}
	public void setStus(ArrayList<students> stus) {
		this.stus = stus;
	}
	
	public boolean removeCourse(String courseId){
		if(dbManager.deleteCtAssignment(semName, semSession, courseId)){
			for(int i=0;i<cts.size();i++){
				if(cts.get(i).getCrs().getCourseId().equalsIgnoreCase(courseId)){
					cts.remove(i);
					break;
				}
				
			}
			return true;
		}else return false;
	}
	
}
