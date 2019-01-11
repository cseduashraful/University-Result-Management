package stermip;

import java.util.ArrayList;

public class assignmentMarks extends decoratedMarkDistributionSystem{
	private double assignmentMarks;
	private double percentage;
	public assignmentMarks(markDistributionSystem mds) {
		// TODO Auto-generated constructor stub
		super(mds);
	}
	@Override
	public void insertMarks(double[] marks) {
		// TODO Auto-generated method stub
		assignmentMarks=marks[2];
		super.insertMarks(marks);
	}
	@Override
	public void printTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println("Assignment marks: "+assignmentMarks);
		super.printTotalMarks();
	}
	@Override
	public double getTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println(assignmentMarks*((percentage*1.0)/100));
		return super.getTotalMarks()+assignmentMarks*((percentage*1.0)/100);
	}
	@Override
	public void setPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		this.percentage=percentage[2];
		super.setPercentage(percentage);
		
	}
	@Override
	public void getPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		percentage[2]=this.percentage;
		super.getPercentage(percentage);
		
	}
	@Override
	public void markingSectors(ArrayList<String> sectors) {
		// TODO Auto-generated method stub
		super.markingSectors(sectors);
		sectors.add("Assignment(%)");
		
	}
	
}
