package com.kh.model.dao;

import static com.kh.common.JDBCTemplate.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Member;

public class MemberDAO {

	private Properties prop = null;

	public MemberDAO() {
		try {
			prop = new Properties();
			prop.load(new FileReader("query.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Connection conn, Member member) {
		/*
		 * - testJDBC1 프로젝트에서 DAO가 했던 업무 1) JDBC 드라이버 등록 2) DB 연결 (Connection 객체 생성) 3)
		 * SQL 실행 4) 트랜잭션 처리 5) 자원 반납
		 * 
		 * - 실제로 DAO가 처리해야하는 업무 : 3번(SQL문을 DB로 전달하여 실행하고 반환 값 받아오기) --> 1, 2, 4, 5 번 업무
		 * 분담 : com.kh.common.JDBCTemplate 1, 2,
		 */
		PreparedStatement pstmt = null;
		int result = 0;

		// 쿼리도 프로퍼티 파일로 분리해서 만들기도 한다.
		String query = prop.getProperty("insertMember");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender() + "");
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setInt(8, member.getAge());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectAll(Connection conn) {
		Statement stmt = null;
		ArrayList<Member> mList = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectAll");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			mList = new ArrayList<Member>();
			Member member = null;

			// 무조건 컬럼명이 아니라...
			while (rset.next()) {
				
				//columnLabel
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");

				member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address, enrollDate);
				mList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rset);
			close(stmt);
		}

		return mList;
	}

	// 입력된 아이디가 포함된 모든 회원 정보 조회
	public ArrayList<Member> selectMemberId(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null;

		String query = prop.getProperty("selectMemberId");
		// SELECT * FROM MEMBER WHERE MEMBER_ID LIKE
		try {
			stmt = conn.createStatement();
			query += " '%" + id + "%'"; // LIKE '%id%'
			rset = stmt.executeQuery(query);
			mList = new ArrayList<Member>();

			while (rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");

				Member member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address,
						enrollDate);
				mList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return mList;
	}

	//
	public ArrayList<Member> selectGender(Connection conn, char gender) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null;

		String query = prop.getProperty("selectGender");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gender + "");
			rset = pstmt.executeQuery();

			mList = new ArrayList<Member>();

			while (rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gen = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");

				Member member = new Member(memberId, memberPwd, memberName, gen, email, phone, age, address,
						enrollDate);
				mList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return mList;

	}

	//
	public boolean checkMemberId(Connection conn, String id) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("checkMemberId");

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if (rset.next() && rset.getString("member_id").equals(id)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rset);
			close(pstmt);

		}

		return false;
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
//
//		try {
//			pstmt = conn.prepareStatement(query);
//			
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//		//	rs = stmt.executeQuery("SELECT * FROM MEMBER WHERE MEMBER_ID=" + id);
////
////			while (rs.next()) {
//				if (rs.next() && rs.getString("MEMBER_ID").equals(id)) {
//					
//					return true;
//				}
//		//	}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//			
//		//	close(conn);
//		}
//
//		return false;

//		Statement stmt = null;
//		ResultSet rs = null;
//		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID='" + id + "'";
//
//		try {
//			stmt = conn.createStatement();
//			
//			rs = stmt.executeQuery(query);
////
////			while (rs.next()) {
//				if (rs.next() && rs.getString("MEMBER_ID").equals(id)) {
//					
//					return true;
//				}
//		//	}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(stmt);
//			
//		//	close(conn);
//		}
//
//		return false;

	}

	public boolean checkMemberPw(Connection conn, String pw) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("checkMemberPw");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pw);
			rset = pstmt.executeQuery();

			if (rset.next() && rset.getString("MEMBER_PWD").equals(pw)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return false;
	}

	public int updateMember(Connection conn, String id, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateMember");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender() + "");
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setInt(7, member.getAge());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, id);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteMember(Connection conn, String id, String pwd) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteMember");
		
		//String query = "DELETE FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public int checkMember(Connection conn, String memberId) {
		// select ~~ from member where member_id = ?
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int check = 0;
		
		String query = prop.getProperty("checkMember");
		//checkMember = SELECT COUNT(*) FROM MEMBER WHERE_ID = ?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			// 1 or 0
			
			if(rset.next()) {
				// resultset의 컬럼명을 직접쓰거나
				// (index 순서가 바뀌어도 정확하게 나오게하고 싶을때)
				//check = rset.getInt("COUNT(*)");
		
				// 컬럼의 Index 위치를 쓰거나
				check = rset.getInt(1);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return check;
	}

	public int updateMember(Connection conn, String memberId, int sel, String input) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMember" + sel);
		// 비밀번호 : updateMember1
		// 이메일 : updateMember2
		// 전화번호 : updateMember3
		// 주소	 :  updateMember4
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, input);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
