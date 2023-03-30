package com.kh.app.member.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.util.JDBCTemplate;
@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			//데이터 꺼내기
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			
			//데이터 뭉치기
			MemberVo vo = new MemberVo();
			vo.setId(memberId);
			vo.setPwd(memberPwd);
			
			//서비스 로직 service 클래스로
			MemberService ms = new MemberService();
			MemberVo loginMember = ms.login(vo);
			
			
			//화면
			if(loginMember != null) {
				
				req.getSession().setAttribute("loginMember", loginMember);
				resp.sendRedirect("/app22");
			}else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			System.out.println("[ERROR] 로그인 중 예외 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "로그인 중 예외 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}
}