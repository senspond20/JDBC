package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.vo.Board;

public class BoardService {

	//private static Connection conn = getConnection();
	
	public ArrayList<Board> selectAll() {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		ArrayList<Board> mList = bDAO.selectAll(conn);
		return mList;
	
	}

	public Board selectOne() {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		Board board = bDAO.selectOne(conn);
		return board;
		
	}

}
