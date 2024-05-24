package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.BoardDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.KicBoard;

@WebServlet("/board/*")
public class BoardController extends MskimRequestMapping{

	
	HttpSession session;
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		System.out.println("service 호출 ");
		super.service(request, response) ;// 맨 아래에다가 써야된다
	}
	
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
		String path = request.getServletContext().getRealPath("/")+ "img/board/";
		MultipartRequest multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "UTF-8");
		String boardId = (String) session.getAttribute("boardId");
		
		request.setCharacterEncoding("utf-8");
		KicBoard kicboard = new KicBoard();
		kicboard.setBoardid(boardId);
		kicboard.setName(multi.getParameter("name"));
		kicboard.setPw(multi.getParameter("pw"));
		kicboard.setSubject(multi.getParameter("subject"));
		kicboard.setContent(multi.getParameter("content"));
		kicboard.setFile1(multi.getFilesystemName("file1"));
		
		System.out.println(multi.getFilesystemName("file1"));
		
		
		int num = dao.insertBoard(kicboard);
		String msg="";
		String url="boardForm";
		if(num>0) {
			msg="게시글 작성 성공";
			url="boardList?boardId="+boardId;
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
		String boardId = request.getParameter("boardId");
		System.out.println(boardId);
		
		int boardCount = dao.boardCount(boardId);
		
		String boardName = "";
		switch(boardId) {
		case "1": boardName = "공지사항";
			break;
		case "2": boardName = "자유게시판";
			break;
		case "3": boardName = "QnA";
			break;
		}
		
		ArrayList<KicBoard> boardList = dao.getAllBoard(boardId);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("boardName", boardName);
		request.setAttribute("boardCount", boardCount);
		session.setAttribute("boardId", boardId);
		
		return "/view/board/boardList.jsp";
	}
	
	
	@RequestMapping("boardInfo")
	public String boardInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		
		int result = dao.addReadCnt(num);

			// 조회수 1 늘리기 성공
		KicBoard board = dao.getBoard(num);
			
		System.out.println(board.getFile1());
		
		request.setAttribute("board", board);
		return "/view/board/boardInfo.jsp";
	}
	
	@RequestMapping("boardUpdateForm")
	public String boardUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BoardDAO dao = new BoardDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		KicBoard board = dao.getBoard(num);
		
		System.out.println("업데이트폼 파일 명 : " + board.getFile1());
		request.setAttribute("board", board);
		
		return "/view/board/boardUpdateForm.jsp";
	}
	
	
	@RequestMapping("boardUpdatePro")
	public String boardUpdatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		String path = request.getServletContext().getRealPath("/")+ "img/board/";
		MultipartRequest multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "UTF-8");
		String boardId = (String) session.getAttribute("boardId");
		
		request.setCharacterEncoding("utf-8");
		KicBoard kicboard = new KicBoard();
		kicboard.setBoardid(boardId);
		kicboard.setName(multi.getParameter("name"));
		kicboard.setPw(multi.getParameter("pw"));
		kicboard.setSubject(multi.getParameter("subject"));
		kicboard.setContent(multi.getParameter("content"));
		
		
		if(multi.getFilesystemName("file1") != null) {
			kicboard.setFile1(multi.getFilesystemName("file1"));
		} else {
			kicboard.setFile1(multi.getParameter("originFile"));
		}
		
  
		System.out.println(multi.getFilesystemName("file1"));
		
		int result = dao.updateBoard(kicboard);
		
		String msg = "";
		String url="boardUpdateForm"; 
		
		if(result>0) {
			msg = "게시글 수정 완료";
			url="boardInfo?num="+kicboard.getNum();
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
		String boardId = (String)session.getAttribute("boardId");
		
		
		String msg = "";
		String url="boardInfo";
		
		if(pw.equals(dbBoard.getPw())) {
			int result = dao.deleteBoard(num);
			if(result >0) {
				msg="성공적으로 삭제하였습니다";
				url="boardList?boardId="+boardId;
				
			}else {
				msg="게시글 삭제 실패";
				url="boardInfo?num="+num;
			}
		}
		session.setAttribute("boardId", boardId);
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}
	

}
