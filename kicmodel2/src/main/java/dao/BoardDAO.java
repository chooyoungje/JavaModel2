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
		String sql = "select * from kicboard order by num desc";
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
	public KicBoard getBoard (int num){
			
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			String sql = "select * from kicboard where num = ?";
			KicBoard board = new KicBoard();
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					ResultSet rs =  pstmt.executeQuery();
					while(rs.next()) {
				
					int dBnum = rs.getInt("num");
					String pw = rs.getString("pass");
					String name = rs.getString("name");
					String dbSubject = rs.getString("subject");
					String content = rs.getString("content");
					Date date = rs.getDate("regdate");
					String boardId = rs.getString("boardid");
					int readcnt = rs.getInt("readcnt");
					
					board.setNum(dBnum);
					board.setPw(pw);
					board.setName(name);
					board.setSubject(dbSubject);
					board.setContent(content);
					board.setRegdate(date);
					board.setBoardid(boardId);
					board.setReadcnt(readcnt);
					}
					
					return board;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}			

			}
	
	
	public int deleteBoard(int num) {
		Connection conn = getConnection();
		PreparedStatement pstmt= null;
		String sql = "delete from kicboard where num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate();
			return result;
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int updateBoard(KicBoard board) {
		Connection conn = getConnection();
		PreparedStatement pstmt= null;
		String sql = "update kicboard set name=?, pass=?, subject=?,content=?, file1=? where num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getPw());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getFile1());
			pstmt.setInt(6, board.getNum());
			
			int result = pstmt.executeUpdate();
			return result;
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	}
	
	
	

