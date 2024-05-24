package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.KicMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.KicMember;

// http://localhost:8080/kicmodel2/member
@WebServlet("/member/*")
public class MemberController extends MskimRequestMapping{
	
	HttpSession session;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		System.out.println("service ");
		System.out.println("service "+session);
		super.service(request, response) ;
		
	}
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/index.jsp";
	}
	
	
	@RequestMapping("join")
	public String join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		request.setAttribute("nav", "join");
		return "/view/member/join.jsp";
	}
	
	@RequestMapping("joinPro")
	public String joinPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String picture = request.getParameter("picture");
		int gender = Integer.parseInt(request.getParameter("gender"));
		
		KicMemberDAO dao = new KicMemberDAO();
		String msg="";
		String url="join";
				
		KicMember mem = dao.getMember(id);
		if(mem == null) {
			KicMember newMem = new KicMember();
			newMem.setId(id);
			newMem.setPw(pw);
			newMem.setName(name);
			newMem.setEmail(email);
			newMem.setTel(tel);
			newMem.setGender(gender);
			newMem.setPicture(picture);
			
			int num = dao.insertMember(newMem);
			if(num>0) {
				msg="회원가입 성공";
				url = "login";
			}else {
				msg="회원가입 실패";
			}
		} else {
			msg="이미 존재하는 유저입니다";
		}

		request.setAttribute("msg",msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
		
	}
	
	
	@RequestMapping("joinInfo")
	public String joinInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id= (String) session.getAttribute("id");
		
		KicMemberDAO kcdao = new KicMemberDAO();
		KicMember mem = kcdao.getMember(id);
		System.out.println("사진 : "+mem.getPicture());
		request.setAttribute("mem", mem);
		request.setAttribute("nav", "joinInfo");
		return "/view/member/joinInfo.jsp";
	}
	
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session.invalidate();
		request.setAttribute("msg", "로그아웃 되었습니다");
		request.setAttribute("url", "index");
		return "/view/alert.jsp";
	}
	
	
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("nav", "login");
		return "/view/member/login.jsp";
	}
	
	
	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		
		KicMemberDAO kcdao = new KicMemberDAO();
		KicMember mem = kcdao.getMember(id);
	
		String msg = id+"님이 로그인을 했다";
		String url = "login";
		if(mem != null){
			if(pw.equals(mem.getPw())){
				// 비번도 일치한다 -> 세션에 저장하기
				session.setAttribute("id", id);
				msg = "로그인이 되었다";
				url = "joinInfo";
			}else{
				msg = "비밀번호가 맞지 않다";
			}
		}else{
			msg = "id를 확인하세요";
		}
	
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	}
	
	
	@RequestMapping("memberUpdateForm")
	public String memberUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = (String)session.getAttribute("id");
		KicMemberDAO dao = new KicMemberDAO();
		KicMember mem = dao.getMember(id); 
		request.setAttribute("mem", mem);
		return "/view/member/memberUpdateForm.jsp";
	}
	
	@RequestMapping("memberUpdatePro")
	public String memberUpdatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	
	@RequestMapping("memberDeletePro")
	public String memberDeletePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		KicMemberDAO dao = new KicMemberDAO();
		String msg="";
		String url="memberDeleteForm";
		String id = (String) session.getAttribute("id");
		String pw = request.getParameter("pw");
		KicMember mem = dao.getMember(id);
		if(mem!=null) {
			if(pw.equals(mem.getPw())) {
				dao.deleteMember(id);
				msg="회원탈퇴 성공";
				session.invalidate();
				url="login";
			}else {
				msg="비밀번호가 틀립니다";
			}
		}else {
			msg="오류 발생";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}
	
	@RequestMapping("memberPassForm")
	public String memberPassForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/view/member/memberPassForm.jsp";
	}
	
	@RequestMapping("memberPassPro")
	public String memberPassPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		KicMemberDAO dao = new KicMemberDAO();
		String msg="";
		String url="memberPassForm";
		String id = (String) session.getAttribute("id");
		String pw = request.getParameter("pw");
		String pw2 = request.getParameter("pw2");
		
		KicMember mem = dao.getMember(id);
		if(mem!=null) {
			if(pw.equals(mem.getPw())) {
				int num = dao.updatePassword(id,pw2);
				if(num>0) {
					msg="비밀번호 수정 성공";
					url="login";
					session.invalidate();
				}else {
					msg="비밀번호 수정 실패";
				}

			}else {
				msg="비밀번호가 틀립니다";
			}
		}else {
			msg="오류 발생";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}
	
	
	
	@RequestMapping("memberList")
	public String memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		KicMemberDAO dao = new KicMemberDAO();
		ArrayList<KicMember> memList = dao.getAllMember();
		request.setAttribute("memList", memList);
		return "/view/member/memberList.jsp";
	}
	
	@RequestMapping("pictureimgForm")
	public String pictureimgForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/single/pictureimgForm.jsp";
	}


	@RequestMapping("picturePro")
	public String picturePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletContext().getRealPath("/")+ "/img/member/picture";
		System.out.println(path);
		
		String filename = null;
		MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024,"UTF-8");
		filename = multi.getFilesystemName("picture");
		
		request.setAttribute("filename", filename);
		System.out.println(filename);
		return "/single/picturePro.jsp";
	}
	
	
	

}
