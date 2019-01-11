package stermip;

import java.util.ArrayList;

public abstract class decoratedMarkDistributionSystem extends markDistributionSystem{
	markDistributionSystem mds;
	public decoratedMarkDistributionSystem(markDistributionSystem mds) {
		// TODO Auto-generated constructor stub
		this.mds=mds;
	}
	@Override
	public double getTotalMarks() {
		// TODO Auto-generated method stub
		return mds.getTotalMarks();
	}
	@Override
	public void insertMarks(double marks[]) {
		// TODO Auto-generated method stub
		mds.insertMarks(marks);
		
	}
	@Override
	public void printTotalMarks() {
		// TODO Auto-generated method stub
		mds.printTotalMarks();
		
	}
	public void setPercentage(double percentage[]){
		mds.setPercentage(percentage);
	}
	public void getPercentage(double percentage[]){
		mds.getPercentage(percentage);
	}
	public void markingSectors(ArrayList<String>sectors){
		mds.markingSectors(sectors);
	}
}
