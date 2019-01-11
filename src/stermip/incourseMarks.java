package stermip;

import java.util.ArrayList;

public class incourseMarks extends decoratedMarkDistributionSystem{
	private double incourseMarks;
	private double percentage;
	public incourseMarks(markDistributionSystem mds) {
		// TODO Auto-generated constructor stub
		super(mds);
	}
	@Override
	public void insertMarks(double[] marks) {
		// TODO Auto-generated method stub
		incourseMarks=marks[1];
		super.insertMarks(marks);
	}
	@Override
	public void printTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println("incourse marks: "+incourseMarks);
		super.printTotalMarks();
	}
	@Override
	public double getTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println(incourseMarks*((percentage*1.0)/100));
		return super.getTotalMarks()+incourseMarks*((percentage*1.0)/100);
	}
	@Override
	public void setPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		this.percentage=percentage[1];
		super.setPercentage(percentage);
	}
	@Override
	public void getPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		percentage[1]=this.percentage;
		super.getPercentage(percentage);
		
	}
	@Override
	public void markingSectors(ArrayList<String> sectors) {
		// TODO Auto-generated method stub
		super.markingSectors(sectors);
		sectors.add("Incourse(%)");
	}
}

