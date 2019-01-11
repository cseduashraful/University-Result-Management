package Functions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import stermip.*;

/*
 * to add a new marking sector
 * 1.call markDistributionSector.addSector(String column);
 * 2.Edit RegisterNewCourse.jsp page;
 * 3.Add new decoratedMds class
 * 6.Edit Functions in this class
 * 
 * */


public class mdsFactory {
		public static markDistributionSystem mdsWrapper(markDistributionSystem mds, String sector){
			if(sector.equalsIgnoreCase("final")) mds = new finalMarks(mds);
			else if(sector.equalsIgnoreCase("incourse")) mds = new incourseMarks(mds);
			else if(sector.equalsIgnoreCase("project")) mds = new projectMarks(mds);
			else if(sector.equalsIgnoreCase("assignment")) mds = new assignmentMarks(mds);
			else if(sector.equalsIgnoreCase("dailyEvaluation")) mds = new dailyEvaluationMarks(mds);
			else if(sector.equalsIgnoreCase("viva")) mds = new vivaMarks(mds);
			return mds;
		}
		
		public static boolean setPercentage(String markingSector[],int i, double value, double percentage[]){
			if(markingSector[i].equalsIgnoreCase("final")) percentage[0]=value;
			else if(markingSector[i].equalsIgnoreCase("incourse")) percentage[1]=value;
			else if(markingSector[i].equalsIgnoreCase("assignment")) percentage[2]=value;
			else if(markingSector[i].equalsIgnoreCase("project")) percentage[3]=value;
			else if(markingSector[i].equalsIgnoreCase("viva")) percentage[4]=value;
			else if(markingSector[i].equalsIgnoreCase("dailyEvaluation")) percentage[5]=value;
			return true;
		}
		public static PreparedStatement insertCourseHelper(course crs,Connection conn){
			
			 PreparedStatement sql_statement2 = null;
		        String jdbc_insert_sql2 = "INSERT INTO mds"
		            + "(course_id, FINAL, INCOURSE, ASSIGNMENT,PROJECT,VIVA, DAILYEVALUATION) VALUES"
		            + "(?,?,?,?,?,?,?)";
		        
		        try {
					sql_statement2= conn.prepareStatement(jdbc_insert_sql2);
					double percentage[] = new double[10];
					crs.getMds().getPercentage(percentage);
					sql_statement2.setString(1, crs.getCourseId());
					sql_statement2.setDouble(2,percentage[0] );
					sql_statement2.setDouble(3,percentage[1] );
					sql_statement2.setDouble(4,percentage[2] );
					sql_statement2.setDouble(5,percentage[3] );
					sql_statement2.setDouble(6,percentage[4] );
					sql_statement2.setDouble(7,percentage[5] );
					//sql_statement2.setDouble(8,0);
					return sql_statement2;
				} catch (SQLException e) {
					return null;
				}		
		}
		public static PreparedStatement insertMarksHelper(String reg_id, String courseId,
				String semName, int semSession, double[] percentage,Connection conn){
			
			 PreparedStatement sql_statement2 = null;
		        String jdbc_insert_sql2 = "INSERT INTO marks"
		            + "(course_id, FINAL, INCOURSE, ASSIGNMENT,PROJECT,VIVA, DAILYEVALUATION,semname,semsession,reg_id) VALUES"
		            + "(?,?,?,?,?,?,?,?,?,?)";
		        
		        try {
					sql_statement2= conn.prepareStatement(jdbc_insert_sql2);
					sql_statement2.setString(1, courseId);
					sql_statement2.setDouble(2,percentage[0] );
					sql_statement2.setDouble(3,percentage[1] );
					sql_statement2.setDouble(4,percentage[2] );
					sql_statement2.setDouble(5,percentage[3] );
					sql_statement2.setDouble(6,percentage[4] );
					sql_statement2.setDouble(7,percentage[5] );
					sql_statement2.setString(8,semName);
					sql_statement2.setInt(9,semSession);
					sql_statement2.setString(10,reg_id);
					
					//sql_statement2.setDouble(8,0);
					return sql_statement2;
				} catch (SQLException e) {
					return null;
				}		
		}
		public static markDistributionSystem extractMdsHelper(String courseId,ResultSet rs){
			markDistributionSystem mds = new concreteMarkDistributionSystem();
			try {
				
				while(rs.next()){
					double marks[] = new double[20];
					marks[0] = Double.parseDouble(rs.getString("FINAL"));
					if(marks[0]>0) mds = new finalMarks(mds);
					marks[1] = Double.parseDouble(rs.getString("INCOURSE"));
					if(marks[1]>0) mds = new incourseMarks(mds);
					marks[4] = Double.parseDouble(rs.getString("VIVA"));
					if(marks[4]>0) mds = new vivaMarks(mds);
					marks[5] = Double.parseDouble(rs.getString("DAILYEVALUATION"));
					if(marks[5]>0) mds = new dailyEvaluationMarks(mds);
					marks[2] = Double.parseDouble(rs.getString("ASSIGNMENT"));
					if(marks[2]>0) mds = new assignmentMarks(mds);
					marks[3] = Double.parseDouble(rs.getString("PROJECT"));
					if(marks[3]>0) mds = new projectMarks(mds);
					mds.setPercentage(marks);
				}
				return mds;
			}catch(Exception e){
				e.printStackTrace();
				return mds;
			}
		}
		public static markDistributionSystem extractMarksHelper(String courseId,ResultSet rs){
			markDistributionSystem mds = new concreteMarkDistributionSystem();
			try {
				
				while(rs.next()){
					double marks[] = new double[20];
					marks[0] = Double.parseDouble(rs.getString("FINAL"));
					if(marks[0]>0) mds = new finalMarks(mds);
					marks[1] = Double.parseDouble(rs.getString("INCOURSE"));
					if(marks[1]>0) mds = new incourseMarks(mds);
					marks[4] = Double.parseDouble(rs.getString("VIVA"));
					if(marks[4]>0) mds = new vivaMarks(mds);
					marks[5] = Double.parseDouble(rs.getString("DAILYEVALUATION"));
					if(marks[5]>0) mds = new dailyEvaluationMarks(mds);
					marks[2] = Double.parseDouble(rs.getString("ASSIGNMENT"));
					if(marks[2]>0) mds = new assignmentMarks(mds);
					marks[3] = Double.parseDouble(rs.getString("PROJECT"));
					if(marks[3]>0) mds = new projectMarks(mds);
					mds.insertMarks(marks);
				}
				return mds;
			}catch(Exception e){
				e.printStackTrace();
				return mds;
			}
		}
		public static void setMarks(String sector, double marks, double markArray[]){
			if(sector.equalsIgnoreCase("Final(%)")) markArray[0]=marks;
			else if(sector.equalsIgnoreCase("Incourse(%)")) markArray[1]=marks;
			else if(sector.equalsIgnoreCase("Assignment(%)")) markArray[2]=marks;
			else if(sector.equalsIgnoreCase("Project(%)")) markArray[3]=marks;
			else if(sector.equalsIgnoreCase("Viva(%)")) markArray[4]=marks;
			else if(sector.equalsIgnoreCase("Daily Eva(%)")) markArray[5]=marks;
			
		}
}
