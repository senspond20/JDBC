package com.kh.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	// JDBC 드라이버 등록
	// DB 연결(Connection)
	// 트랙잰션
	// 자원반납
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		// 한번만 수행
		if(conn == null) {
			try {
				// 옛날에는 String으로 직접 넣지 않고 property 파일을 통해서 키와 벨류를 가지고 넣는 방식을 많이 사용했다.
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MEMBER","MEMBER");
				
				Properties prop = new Properties();
				prop.load(new FileReader("driver.properties"));
				
				Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(prop.getProperty("url"),
												   prop.getProperty("user"),
												   prop.getProperty("password"));
				
				// 자동으로 커밋하는것을 막겠다.
				// why? : 우리가 트랜잭션을 하는것을 개발자가 이럴떄 커밋하고 롤백할겁니다 해야하는데
				// true라고 해놓으면 
				conn.setAutoCommit(false);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if( conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt != null && stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rset) {
		try {
			if(rset !=null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null & !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
