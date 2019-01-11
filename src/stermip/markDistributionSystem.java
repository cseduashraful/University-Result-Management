package stermip;

import java.sql.Statement;
import java.util.ArrayList;

import Functions.dbManager;



abstract public class markDistributionSystem {

	abstract public double getTotalMarks();
	abstract public void insertMarks(double marks[]);
	abstract public void printTotalMarks();
	abstract public void setPercentage(double percentage[]);
	abstract public void getPercentage(double percentage[]);
	abstract public void markingSectors(ArrayList<String>sectors);
	final public static boolean addSector(String column){
		return dbManager.addSector(column);
	}
	final public static boolean deleteSector(String column){
		return dbManager.deleteSector(column);
	}
	final public static ArrayList<String> getAvailableSectors(){
		ArrayList<String> columns = dbManager.getColumnNamesOf("mds");
		for(int i = 0;i<columns.size();i++){
			String str = columns.get(i);
			if(str.equalsIgnoreCase("COURSE_ID")) {
				columns.remove(i);
				break;
			}
		}
		return columns;
	}

}








/*final, incourse, assignment, project, viva,dailyEvaluation*/
