package stermip;

import java.util.ArrayList;

public class projectMarks extends decoratedMarkDistributionSystem{
	private double projectMarks;
	private double percentage;
	public projectMarks(markDistributionSystem mds) {
		// TODO Auto-generated constructor stub
		super(mds);
	}
	@Override
	public void insertMarks(double[] marks) {
		// TODO Auto-generated method stub
		projectMarks=marks[3];
		super.insertMarks(marks);
	}
	@Override
	public void printTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println("Poject marks: "+projectMarks);
		super.printTotalMarks();
	}
	@Override
	public double getTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println(projectMarks*((percentage*1.0)/100));
		return super.getTotalMarks()+projectMarks*((percentage*1.0)/100);
	}
	@Override
	public void setPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		this.percentage=percentage[3];
		super.setPercentage(percentage);
		
	}
	@Override
	public void getPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		percentage[3]=this.percentage;
		super.getPercentage(percentage);
		
	}
	@Override
	public void markingSectors(ArrayList<String> sectors) {
		// TODO Auto-generated method stub
		super.markingSectors(sectors);
		sectors.add("Project(%)");
	}
}

