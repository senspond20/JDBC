package com.kh.controller;

import static com.kh.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.MemberDAO;
import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {

	private MemberMenu menu = new MemberMenu();
	// private MemberDAO md = new MemberDAO();
	private MemberService service = new MemberService();

	public void insertMember() {

		Member member = menu.insertMember();
		int result = service.insertMember(member);
		if (result > 0) {
			menu.displaySuccess(result + "개의 행이 추가되었습니다.");
		} else {
			menu.displayError("데이터 삽입 과정 중 오류 발생");
		}

	}

	public ArrayList<Member> selectAll() {

		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();

		ArrayList<Member> mlist = mDAO.selectAll(conn);

		// ArrayList<Member> mList = service.selectAll();
		menu.displayMember(mlist);
		return mlist;
	}

	public void selectMember() {
		// 검색 조건의 번호를 반환 받아 저장
		int sel = menu.selectMember();
		ArrayList<Member> mList = null;

		switch (sel) {
			case 1:
				String id = menu.inputMemberId();
				mList = service.selectMemberId(id);
				break;
			case 2:
				char gender = menu.inputGender();
				mList = service.selectGender(gender);
				break;
			case 0:
				return;
		}

		if (!mList.isEmpty()) {
			menu.displayMember(mList);
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
	}

}
