package stermip;

import java.util.ArrayList;

public class vivaMarks extends decoratedMarkDistributionSystem{
	private double vivaMarks;
	private double percentage;
	public vivaMarks(markDistributionSystem mds) {
		// TODO Auto-generated constructor stub
		super(mds);
	}
	@Override
	public void insertMarks(double[] marks) {
		// TODO Auto-generated method stub
		vivaMarks=marks[4];
		super.insertMarks(marks);
	}
	@Override
	public void printTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println("viva marks: "+vivaMarks);
		super.printTotalMarks();
	}
	@Override
	public double getTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println(vivaMarks*((percentage*1.0)/100));
		return super.getTotalMarks()+vivaMarks*((percentage*1.0)/100);
	}
	@Override
	public void setPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		this.percentage=percentage[4];
		super.setPercentage(percentage);
		
	}
	@Override
	public void getPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		percentage[4]=this.percentage;
		super.getPercentage(percentage);
		
	}
	@Override
	public void markingSectors(ArrayList<String> sectors) {
		// TODO Auto-generated method stub
		super.markingSectors(sectors);
		sectors.add("Viva(%)");
	}
}
