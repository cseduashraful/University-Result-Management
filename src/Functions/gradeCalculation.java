package Functions;
//Singleton Pattern
public class gradeCalculation {
	private static  gradeCalculation uniqueInstance=null;
	private gradeCalculation(){
		
	}
	public static gradeCalculation getInstanceOfGradeCalculation(){
		if(uniqueInstance==null){
			uniqueInstance = new gradeCalculation();
			System.out.println("object created");
		}
		return uniqueInstance;
	}
	
	public double gradePoint(double marks){
		if(marks>=80) return 4.0;
		else if(marks>=75) return 3.75;
		else if(marks>=70) return 3.50;
		else if(marks>=65) return 3.25;
		else if(marks>=60) return 3.00;
		else if(marks>=55) return 2.75;
		else if(marks>=50) return 2.50;
		else if(marks>=45) return 2.25;
		else if(marks>=40) return 2.00;
		else return 0;
	}
	
	public String grade(double gradePoint){
		if(gradePoint==4.0) return "A+";
		else if(gradePoint==3.75) return "A";
		else if(gradePoint==3.50) return "A-";
		else if(gradePoint==3.25) return "B+";
		else if(gradePoint==3.00) return "B";
		else if(gradePoint==2.75) return "C+";
		else if(gradePoint==2.50) return "C";
		else if(gradePoint==2.25) return "D+";
		else if(gradePoint==2.00) return "D";
		else return "F";
	}

}
