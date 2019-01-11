package stermip;

import java.util.ArrayList;

public class students extends users implements observer {
	private int serial;
	private ArrayList<String>ntfs;
	students(){
		super();
		ntfs = new ArrayList<String>();
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	
	
	public ArrayList<String> getNtfs() {
		
		return ntfs;
	}
	public void setNtfs(ArrayList<String> ntfs) {
		this.ntfs = ntfs;
	}
	public void addNotification(String str){
		ntfs.add(str);
	}
	public void deleteNotification(String str){
		ntfs.remove(str);
	}
	public String getNotification(int i){
		if(i>=ntfs.size()) return "No Such Notification";
		else return ntfs.get(i);
	}
	
	
	
	@Override
	public void update(ArrayList<ctAssignment> cts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String SpecialNotification) {
		// TODO Auto-generated method stub
		addNotification(SpecialNotification);
		
	}
	

}
