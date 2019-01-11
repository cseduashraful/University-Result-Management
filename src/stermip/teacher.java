package stermip;

import java.util.ArrayList;

import Functions.dbManager;

public class teacher extends users {
	public ArrayList<csAssignment>myCourses(){
		return dbManager.fetchCsAssignment(this.getReg_id());
	}
	public ArrayList<csAssignment>myCurrentCourses(){
		ArrayList<csAssignment>css = myCourses();
		for(int i = 0;i<css.size();i++){
			if(css.get(i).isResultSubmitted()==true){
				css.remove(i);
			}
		}
		return css;
	}
	public ArrayList<csAssignment>myPreviousCourses(){
		ArrayList<csAssignment>css = myCourses();
		for(int i = 0;i<css.size();i++){
			if(css.get(i).isResultSubmitted()==false){
				css.remove(i);
			}
		}
		return css;
	}
	
}

