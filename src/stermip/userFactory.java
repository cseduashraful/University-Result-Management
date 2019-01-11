package stermip;

import java.sql.ResultSet;
import java.sql.SQLException;


public class userFactory {
	
	private static userFactory ufac;
	private userFactory(){
		
	}
	public static userFactory getInstanceOfUserFactory(){
		if(ufac!=null) return ufac;
		else{
			ufac=new userFactory();
			return ufac;
		}
	}
	
	public users createUser(String type){
		users u = null;
		if(type.equalsIgnoreCase("student")) {
			 u = new students();
			 u.setRole(type);
		}
		else if(type.equalsIgnoreCase("teacher")) {
			 u = new teacher();
			 u.setRole(type);
		}
		else if(type.equalsIgnoreCase("chairman")){
			 u = new admin();
			 u.setRole(type);
		}
		return u;
		
	}
	
	
	public users resultSetToUser(ResultSet rs) throws SQLException{
		
		admin uc;
		students ust;
		teacher usf;
		users u;
		
		if(rs.getString("role").equals("chairman")) 
			{
				uc = new admin();
				u=uc;
			}
		if(rs.getString("role").equals("student"))
			{
				ust = new students();
				ust.setSerial(rs.getInt("serial"));
				u=ust;
			}
		else
			{
				usf = new teacher();
				u=usf;
			}
		
		u.setReg_id(rs.getString("reg_id"));
		u.setName(rs.getString("name"));
		u.setEmail(rs.getString("email"));
		u.setMob(rs.getString("mob"));
		u.setRole(rs.getString("role"));
		u.setDob(rs.getDate("dob"));
		/*if(u.getDob()!=null){
			java.util.Date d = u.getDob();
			d.setYear(d.getYear()-1900);
			u.setDob(d);
		}*/
		u.setStatus(rs.getString("status"));
		u.setYearOfRegistration(rs.getInt("year_of_registration"));
		return u;
}
}
