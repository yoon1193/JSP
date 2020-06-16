package human;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DBconn {
	//.getInstance()
	private DBconn() {}
	private static Connection dbConn = null;
	private static Statement st = null;
	private static ResultSet rs = null;
	private static Scanner sc = new Scanner(System.in);
	
	public static ResultSet statementQuery(String sql) {

		try {
			if(st == null) {
				st = dbConn.createStatement();
			}
			rs = st.executeQuery(sql);
	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return rs;
	}
	
	public static int statementUpdate(String sql) {
		int resultValue=0;
		DBconn.getInstance(); //이미 밑에서 실행중.
		try {
			if(st == null) {
				st = dbConn.createStatement();
			}
			resultValue = st.executeUpdate(sql);
//			if(n==1) {//n은 변경된 개수를 의미함 여러개 업뎃이면 부등호 쓰셈
//				System.out.println("업데이트");
//			}
//			else {
//				System.out.println("업데이트x");
//			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//DBconn.dbClose();
		}
		
		return resultValue;
	}
	
	public static Connection getInstance() {
		if(dbConn == null) {
			try {
				//1. 사용할  DB Drive 등록
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("드라이버 로딩완료");
				//2. 해당 DB 접속
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "human";
				String pw ="human";
				dbConn = DriverManager.getConnection(url,user,pw);
				System.out.println("DB 접속");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dbConn;
	}
	public static void dbClose() {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close(); //닫을땐 역순으로
				if(dbConn != null) dbConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				rs = null;
				st = null;
				dbConn = null;
			}

			
	}
	//
	public static int inputInt() {
		System.out.println("정수입력>>");
		return Integer.parseInt(sc.nextLine());
	}
	public static double inputDouble() {
		System.out.println("실수입력>>");
		return Double.parseDouble(sc.nextLine());
	}
	public static String inputString() {
		System.out.println("문자열 입력>>");
		return sc.nextLine();
	}
	
	public static Date inputDate() {
		Date returnValue = null;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("날짜 입력  (yyyy-MM-dd HH:mm:ss)>>");
		String inputString = sc.nextLine();
		try {
			returnValue = transFormat.parse(inputString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public static String dateToString(Date d) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return df.format(d);
	}
}

