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
	
	
	@RequestMapping("boardInfo")
	public String boardInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		KicBoard board = dao.getBoard(num);
		request.setAttribute("board", board);
		return "/view/board/boardInfo.jsp";
	}
	
	@RequestMapping("boardUpdateForm")
	public String boardUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BoardDAO dao = new BoardDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		KicBoard board = dao.getBoard(num);
		
		request.setAttribute("board", board);
		
		return "/view/board/boardUpdateForm.jsp";
	}
	
	
	@RequestMapping("boardUpdatePro")
	public String boardUpdatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String file1 = request.getParameter("file1");
		
		KicBoard board = new KicBoard();
		board.setNum(num);
		board.setName(name);
		board.setSubject(subject);
		board.setContent(content);
		board.setFile1(file1);
		
		int result = dao.updateBoard(board);
		
		String msg = "";
		String url="boardUpdateForm"; 
		
		if(result>0) {
			msg = "게시글 수정 완료";
			url="boardInfo?num="+num;
		}else {
			msg = "게시글 수정 실패";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
		
		
	}
	
	
	
	@RequestMapping("boardDeleteForm")
	public String boardDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int num = Integer.parseInt(request.getParameter("num"));

		request.setAttribute("num", num);
		return "/view/board/boardDeleteForm.jsp";
	}
	
	
	@RequestMapping("boardDeletePro")
	public String boardDeletePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		String pw = request.getParameter("pw");
		KicBoard dbBoard = dao.getBoard(num);
		
		String msg = "";
		String url="boardInfo";
		
		if(pw.equals(dbBoard.getPw())) {
			int result = dao.deleteBoard(num);
			if(result >0) {
				msg="성공적으로 삭제하였습니다";
				url="boardList";
				
			}else {
				msg="게시글 삭제 실패";
				url="boardInfo?num="+num;
			}
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}
	

}
