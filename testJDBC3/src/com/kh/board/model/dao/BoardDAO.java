package com.kh.board.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import static com.kh.common.JDBCTemplate.close;

public class BoardDAO {
	private Properties prop = null;
	public BoardDAO() {
		try {
			prop = new Properties();
			prop.load(new FileReader("query.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Board> selectAll(Connection conn) {
		Statement stmt = null;
		ArrayList<Board> bList = null;
		ResultSet rset = null;

		//String query = prop.getProperty("selectAll");
		
		String query = "SELECT * FROM BOARD WHERE DELETE_YN = 'N'";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			bList = new ArrayList<Board>();
			Board board = null;
			while(rset.next()) {
				int bNo = rset.getInt("BNO");
				String title = rset.getString("TITLE");
				String content = rset.getString("CONTENT");
				Date createDate = rset.getDate("CREATE_DATE");
				String writer = rset.getString("WRITER");
				
				board = new Board(bNo,title,content,createDate,writer);
				bList.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return bList;
	}

	public Board selectOne(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

}
