package stermip;
import java.util.Date;
import Functions.*;

abstract public class users {
	private String name;
	private String role;
	private String reg_id;
	private Date dob;
	private String email;
	private String mob;
	private int yearOfRegistration;
	private String status;
	users(){
		
	}
	public int getYearOfRegistration() {
		return yearOfRegistration;
	}
	public void setYearOfRegistration(int yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public void removeProfilePhoto(){
		int sr = dbManager.profilePhotoSerial(reg_id);
		if(sr==0) return;
		dbManager.updatePictureStatus(reg_id, sr, "past");
	}
	public void setProfilePhoto(int sr){
		removeProfilePhoto();
		dbManager.updatePictureStatus(reg_id, sr, "current");
	}
	public void deletePhoto(int sr){
		dbManager.deletePhoto(reg_id, sr);
	}
	public String printDob(){
		return printing.dateFormat(dob);
	}
}

/*
 * to access from same lan
 * http://192.168.0.120:8080/Your_App_Name/some_path/some_file.jsp"
 * */
