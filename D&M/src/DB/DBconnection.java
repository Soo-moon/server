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
			
			System.out.println("DB ����");
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����: "+e.toString());
		}catch(SQLException e) {
			System.out.println("db ���� ����: " + e.toString());
		}catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return conn;
	}
}
