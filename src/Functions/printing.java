package Functions;

import java.util.Date;

public class printing {
	public static String dateFormat(Date dob){
		if(dob==null) return "Not inserted yet";
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
		if(day==1 || day==21 || day==31) dateExtension=""+day+"st";
		else if(day==2 || day==22) dateExtension=""+day+"nd";
		else if(day==3 || day==23) dateExtension=""+day+"rd";
		else dateExtension = ""+day+"th";
		String monthExtension=null;
		if(mon==0)  monthExtension = "January";
		else if (mon==1) monthExtension = "February";
		else if(mon==2) monthExtension = "March";
		else if(mon==3) monthExtension = "April";
		else if(mon==4) monthExtension = "May";
		else if(mon==5) monthExtension = "June";
		else if(mon==6) monthExtension = "July";
		else if(mon==7) monthExtension = "August";
		else if(mon==8) monthExtension = "September";
		else if(mon==9) monthExtension = "October";
		else if(mon==10) monthExtension = "November";
		else if(mon==11) monthExtension = "December";
		return dateExtension+" "+monthExtension+", "+year;
	}
}
