package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KicMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.KicMember;

// http://localhost:8080/kicmodel2/member
@WebServlet("/member/*")
public class MemberController extends MskimRequestMapping{
	
	@RequestMapping("join")
	public String join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/member/join.jsp";
	}
	
	
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/member/login.jsp";
	}
	
	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		HttpSession session = request.getSession();
		
		KicMemberDAO kcdao = new KicMemberDAO();
		Connection conn = kcdao.getConnection();

		KicMember mem = kcdao.getMember(id);
	
		String msg = id+"님이 로그인을 했다";
		String url = "index";
		if(mem != null){
			if(pw.equals(mem.getPw())){
				// 비번도 일치한다 -> 세션에 저장하기
				session.setAttribute("id", id);
				msg = "로그인이 되었다";
			}else{
				msg = "비밀번호가 맞지 않다";
				url = "login";
			}
		}else{
			msg = "id를 확인하세요";
			url = "login";
		}
	
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	}
}
