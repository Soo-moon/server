package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shared.Player;

public class Oracle extends Thread {
	String data;
	ArrayList<Player> arraylist =new ArrayList<>();
	String id;
	public Oracle(String data,String id) {this.data = data;
	this.id=id;
	}

	public void run() {
		// TODO Auto-generated method stub
		String quary=data;
		Connection conn =null;
		PreparedStatement pstm = null;
		ResultSet rs =null;
		
		
		try {
			
			
			conn = (Connection) DBconnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				int uid = rs.getInt(1);
				String teamname = rs.getString(2);
				String name = rs.getString(3);
				String position = rs.getString(4);
				int season = rs.getInt(5);
				int power=rs.getInt(6);
				int condition=rs.getInt(7);
				int speed=rs.getInt(8);
				int health=rs.getInt(9);
				int control=rs.getInt(10);
				int ballspeed=rs.getInt(11);
				String a;
				
				if (position.equals("포수"))
					a="포수";
				else if(health == 0 )
					a="타자";
				else
					a="투수";
				
				Player player = new Player(teamname, name, position, season, power, condition, speed, health, control, ballspeed, a);
				arraylist.add(player);
					
			}
			System.out.println("DB 검색 완료");
		}catch (SQLException e) {
			System.out.println("select 문 예외 ");
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2.getMessage());
			}
		}
		
	}
	
	public ArrayList<Player> getarray(){
		return arraylist;
	}

}
