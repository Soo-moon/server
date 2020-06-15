package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBconnection {

	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String user="moon";
			String pw ="1234";
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,pw);
			
			System.out.println("DB 연결");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: "+e.toString());
		}catch(SQLException e) {
			System.out.println("db 접속 실패: " + e.toString());
		}catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return conn;
	}
}
