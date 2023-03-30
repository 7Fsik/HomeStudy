package com.kh.app.member.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp2.Jdbc41Bridge;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.util.JDBCTemplate;
@WebServlet("/member/edit")
public class MemberEditController extends HttpServlet {
	//회원정보 수정 화면
	//select * from member where no =?
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 데이터 준비 session에서 꺼내오기
			MemberVo loginMember = (MemberVo)req.getSession().getAttribute("loginMember");
			String no = loginMember.getNo();
			
			//서비스 로직 service 클래스에
			MemberService ms = new MemberService();
			MemberVo dbMember = ms.selectOneByNo(no);
			//화면
//			req.setAttribute("dbMember", dbMember);
			req.getRequestDispatcher("/WEB-INF/views/member/editForm.jsp").forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("[ERROR] 마이페이지 화면 조회 중 예외 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "마이페이지 화면 조회 중 예외 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
		
	}
	//회원 정보 수정
	//UPDATE MEMBER SET PWD = ? , NICK = ? WHERE NO = ?
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//데이터 준비
			String id = req.getParameter("memberId");
			String pwd = req.getParameter("memberPwd");
			String nick = req.getParameter("memberNick");
			
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
			String no = loginMember.getNo();
			MemberVo vo = new MemberVo();
			vo.setPwd(pwd);
			vo.setNick(nick);
			vo.setNo(no);
			//서비스 로직
			MemberService ms = new MemberService();
			int result = ms.edit(vo);
			
			//화면
			session.invalidate();//수정 후 재접속 해야됨
			resp.sendRedirect("/app22");
			
		} catch (Exception e) {
			System.out.println("[ERROR] 회원정보 수정 중 예외 발생..." );
			e.printStackTrace();
			req.setAttribute("errorMsg", "회원정보 수정 중 예외발생 . . .");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	
		
		
		
	}
}