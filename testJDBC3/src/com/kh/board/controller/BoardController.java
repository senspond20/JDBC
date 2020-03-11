package com.kh.board.controller;

import java.util.ArrayList;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
//import com.kh.member.controller.MemberController;
import com.kh.view.View;

public class BoardController {

	 private  View view = new View();
	 private  BoardService bs = new BoardService();
	//private MemberController mc = new MemberController();
	
	public void selectAll() {
		ArrayList<Board> bList = bs.selectAll();
		if (!bList.isEmpty()) {
			view.selectAll(bList);
		} else {
			view.displayError("조회 결과가 없습니다");
		}
	}

	public void selectOne() {
		Board board = bs.selectOne();
		
		if(board != null) {
			view.selectOne(board);
		}else {
			view.displayError("조회 결과가 없습니다");
		}
	}

	public void insertBoard() {

	}

	public void updateBoard() {

	}

	
	public void deleteBoard() {

	}

}
