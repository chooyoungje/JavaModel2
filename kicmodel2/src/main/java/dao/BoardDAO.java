package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.KicBoard;

public class BoardDAO {

	
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
	
	public int insertBoard(KicBoard board) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into kicboard values(kicboardseq.nextval,?,?,?,?,?,sysdate,0,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getPw());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getFile1());
			pstmt.setString(6, "1");
			
			int num = pstmt.executeUpdate();
			return num;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;	
		}
		
		
	}
	
	
	public ArrayList<KicBoard> getAllBoard (){
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from kicboard";
		ArrayList<KicBoard> boardList = new ArrayList<KicBoard>();
		
			try {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next()) {
				KicBoard board = new KicBoard();
				int num = rs.getInt("num");
				String pw = rs.getString("pass");
				String name = rs.getString("name");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				Date date = rs.getDate("regdate");
				String boardId = rs.getString("boardid");
				int readcnt = rs.getInt("readcnt");
				
				board.setNum(num);
				board.setPw(pw);
				board.setName(name);
				board.setSubject(subject);
				board.setContent(content);
				board.setRegdate(date);
				board.setBoardid(boardId);
				board.setReadcnt(readcnt);
				boardList.add(board);
				}
				
				return boardList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}			

		}
		
	}
	
	
	

