package com.kh.app.member.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.Jdbc41Bridge;

import com.kh.app.dao.MemberDao;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.util.JDBCTemplate;

public class MemberService {
	
	//회원가입
	
	public int join(MemberVo vo) throws Exception {
		int result = 0;
		
		//1.conn (service 클래스에서 트랜잭션 처리 위해 여기서 conn 생성 및 close)
		Connection conn = JDBCTemplate.getConnection();
		
		//2.sql 은 memberdao 클래스에 join method 로
		MemberDao dao = new MemberDao();
		result = dao.join(conn, vo);
		
		//3.tx ||rs ->obj
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//4.close
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	//회원 1명 정보 고유 no 를 이용하여 조회
	public MemberVo selectOneByNo(String no) throws Exception {
		//1.conn
		Connection conn = JDBCTemplate.getConnection();
		//2.SQL 은 DAO로
		MemberDao dao = new MemberDao();
		MemberVo editMember = dao.selectOneByNo(conn, no);
		
		
		
		//4. close
		
		JDBCTemplate.close(conn);
		return editMember;
	}


	public int edit(MemberVo vo) throws Exception {
		
		//1.conn
		Connection conn = JDBCTemplate.getConnection();
		
		//2.sql dao로 넘겨서 처리
		MemberDao dao = new MemberDao();
		int result = dao.edit(conn, vo);
		
		//3. tx ||rs
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//4.close
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int deleteByNo(String no) throws Exception {
		
		//1. conn
		Connection conn = JDBCTemplate.getConnection();
		
		//2.sql dms dao로
		MemberDao dao = new MemberDao();
		int result = dao.deleteByNo(conn, no);
		//3.tx rs
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//4.close
		JDBCTemplate.close(conn);
		
		
		return result;
	}


	public MemberVo login(MemberVo vo) throws Exception {
		
		//1.CONN
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		
		MemberVo loginMember = dao.login(conn,vo);
		
		//4.CLOSE
		
		JDBCTemplate.close(conn);
		
		return loginMember;
	}

}