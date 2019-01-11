package Functions;

import java.util.ArrayList;

public class checkUserNameValidity {
	public static boolean checkFormat(String userName){
		if(userName.length()==0) return false;
		char ch;
		ch = userName.charAt(0);
		if(ch=='.' || ch=='-' || ch=='_') return false;
		ch = userName.charAt(userName.length()-1);
		if(ch=='.' || ch=='-' || ch=='_') return false;
		for(int i = 0; i<userName.length();i++){
			if(userName.charAt(i)==' ') return false;
			ch = userName.charAt(i);
			if(!((ch>='A' && ch<='Z')||(ch>='a' && ch<='z')||(ch>='0'&& ch<='9')|| ch=='.' || ch=='-' || ch=='_'))
				return false;
		}
		return true;
	}
	public static boolean checkAvailability(String userName) {
		ArrayList<String>userNames = dbManager.fetchAuserNames();
		for(int i = 0;i<userNames.size();i++){
			if(userName.equalsIgnoreCase(userNames.get(i))) return false;
		}
		return true;
	}
	public static boolean isValid(String userName) {
		boolean decision;
		decision = checkFormat(userName);
		if(decision) decision=checkAvailability(userName);
		return decision;
		
	}
	

}
