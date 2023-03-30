package com.kh.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.app.member.vo.MemberVo;
import com.kh.app.util.JDBCTemplate;

public class MemberDao {
	
	
	//회원가입 SQL 쿼리문 실행
	public int join(Connection conn , MemberVo vo) throws Exception {
		int result = 0 ;
		//sql
		
		String sql = "INSERT INTO MEMBER (NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, ?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNick());
		result = pstmt.executeUpdate();
			//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public MemberVo selectOneByNo(Connection conn, String no) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		//3.tx||rs
		MemberVo vo= null;
		if(rs.next()) {
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			String nick = rs.getString("NICK");
			
			vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setNick(nick);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	public int edit(Connection conn, MemberVo vo) throws Exception {
		String sql = "UPDATE MEMBER SET PWD = ? , NICK = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getPwd());
		pstmt.setString(2, vo.getNick());
		pstmt.setString(3, vo.getNo());
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
		
	}
	//회원 탈퇴
	public int deleteByNo(Connection conn, String no) throws Exception {
		String sql = "DELETE MEMBER WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		
		//2.SQL 은 dao 클래스로
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		//3.TX || RS
		MemberVo loginMember =null;
		if(rs.next()) {
			String no = rs.getString("NO");
			String id = rs.getString("ID");
			String pwd = rs.getString("Pwd");
			String nick = rs.getString("Nick");
			
			loginMember = new MemberVo();
			loginMember.setNo(no);
			loginMember.setId(id);
			loginMember.setPwd(pwd);
			loginMember.setNick(nick);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return loginMember;
	}
}