package Functions;

import java.util.ArrayList;

public class oracleHandler {
		public static String insertSql(String tableName, ArrayList<String>columnName, ArrayList<String>columnValue){
			String str="INSERT INTO "+tableName+"(";
			for(int i=0;i<columnName.size();i++){
				if(i>0) str=str+",";
				str=str+columnName.get(i);
			}
			str= str+") VALUES (";
			for(int i = 0;i<columnValue.size();i++){
				if(i>0) str= str+",";
				str = str+ columnValue.get(i);
			}
			 str = str+")";
			System.out.println(str);
			return str;
		}
		
		public static String upadateSql(String tableName, String columnName, String columnValue){
			String str = "upadte "+ tableName+" set "+ columnName+" = "+columnValue;
			System.out.println(str);
			return str;
		}
		
		public static String deleteSql(String tableName){
			return "delete from "+tableName;
		}
		public static String addWhereCondition(String sql, ArrayList<String>columnName, ArrayList<String>columnValue){
			sql = sql +" where ";
			for(int i=0;i<columnName.size();i++){
				if(i>0) sql =sql+" and ";
				sql = sql+columnName.get(i)+" = "+columnValue.get(i);
			}
			return sql;
		}
}
