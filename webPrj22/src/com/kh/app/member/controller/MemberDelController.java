package com.kh.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;
@WebServlet("/member/del")
public class MemberDelController extends HttpServlet {
	
	//회원탈퇴
	//DELETE MEMBER WHERE NO = ?;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//데이터 준비
			MemberVo loginMember = loginMember = (MemberVo)req.getSession().getAttribute("loginMember");
			String no = loginMember.getNo();
			//서비스 로직 service 클래스에서
			
			MemberService ms = new MemberService();
			int result = ms.deleteByNo(no);
			
			//화면
			if(result ==1) {
				req.getSession().invalidate();
				resp.sendRedirect("/app22");
			}else {
				throw new Exception("[MemberJoin Result0 ERROR] 회원탈퇴 실패");
			}
		
		}catch(Exception e){
			System.out.println("[ERROR] 회원 탈퇴 중 에러 발생 . . .");
			e.printStackTrace();
			req.setAttribute("errorMsg", "회원 탈퇴 중 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	
	}
	
}//class