package Functions;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.*;
import java.sql.*; 
public class xlsToOracle {  
	
	//private static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	//static final String DB_URL = "jdbc:oracle:thin:@localhost:8080:orcl";
	private static String DB_URL="jdbc:oracle:thin:@//127.0.0.1:1521/XE";
	//static final String DB_URL = "jdbc:oracle:thin:@http://127.0.0.1:8080/apex/f?p=4550:11:244036022536119::NO::::orcl";
	private static String USER = "result2"; 
	private static String PASS = "result2";
	private static Connection conn = null; 
	//private static Statement stmt = null; 
	//private static String sql;
	private static void establishConnection(){
		try{ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Registered");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection Established");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
        public static void convert(FileInputStream input_document) throws Exception{                
                /* Create Connection objects */
            establishConnection(); 
            PreparedStatement sql_statement = null;
            String jdbc_insert_sql = "INSERT INTO users"
                + "(name, reg_id, role, dob, email,mob, serial, password, year_of_registration, status,code) VALUES"
                + "(?,?,?,null,?,null,?,null,?,'deactivated',null)";
            sql_statement = conn.prepareStatement(jdbc_insert_sql);
            HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);
            HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
            Iterator<Row> rowIterator = my_worksheet.iterator(); 
            while(rowIterator.hasNext()) {
                    Row row = rowIterator.next(); 
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int i=1;
                            while(cellIterator.hasNext()) {
                                    Cell cell = cellIterator.next();
                                    switch(cell.getCellType()) { 
                                    case Cell.CELL_TYPE_STRING: //handle string columns
                                            {
                                            	sql_statement.setString(i, cell.getStringCellValue());
                                            	System.out.println(cell.getStringCellValue());
                                            	
                                            }
                                            break;
                                    case Cell.CELL_TYPE_NUMERIC: //handle double data
                                            sql_statement.setDouble(i,cell.getNumericCellValue() );
                                            break;
                                    }
                                    i++;  
                            }
            //we can execute the statement before reading the next row
           sql_statement.executeUpdate();
                           // System.out.println(sql_statement.toString());
            }
            /* Close input stream */
            input_document.close();
            /* Close prepared statement */
            sql_statement.close();
            /* COMMIT transaction */
            conn.commit();
            /* Close connection */
            conn.close();
            

        }
}
