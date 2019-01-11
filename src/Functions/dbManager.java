package Functions;
import stermip.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class dbManager {
	//private static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	//static final String DB_URL = "jdbc:oracle:thin:@localhost:8080:orcl";
	private static String DB_URL="jdbc:oracle:thin:@//127.0.0.1:1521/XE";
	//static final String DB_URL = "jdbc:oracle:thin:@http://127.0.0.1:8080/apex/f?p=4550:11:244036022536119::NO::::orcl";
	private static String USER = "result2"; 
	private static String PASS = "result2";
	private static Connection conn = null; 
	private static Statement stmt = null; 
	private static String sql;
	public dbManager(){
		try{ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Registered");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection Established");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void establishConnection(){
		try{ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Registered");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection Established");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean deleteCtAssignment(String semName, int semSession, String courseId){
		try{
			establishConnection();
			stmt = conn.createStatement();
			sql = "delete from ctAssignment where course_id=";
			sql+=tableValueFixer.oracleValue(courseId);
			sql+= " and semName="+tableValueFixer.oracleValue(semName);
			sql+= " and semSession="+semSession;
			System.out.println(sql);
			stmt.execute(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public static int profilePhotoSerial(String reg_id){
		try{
			establishConnection();
			stmt = conn.createStatement();
			sql = "select serial from photos where reg_id='"+reg_id+"' and status ='current'" ;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			int sr=0;
			while(rs.next()){
				sr=rs.getInt("serial");
			}
			return sr;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean updatePictureStatus(String reg_id, int serial, String newStatus){
		try{
			establishConnection();
			stmt = conn.createStatement();
			sql = "update photos set status='"+newStatus+"' where reg_id='"+reg_id+"' and serial="+serial;
			stmt.executeQuery(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static boolean deletePhoto(String reg_id, int serial){
		try{
			establishConnection();
			stmt = conn.createStatement();
			sql = "delete from photos where reg_id='"+reg_id+"' and serial="+serial;
			stmt.executeQuery(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static ArrayList<String> fetchAuserNames(){
		try{
			ArrayList<String>userNames = new ArrayList<String>();
			establishConnection();
			stmt = conn.createStatement();
			sql = "select reg_id from users";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				userNames.add(rs.getString("reg_id"));
			}
			return userNames;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<semester> fetchSemestersWhereStatusIs(String semStatus){
		try{
			ArrayList <semester> sems = new ArrayList<semester>();
			stmt = conn.createStatement();
			sql = "select * from semester where semStatus='"+semStatus+"'" ;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String semName = rs.getString("semName");
				int semSession = rs.getInt("semSession");
				semester sem = new semester(semName, semSession, semStatus);
				sems.add(sem);
			}
			return sems;
		}catch(Exception e){
			System.out.println("Exception in fetchSemesterwhereStatusIs");
			return null;
		}
	}
	
	public static ArrayList<ssAssignment> fetchSsAssignment(String reg_id){
		try{
			ArrayList<ssAssignment> asts= new ArrayList<ssAssignment>();
			stmt = conn.createStatement();
			sql = "select * from ssAssignment where reg_id='"+reg_id+"'" ;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ssAssignment ast;
				String semName = rs.getString("semName");
				int semSession = rs.getInt("semSession");
				double gpa = rs.getDouble("gpa");
				double obtainedCredit = rs.getDouble("obtainedCredit");
				String ssStatus = rs.getString("ssStatus");
				ast = new ssAssignment(semName, semSession, ssStatus, gpa, obtainedCredit);
				asts.add(ast);
			}
			return asts;
		}catch(Exception e){
			System.out.println("Exception in fecthSsAssignment");
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList<String> fetchStudentsOf(String semName,int semSession){
		try{
			ArrayList<String> asts= new ArrayList<String>();
			stmt = conn.createStatement();
			sql = "select reg_id from ssAssignment where semName='"+semName+"' and semSession="+semSession;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String reg_id = rs.getString("reg_id");
				asts.add(reg_id);
			}
			return asts;
		}catch(Exception e){
			System.out.println("Exception in fetchStudentsOf");
			e.printStackTrace();
			return null;
		}
	}

	
	public static semester fetchSemester(String semName, int semSession){
		try{
			ArrayList<ctAssignment>cts = dbManager.fetchCtAssignment(semName, semSession);
			ArrayList<students>stus =dbManager.fetchSemStudents(semName, semSession);
			stmt = conn.createStatement();
			sql = "select * from semester where semName='"+semName+"' and semSession="+semSession;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String semStatus = rs.getString("semStatus");
				return new semester(semName, semSession, semStatus, cts, stus);
			}
		
		}catch(Exception ex){
			System.out.println("Exception in fetch semester");
			ex.printStackTrace();
		}
		return null;
	}
	
	
    public static ArrayList<ctAssignment> fetchCtAssignment(String semName, int semSession){
    	establishConnection();
    	try{
    		ArrayList<ctAssignment>cts = new ArrayList<ctAssignment>();
    		stmt = conn.createStatement();
			sql = "select * from ctAssignment where semName='"+semName+"' and semSession="+semSession;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String reg_id, course_id, isResultSubmitted;
				boolean isResultSubmittedBool;
				reg_id = rs.getString("reg_id");
				course_id=rs.getString("course_id");
				isResultSubmitted= rs.getString("isResultSubmitted");
				if(isResultSubmitted.equalsIgnoreCase("true")) isResultSubmittedBool = true;
				else isResultSubmittedBool = false;
				course crs = dbManager.fetchCourse(course_id);
				System.out.println(crs.getCourseName());
				teacher tch = (teacher) dbManager.fetchDeptTeacher(reg_id);
				System.out.println(tch.getName());
				ctAssignment ct = new ctAssignment(crs, tch, isResultSubmittedBool);
				cts.add(ct);
			}
			return cts;
    	}catch(Exception e){
    		System.out.println("Exception in getCtAssignment");
    		e.printStackTrace();
    		return null;
    	}
    	
    }
    public static ArrayList<csAssignment> fetchCsAssignment(String reg_id){
    	establishConnection();
    	try{
    		ArrayList<csAssignment>css = new ArrayList<csAssignment>();
    		stmt = conn.createStatement();
			sql = "select * from ctAssignment where reg_id='"+reg_id+"'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String semName, course_id, isResultSubmitted;
				int semSession;
				boolean isResultSubmittedBool;
				semName = rs.getString("semName");
				semSession = rs.getInt("semSession");
				course_id=rs.getString("course_id");
				isResultSubmitted= rs.getString("isResultSubmitted");
				if(isResultSubmitted.equalsIgnoreCase("true")) isResultSubmittedBool = true;
				else isResultSubmittedBool = false;
				course crs = dbManager.fetchCourse(course_id);
				semester sem  = dbManager.fetchSemester(semName, semSession);
				System.out.println(crs.getCourseName());
				csAssignment ct = new csAssignment(sem,crs, isResultSubmittedBool);
				css.add(ct);
			}
			return css;
    	}catch(Exception e){
    		System.out.println("Exception in getCsAssignment");
    		e.printStackTrace();
    		return null;
    	}
    	
    }
    public static ArrayList<students> fetchSemStudents(String semName, int semSession){
    	establishConnection();
    	try{
    		ArrayList<students>stus = new ArrayList<students>();
    		stmt = conn.createStatement();
			sql = "select * from ssAssignment where semName='"+semName+"' and semSession="+semSession;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String reg_id = rs.getString("reg_id");
				students stu =(students) dbManager.fetchUser(reg_id);
				stus.add(stu);
			}
			return stus;
    	}catch(Exception ex){
    		System.out.println("Exception in fetchSemStudents");
    		ex.printStackTrace();
    		return null;
    	}
    }
    
    
	public static ArrayList<teacher> fetchDeptTeacherList(){
		establishConnection();
		try{
			ArrayList<teacher> teacherArray = new ArrayList<teacher>();
			stmt = conn.createStatement();
			sql = "select * from users where role='teacher'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				teacher tch = (teacher)userFactory.getInstanceOfUserFactory().resultSetToUser(rs);
				teacherArray.add(tch);
			}
			return teacherArray;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	public static teacher fetchDeptTeacher(String reg_id){
		establishConnection();
		try{
			stmt = conn.createStatement();
			sql = "select * from users where reg_id='" +reg_id+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				teacher tch = (teacher)userFactory.getInstanceOfUserFactory().resultSetToUser(rs);
				return tch;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
		
	}
	public static users fetchUser(String reg_id){
		establishConnection();
		try{
			stmt = conn.createStatement();
			sql = "select * from users where reg_id='" +reg_id+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				users tch = userFactory.getInstanceOfUserFactory().resultSetToUser(rs);
				return tch;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
		
	}
	
	
	private static course ResultSetToCourse(ResultSet rs){
		try{
			course crs = new course();
			crs.setCourseId(rs.getString("Course_Id"));
			crs.setCourseName(rs.getString("Course_Name"));
			crs.setCredit(Double.parseDouble(rs.getString("Credit")));
			crs.setCourseArea(rs.getString("area"));
			crs.setType(rs.getString("course_type"));
			crs.setMds(extractMds(crs.getCourseId()));
			return crs;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static course fetchCourse(String course_id){
		establishConnection();
		try{
			stmt = conn.createStatement();
			sql = "select * from course where course_id='"+course_id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				return ResultSetToCourse(rs);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
		
	}
	
	
	
	
	public static ArrayList<course> fetchCourseList(){
		establishConnection();
		try{
			ArrayList<course> courseArray = new ArrayList<course>();
			stmt = conn.createStatement();
			sql = "select * from course";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				courseArray.add(ResultSetToCourse(rs));
			}
			return courseArray;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static boolean activate(String userId,String code, int password){
		establishConnection();
		try {
			stmt = conn.createStatement();
			sql = "select * from users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String reg_id = rs.getString("reg_id");
				System.out.println("received: "+reg_id+".\nrequired: "+userId);
				if(reg_id.equals(userId)){
					System.out.println("User Found");
					String code1 = rs.getString("code");
					String role = rs.getString("role");
					String status = rs.getString("status");
					if(code1.equals(code)){
						
						if(status.equals("pending")) {
							return makeActivated(reg_id, password, "emailConfirmed");
						}
						
						return makeActivated(reg_id, password);
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		System.out.println("User not found");
		return false;
	}
	public static users login(String userId,String password){
		establishConnection();
		try {
			stmt = conn.createStatement();
			sql = "select * from users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String reg_id = rs.getString("reg_id");
				System.out.println("received: "+reg_id+".\nrequired: "+userId);
				if(reg_id.equals(userId)){
					System.out.println("User Found");
					String pass = rs.getString("password");
					if(pass.equals(password)){
						return userFactory.getInstanceOfUserFactory().resultSetToUser(rs);
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		System.out.println("User not found");
		return null;
	}
	
	
	public static String emai(String userId){
		establishConnection();
		try {
			stmt = conn.createStatement();
			sql = "select * from users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String reg_id = rs.getString("reg_id");
				System.out.println("received: "+reg_id+".\nrequired: "+userId);
				if(reg_id.equals(userId)){
					System.out.println("User Found");
					String email = rs.getString("email");
					return email;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		System.out.println("User not found");
		return null;
	}
	
	public static String status(String userId){
		establishConnection();
		try {
			stmt = conn.createStatement();
			sql = "select * from users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String reg_id = rs.getString("reg_id");
				System.out.println("received: "+reg_id+".\nrequired: "+userId);
				if(reg_id.equals(userId)){
					System.out.println("User Found");
					String email = rs.getString("status");
					return email;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		System.out.println("User not found");
		return null;
	}
	
	
	private static boolean makeActivated(String reg_id, int code){
		establishConnection();
		try{
			stmt =conn.createStatement();
			sql="update users set status='activated', password="+code+" where reg_id='"+reg_id+"'";
			System.out.println(sql);
			stmt.executeQuery(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean makeActivated(String reg_id, int code, String status){
		establishConnection();
		try{
			stmt =conn.createStatement();
			sql="update users set status='"+status+"', password="+code+" where reg_id='"+reg_id+"'";
			System.out.println(sql);
			stmt.executeQuery(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public static boolean updateCode(String userId, int code){
		establishConnection();
		try{
			stmt =conn.createStatement();
			sql="update users set code="+code+" where reg_id='"+userId+"'";
			System.out.println(sql);
			stmt.executeQuery(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public static boolean updateSemStatus(semester sem){
		establishConnection();
		try{
			stmt =conn.createStatement();
			sql="update semester set semStatus='"+sem.getSemStatus()+"' where semName='"+sem.getSemName()+"' and semSession="+sem.getSemSession();
			System.out.println(sql);
			stmt.executeQuery(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public static boolean insertUser(users user){
		try{
			establishConnection(); 
	        PreparedStatement sql_statement = null;
	        String jdbc_insert_sql = "INSERT INTO users"
	            + "(REG_ID,NAME,ROLE,DOB,EMAIL,MOB,SERIAL,PASSWORD,YEAR_OF_REGISTRATION,STATUS,CODE) VALUES"
	            + "(?,?,?,?,?,?,?,?,?,'deactivated',?)";
	        sql_statement = conn.prepareStatement(jdbc_insert_sql);
			sql_statement.setString(1,user.getReg_id());
			sql_statement.setString(2,user.getName());
			sql_statement.setString(3,user.getRole());
			java.sql.Date sqlDate = new java.sql.Date(user.getDob().getTime());
			sql_statement.setDate(4, sqlDate);
			sql_statement.setString(5,user.getEmail());
			sql_statement.setString(6,user.getMob());
			Random rn = new Random();
			if(user.getRole().equalsIgnoreCase("student")) sql_statement.setInt(7,((students)user).getSerial());
			else sql_statement.setInt(7,rn.nextInt());
			sql_statement.setInt(8,rn.nextInt());
			sql_statement.setInt(9,user.getYearOfRegistration());
			sql_statement.setInt(10,rn.nextInt());
			sql_statement.execute();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean insertUser(users user, String status){
		try{
			establishConnection(); 
	        PreparedStatement sql_statement = null;
	        String jdbc_insert_sql = "INSERT INTO users"
	            + "(REG_ID,NAME,ROLE,DOB,EMAIL,MOB,SERIAL,PASSWORD,YEAR_OF_REGISTRATION,STATUS,CODE) VALUES"
	            + "(?,?,?,?,?,?,?,?,?,?,?)";
	        sql_statement = conn.prepareStatement(jdbc_insert_sql);
			sql_statement.setString(1,user.getReg_id());
			sql_statement.setString(2,user.getName());
			sql_statement.setString(3,user.getRole());
			java.sql.Date sqlDate = new java.sql.Date(user.getDob().getTime());
			sql_statement.setDate(4, sqlDate);
			sql_statement.setString(5,user.getEmail());
			sql_statement.setString(6,user.getMob());
			Random rn = new Random();
			if(user.getRole().equalsIgnoreCase("student")) sql_statement.setInt(7,((students)user).getSerial());
			else sql_statement.setInt(7,rn.nextInt());
			sql_statement.setInt(8,rn.nextInt());
			sql_statement.setInt(9,user.getYearOfRegistration());
			sql_statement.setString(10,status);
			sql_statement.setInt(11,rn.nextInt());
			sql_statement.execute();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public static boolean insertSsAssignment(String reg_id, String semName, int semSession,String  ssStatus, double gpa, double obtainedCredit){
		try{
			establishConnection(); 
	        PreparedStatement sql_statement = null;
	        String jdbc_insert_sql = "INSERT INTO ssAssignment"
	            + "(reg_id,semName,semSession,ssStatus,gpa,obtainedCredit) VALUES"
	            + "(?,?,?,?,?,?)";
	        sql_statement = conn.prepareStatement(jdbc_insert_sql);
			sql_statement.setString(1,reg_id);
			sql_statement.setString(2,semName);
			sql_statement.setInt(3, semSession);
			sql_statement.setString(4,ssStatus);
			sql_statement.setDouble(5, gpa);
			sql_statement.setDouble(6, obtainedCredit);
			sql_statement.execute();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	
	
	public static boolean insertSemester(semester sem){
		try{
			establishConnection(); 
	        PreparedStatement sql_statement = null;
	        String jdbc_insert_sql = "INSERT INTO semester"
	            + "(semName,semSession,semStatus) VALUES"
	            + "(?,?,?)";
	        sql_statement = conn.prepareStatement(jdbc_insert_sql);
			sql_statement.setString(1,sem.getSemName());
			sql_statement.setInt(2, sem.getSemSession());
			sql_statement.setString(3, sem.getSemStatus());
			sql_statement.execute();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static boolean insertCtAssignment(semester sem, ctAssignment ct){
		try{
			establishConnection(); 
	        PreparedStatement sql_statement = null;
	        String jdbc_insert_sql = "INSERT INTO ctAssignment"
	            + "(semName,semSession,COURSE_ID, REG_ID, ISRESULTSUBMITTED) VALUES"
	            + "(?,?,?,?,?)";
	        
	        sql_statement = conn.prepareStatement(jdbc_insert_sql);
			sql_statement.setString(1,sem.getSemName());
			sql_statement.setInt(2, sem.getSemSession());
			sql_statement.setString(3,ct.getCrs().getCourseId());
			sql_statement.setString(4, ct.getTch().getReg_id());
			if(ct.isResultSubmitted())sql_statement.setString(5, "true");
			else sql_statement.setString(5, "false");
			sql_statement.execute();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	

	public static boolean addSector(String column) {
		// TODO Auto-generated method stub
		establishConnection();
		try{
			stmt = conn.createStatement();
			sql = "alter table mds add ( "+column+" number(10,0) null)";
			System.out.println(sql);
			stmt.executeQuery(sql);			
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public static boolean deleteSector(String column) {
		// TODO Auto-generated method stub
		establishConnection();
		try{
			stmt = conn.createStatement();
			sql = "alter table MDS drop column "+column;
			System.out.println(sql);
			stmt.executeQuery(sql);			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean insertCourse(course crs){
		establishConnection(); 
        PreparedStatement sql_statement = null;
        String jdbc_insert_sql = "INSERT INTO course"
            + "(course_id, course_name, credit, area, course_type) VALUES"
            + "(?,?,?,?,?)";
       
        try {
        	sql_statement = conn.prepareStatement(jdbc_insert_sql);
			sql_statement.setString(1,crs.getCourseId());
			sql_statement.setString(2,crs.getCourseName());
			sql_statement.setDouble(3,crs.getCredit());
			sql_statement.setString(4, crs.getCourseArea());
			sql_statement.setString(5, crs.getType());
			sql_statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 PreparedStatement sql_statement2 = null; 
	        try {
	        	sql_statement2 = mdsFactory.insertCourseHelper(crs, conn);
				sql_statement2.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				return false;
			}		
		return true;
	}
	private static markDistributionSystem extractMds(String courseId){
		markDistributionSystem mds = new concreteMarkDistributionSystem();
		establishConnection();
		try {
			stmt = conn.createStatement();
			sql = "select * from mds where course_id='"+courseId+"'";
			ResultSet rs = stmt.executeQuery(sql);
			return mdsFactory.extractMdsHelper(courseId, rs);
		}catch(Exception e){
			e.printStackTrace();
			return mds;
		}
	}
	public static markDistributionSystem extractMarks(String courseId, String reg_id, String semName, int semSession){
		markDistributionSystem mds = new concreteMarkDistributionSystem();
		establishConnection();
		try {
			stmt = conn.createStatement();
			sql = "select * from marks where course_id='"+courseId+"' and reg_id = '"+reg_id+"' and semName='"+semName+"' and semSession="+semSession;
			ResultSet rs = stmt.executeQuery(sql);
			mds=mdsFactory.extractMarksHelper(courseId, rs);
			course crs = dbManager.fetchCourse(courseId);
			double percentage[] = new double[20];
			crs.getMds().getPercentage(percentage);
			mds.setPercentage(percentage);
			return mds;
		}catch(Exception e){
			e.printStackTrace();
			return mds;
		}
	}
	
	public static ArrayList<String> getColumnNamesOf(String table){
		establishConnection();
		ArrayList<String>columns=new ArrayList<String>();
		try {
			table= table.toUpperCase();
			stmt = conn.createStatement();
			sql = "select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='"+table+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				columns.add(rs.getString("COLUMN_NAME"));
			}
			return columns;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static void defaultPhoto(String reg_id, int sr) {
		// TODO Auto-generated method stub
		try{
			establishConnection();
			stmt = conn.createStatement();
			sql = "delete from photos where serial=0";
			stmt.executeQuery(sql);
			sql = "update photos set serial=0 where reg_id='"+reg_id+"' and serial="+sr;
			stmt.executeQuery(sql);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static boolean insertMarks(String reg_id, String courseId,
			String semName, int semSession, double[] markArray) {
		// TODO Auto-generated method stub
		try{
			establishConnection(); 
	        PreparedStatement sql_statement = mdsFactory.insertMarksHelper(reg_id, courseId, semName, semSession, markArray, conn);
			sql_statement.execute();
	        return true;
		}catch(Exception e){
			return false;
		}
	}

	public static void updateCtAssignment(String semName, int semSession,
			String courseId) {
		// TODO Auto-generated method stub
		establishConnection();
		try{
			stmt =conn.createStatement();
			sql="update ctAssignment set ISRESULTSUBMITTED='true' where semName='"+semName+"' and semSession="+semSession+" and course_id='"+courseId+"'";
			System.out.println(sql);
			stmt.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
