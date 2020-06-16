package human;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HumanDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public HumanDao() {
		try {
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "human";
			String pw ="human";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url,user,pw);
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int login(String userID,String userPassword) {
		String sql = "select userPassword from Human where userID=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				}
				else {
					return 0; //비번 불일치
				}
			}
			return -1; //아이디 없음
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -2;//DB오류
	}
}
