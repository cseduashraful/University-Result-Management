package stermip;

import java.util.ArrayList;

public class dailyEvaluationMarks extends decoratedMarkDistributionSystem{
	private double dailyEvaluationMarks;
	private double percentage;
	public dailyEvaluationMarks(markDistributionSystem mds) {
		// TODO Auto-generated constructor stub
		super(mds);
	}
	@Override
	public void insertMarks(double[] marks) {
		// TODO Auto-generated method stub
		dailyEvaluationMarks=marks[5];
		super.insertMarks(marks);
	}
	@Override
	public void printTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println("dailyEvaluation marks: "+dailyEvaluationMarks);
		super.printTotalMarks();
	}
	@Override
	public double getTotalMarks() {
		// TODO Auto-generated method stub
		System.out.println(dailyEvaluationMarks*((percentage*1.0)/100));
		return super.getTotalMarks()+dailyEvaluationMarks*((percentage*1.0)/100);
	}
	@Override
	public void setPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		this.percentage=percentage[5];
		super.setPercentage(percentage);
		
	}
	@Override
	public void getPercentage(double[] percentage) {
		// TODO Auto-generated method stub
		percentage[5]=this.percentage;
		super.getPercentage(percentage);
		
	}
	@Override
	public void markingSectors(ArrayList<String> sectors) {
		// TODO Auto-generated method stub
		
		sectors.add("Daily Eva(%)");
		super.markingSectors(sectors);
		
	}
}