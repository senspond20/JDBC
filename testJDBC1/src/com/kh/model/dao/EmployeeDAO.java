package com.kh.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Employee;

public class EmployeeDAO {
//데이터 베이스와 연결할 곳이 DAO
	public ArrayList<Employee> selectAll() {
		
		Connection conn = null;  // 연결정보를 담은 객체
		Statement stmt = null;   // Connection 객체를 통해 DB에 SQL문을 전달하여 실행시키고 결과값을 반환 하는 역
		ResultSet rset = null;   // SELECT ���� ����� ���� ���� �� ��ȯ�Ǵ� ��ü
		
		ArrayList<Employee> empList = null;
		// ����̹� ����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","SCOTT");
			// jdbc:oracle:thin         JBCD 가 thin 타입
			// @localhost             	 오라클이 설치된 서버의 ip가 자신의 컴퓨터 == @127.0.0.0.1
			// 1521 					 오라클 listerner 포트번호
			// xe 						 접속할 오라클명
			
			//System.out.println(conn);  // 접속실패 null 반환
			
			// �ϼ��� ����
			String query = "SELECT * FROM EMP";
			stmt = conn.createStatement();

			rset = stmt.executeQuery(query); // select ���� �����⶧���� executeQeury
			
			empList = new ArrayList<Employee>(); // ��ȸ����� ������ ArrayList
			Employee emp = null; // ��ȸ ����� �� ���� ���� ������ �ӽ� vo ����
			
			while(rset.next()) {
									// EMPNO, empno �÷����� ��ҹ��� ���� x
				int empNo = rset.getInt("empno");
				String empName = rset.getString("ename");
				String job = rset.getString("job");
				int mgr = rset.getInt("mgr");
				Date hireDate = rset.getDate("hiredate"); // Date - sql ����Ʈ
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("comm");
				int deptNo = rset.getInt("deptNo");
				
				emp = new Employee(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
				empList.add(emp);
			}
			
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();     // ���� ���Ŵ� ���� ���߿� �ݴ´�.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return empList;
		
	}

	 public Employee selectEmployee(int empNo) {
	      Connection conn = null;
	      
	    //  Statement stmt = null;
	      PreparedStatement pstmt = null;
	     
	      ResultSet rset = null;
	      Employee emp = null;
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "SCOTT");
	         
	         //String qurey = "SELECT * FROM EMP WHERE EMPNO = " + empNo;
	         //String qurey = "SELECT * FROM EMP WHERE EMPNO = '" + empNo + "'";
	         
	         String qurey = "SELECT * FROM EMP WHERE EMPNO = ?";
	         // prepareStatement 를 사용하게 되면 위치홀더 자리에 싱글코테이션이 알아서 붙어서 들어간다.
	         
	         //stmt = conn.createStatement();
	         
	         pstmt = conn.prepareStatement(qurey);
	         pstmt.setInt(1, empNo); // 첫번쨰 위치홀더에 empNo 집어넣는다.
	         
	         rset = pstmt.executeQuery();
	       //  rset = stmt.executeQuery(qurey);

	         if (rset.next()) {
	            String empName = rset.getString("ENAME");
	            String job = rset.getString("JOB");
	            int mgr = rset.getInt("MGR");
	            Date hireDate = rset.getDate("HIREDATE");
	            int sal = rset.getInt("SAL");
	            int comm = rset.getInt("COMM");
	            int deptNo = rset.getInt("DEPTNO");

	            emp = new Employee(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
	         }
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rset.close();
	            pstmt.close();
	      //      stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return emp;
	   }

	public int insertEmployee(Employee emp) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "SCOTT");
	         
			String query = "INSERT INTO EMP VALUES(?,?,?,?,SYSDATE, ?,?,?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,  emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setInt(5, emp.getSal());
			pstmt.setInt(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptNo());
			
			// 한 행이 삽입되었습니다. 0 행이 삽입되었습니다. 
			// 제대로 성공했다면 1이 반환
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}

	public int updateEmployee(Employee emp) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","SCOTT");
			
			String query = "UPDATE EMP SET JOB = ?, SAL = ?, COMM = ? WHERE EMPNO =?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, emp.getJob());
			pstmt.setInt(2, emp.getSal());
			pstmt.setInt(3, emp.getComm());
			pstmt.setInt(4, emp.getEmpNo());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {	
				e.printStackTrace();
			}
			
		}
		return result;
		
		
	}

	public int deleteEmployee(int empNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","SCOTT");
			String query = "DELETE FROM EMP WHERE EMPNO = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empNo);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
	
		return result;
	}

}
