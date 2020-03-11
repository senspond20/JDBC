package com.kh.member.controller;
import java.util.ArrayList;

import com.kh.board.model.vo.Board;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.view.View;

public class MemberController {

	private MemberService mService = new MemberService();
	private View view = new View();
	
	// 
	public void login() {
		Member mem = view.inputLogin();
		int result = mService.login(mem);
		if(result > 0) {
			view.displayLoginSuccess();
		}else {
			view.displayLoginError();
		}
	}

	public void selectAll(ArrayList<Board> bList) {
		view.selectAll(bList);
	}

}
