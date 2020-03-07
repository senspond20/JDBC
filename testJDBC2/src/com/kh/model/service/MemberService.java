package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

public class MemberService {

	// private MemberDAO dao = new MemberDAO();

	public int insertMember(Member member) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		int result = mDAO.insertMember(conn, member);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<Member> selectAll() {

		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		ArrayList<Member> mList = mDAO.selectAll(conn);
		return mList;
	}

	public ArrayList<Member> selectMemberId(String id) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		ArrayList<Member> mList = mDAO.selectMemberId(conn, id);
		return mList;
	}

	public ArrayList<Member> selectGender(char gender) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		ArrayList<Member> mList = mDAO.selectGender(conn, gender);

		return mList;
	}
	public boolean checkMemberId(String id) {

		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		boolean bool = mDAO.checkMemberId(conn, id);
		return bool;
	}

	public boolean checkMemberPw(String pw) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		boolean bool = mDAO.checkMemberPw(conn, pw);
		return bool;
		
	}

	public int updateMember(String id, Member member) {

		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		int result = mDAO.updateMember(conn, id, member);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;

	}

	public int deleteMember(String id, String pwd) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		int result = mDAO.deleteMember(conn, id, pwd);

		return result;

	}

	
}
