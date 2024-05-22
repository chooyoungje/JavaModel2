package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.KicBoard;

@WebServlet("/board/*")
public class BoardController extends MskimRequestMapping{

	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/index.jsp";
	}
	
	
	@RequestMapping("boardForm")
	public String boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/board/boardForm.jsp";
	}
	
	
	@RequestMapping("boardPro")
	public String boardPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		KicBoard board = new KicBoard();
		
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String file = "";
		
		
		board.setName(name);
		board.setPw(pw);
		board.setSubject(subject);
		board.setContent(content);
		board.setFile1(file);
		
		
		int num = dao.insertBoard(board);
		String msg="";
		String url="boardForm";
		if(num>0) {
			msg="게시글 작성 성공";
			url="boardList";
		}else {
			msg="게시글 작성 실패";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";	
	}
	
	
	@RequestMapping("boardList")
	public String boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BoardDAO dao = new BoardDAO();
		KicBoard board = new KicBoard();
		
		ArrayList<KicBoard> boardList = dao.getAllBoard();
		request.setAttribute("boardList", boardList);
		return "/view/board/boardList.jsp";
	}
	

}
