package com.kh.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;
@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/joinForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//데이터 꺼내기
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			String memberNick = req.getParameter("memberNick");
			
			//데이터 뭉치기
			MemberVo joinMember = new MemberVo();
			joinMember.setId(memberId);
			joinMember.setPwd(memberPwd);
			joinMember.setNick(memberNick);
			//Service 로직
			MemberService ms = new MemberService();
			int result = ms.join(joinMember);
			//화면
			if( result ==1) {
				req.setAttribute("msg", "회원가입 성공!");
			}else {
//				req.setAttribute("msg", "실패");
				throw new Exception("[MemberJoin Result0 ERROR] 회원가입 실패");
			}
			req.getRequestDispatcher("/WEB-INF/views/common/result.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println("[ERROR] 회원가입 진행 중 예외 발생");
			e.printStackTrace();
			//예외 하면 보여주기
			req.setAttribute("errorMsg", "회원가입 진행 중 예외 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
		
		
	}
}//class