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
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/index.jsp";
	}
	
	
	@RequestMapping("join")
	public String join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		return "/view/member/join.jsp";
		
	}
	
	
	@RequestMapping("joinInfo")
	public String joinInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id= (String) session.getAttribute("id");
		
		KicMemberDAO kcdao = new KicMemberDAO();
		KicMember mem = kcdao.getMember(id);
		request.setAttribute("mem", mem);
		
		return "/view/member/joinInfo.jsp";
	}
	
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("msg", "로그아웃 되었습니다");
		request.setAttribute("url", "index");
		return "/view/alert.jsp";
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
	
	
	@RequestMapping("memberUpdateForm")
	public String memberUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		KicMemberDAO dao = new KicMemberDAO();
		KicMember mem = dao.getMember(id); 
		request.setAttribute("mem", mem);
		return "/view/member/memberUpdateForm.jsp";
	}
	
	@RequestMapping("memberUpdatePro")
	public String memberUpdatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String picture = request.getParameter("picture");
		
		
		KicMemberDAO dao = new KicMemberDAO();
		KicMember memdb = dao.getMember(id);
		
		KicMember kic = new KicMember();
		kic.setId(id);
		kic.setPw(pw);
		kic.setName(name);
		kic.setGender(gender);
		kic.setTel(tel);
		kic.setEmail(email);
		kic.setPicture(picture);
		
		
		String msg="";
		String url="memberUpdateForm";
		if(memdb != null){
			if(memdb.getPw().equals(pw)){
				msg="수정하였습니다";
				dao.updateMember(kic);
				url = "joinInfo";
			} else{
				msg ="비번틀림";
			}
		}else{
			msg="수정할 수 없습니다";
		}

		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	}
	
	
	
	@RequestMapping("memberDeleteForm")
	public String memberDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/member/memberDeleteForm.jsp";
	}
	
	@RequestMapping("memberPassForm")
	public String memberPassForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/member/memberUpdateForm.jsp";
	}
}
