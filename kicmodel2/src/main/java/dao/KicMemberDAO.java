package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.KicMember;

// DB와 관련된 작업을 모아놓은 클래스
public class KicMemberDAO {

	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = 
					DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic24", "1234");
			return conn;
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} // 2. connection
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public KicMember getMember(String id) {
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from kicmember where id =?";
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			String dbpw=null;
			if(rs.next()){
				// pw 가 있다
				KicMember kic = new KicMember();
				kic.setId(rs.getString("id"));
				kic.setPw(rs.getString("pw"));
				kic.setName(rs.getString("name"));
				kic.setGender(Integer.parseInt(rs.getString("gender")));
				kic.setTel(rs.getString("tel"));
				kic.setEmail(rs.getString("email"));
				kic.setPicture(rs.getString("picture"));
				
				return kic;
			}
			else {
				return null;
			}
					
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
public ArrayList<KicMember> getAllMember() {
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from kicmember";
		ArrayList<KicMember> memList = new ArrayList<>();
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
					KicMember mem = new KicMember();
					
					String id = rs.getString("id");
					String name = rs.getString("name");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					int gender = rs.getInt("gender");
					
					mem.setId(id);
					mem.setName(name);
					mem.setTel(tel);
					mem.setEmail(email);
					mem.setGender(gender);
					
					memList.add(mem);
			}
			return memList;
					
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public int insertMember(KicMember kic) {
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		
		String sql = "insert into kicmember values(?,?,?,?,?,?,?)";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,kic.getId());
			pstmt.setString(2,kic.getPw());
			pstmt.setString(3,kic.getName());
			pstmt.setInt(4,kic.getGender());
			pstmt.setString(5,kic.getTel());
			pstmt.setString(6,kic.getEmail());
			pstmt.setString(7,kic.getPicture());
			System.out.println(kic.getPicture());
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}			
	}
	
	
	public int updateMember(KicMember kic) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql =
		"update kicmember set name= ?, gender= ?, tel= ?, email= ?, picture=? where id= ?";
		//                                                       여기에 , 를 쓰면 안 된다
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kic.getName());
			pstmt.setInt(2, kic.getGender());
			pstmt.setString(3, kic.getTel());
			pstmt.setString(4, kic.getEmail());
			pstmt.setString(5, kic.getPicture());
			pstmt.setString(6, kic.getId());
			
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int deleteMember(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql =
		"delete from kicmember  where id= ?";
		//                                                       여기에 , 를 쓰면 안 된다
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int updatePassword(String id, String newPw) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql =
		"update kicmember set pw=? where id= ?";
		//                                                       여기에 , 를 쓰면 안 된다
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
