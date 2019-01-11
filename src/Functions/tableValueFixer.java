package Functions;

import java.util.ArrayList;
import java.util.Date;

public class tableValueFixer {
	public static String oracleValue(int value){
		return ""+value+"";
	}
	public static String oracleValue(String value){
		return "'"+value+"'";
	}
	public static String oracleValue(Date dob){
		@SuppressWarnings("deprecation")
		int mon = dob.getMonth();
		System.out.println(mon);
		@SuppressWarnings("deprecation")
		int day = dob.getDate();
		System.out.println(day);
		@SuppressWarnings("deprecation")
		int year = dob.getYear()+1900;
		System.out.println(year);
		String dateExtension = null;
		String monthExtension=null;
		if(mon==0)  monthExtension = "Jan";
		else if (mon==1) monthExtension = "Feb";
		else if(mon==2) monthExtension = "Mar";
		else if(mon==3) monthExtension = "Apr";
		else if(mon==4) monthExtension = "May";
		else if(mon==5) monthExtension = "Jun";
		else if(mon==6) monthExtension = "Jul";
		else if(mon==7) monthExtension = "Aug";
		else if(mon==8) monthExtension = "Sep";
		else if(mon==9) monthExtension = "Oct";
		else if(mon==10) monthExtension = "Nov";
		else if(mon==11) monthExtension = "Dec";
		return "'"+day+"-"+monthExtension+"-"+year+"'";
	}
	public static void addColumnToInsertSql(ArrayList<String> columnName, ArrayList <String> columnValue, String name,String value){
		columnName.add(name);
		columnValue.add(tableValueFixer.oracleValue(value));
	}
	public static void addColumnToInsertSql(ArrayList<String> columnName, ArrayList <String> columnValue, String name,int value){
		columnName.add(name);
		columnValue.add(tableValueFixer.oracleValue(value));
	}
	public static void addColumnToInsertSql(ArrayList<String> columnName, ArrayList <String> columnValue, String name,Date value){
		columnName.add(name);
		columnValue.add(tableValueFixer.oracleValue(value));
	}
	
}
