package stermip;

import java.util.ArrayList;

public class finalMarks extends decoratedMarkDistributionSystem{
	private double finalMarks;
	private double percentage;
	public finalMarks(markDistributionSystem mds) {
		// TODO Auto-generated constructor stub
		super(mds);
	}
	public void insertMarks(double marks[]) {
		// TODO Auto-generated method stub
		finalMarks=marks[0];
		super.insertMarks(marks);
	}
	@Override
	public void printTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println("Final marks:"+finalMarks);
		super.printTotalMarks();
	}
	@Override
	public double getTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println(finalMarks*((percentage*1.0)/100));
		return super.getTotalMarks()+finalMarks*(percentage/100.0);
	}
	@Override
	public void setPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		this.percentage=percentage[0];
		super.setPercentage(percentage);		
	}
	@Override
	public void getPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		percentage[0]=this.percentage;
		super.getPercentage(percentage);
		
	}
	@Override
	public void markingSectors(ArrayList<String> sectors) {
		// TODO Auto-generated method stub
		sectors.add("Final(%)");
		super.markingSectors(sectors);
	}
}

