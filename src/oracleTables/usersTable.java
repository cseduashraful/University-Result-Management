package oracleTables;

import java.util.ArrayList;

public class usersTable {
	public  static String reg_id ="REG_ID";
	public  static String name ="NAME";
	public  static String role ="ROLE";
	public  static String dob = "DOB";
	public  static String email ="EMAIL";
	public  static String mob = "MOB";
	public  static String serial = "SERIAL";
	public  static String password = "PASSWORD";
	public  static String year_of_registration = "YEAR_OF_REGISTRATION";
	public  static String status = "STATUS";
	public  static String code = "CODE";
	
	public static ArrayList<String> columnName(){
		ArrayList<String>ar = new ArrayList<String>();
		ar.add(reg_id);
		ar.add(name);
		ar.add(role);
		ar.add(dob);
		ar.add(email);
		ar.add(mob);
		ar.add(serial);
		ar.add(password);
		ar.add(year_of_registration);
		ar.add(status);
		ar.add(code);
		return ar;
	}
	
}

