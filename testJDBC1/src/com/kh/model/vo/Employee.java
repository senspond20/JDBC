package com.kh.model.vo;

import java.util.Date;

public class Employee {
	/*
	 * 	- empNo : int      // 사번
	 *  - empName : String // 이름
	 *  - job : String     // 직책
	 *  - mgr : int        // 직속 상상(manager)
	 *  - hireDate : Date  // 고용일(java.sql.Date)
	 *  - sal : int        // 급여
	 *  - comm : int       // 커미션(인센티브)
	 *  - deptNo : int     // 부서번호
	 *  
	 *  + 기본생성자
	 *  + Employee(empNo : int, empName : String, job:String, mgr:int, hireDate : Date, sal : int, comm:int, deptNo:int)
	 *  + Employee(empNo : int, empName : String, job:String, mgr:int, sal:int, comm:int, deptNo:int)
	 *  + Employee(empNo : int, job:String, sal:int, comm:int)
	 *  + Employee(job:String, sal:int, comm:int)
	 *  + getter/setter
	 *  + toString():String
	 *  	반환형식 : empNO / empName / job / mgr / hireDate /sal /comm/ deptNO
	 *  
	 */
	private int empNo;
	private String empName;
	private String job;
	private int mgr;
	private Date hireDate;
	private int sal;
	private int comm;
	private int deptNo;
	
	public Employee() {
	}

	public Employee(String job, int sal, int comm) {
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}
	public Employee(int empNo, String job, int sal, int comm) {
		this.empNo = empNo;
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}
	public Employee(int empNo, String empName, String job, int mgr, int sal, int comm, int deptNo) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}
	public Employee(int empNo, String empName, String job, int mgr, Date hireDate, int sal, int comm, int deptNo) {
		this(empNo,empName,job,mgr,sal,comm,deptNo);
		this.hireDate = hireDate;
	}
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	@Override
	public String toString() {
		String str = 
	      "[" + empNo + ", " + empName + ", " + job + ", " + mgr + ", " + 
		        hireDate + ", " + sal + ", " + comm + ", " + deptNo + " ]";
		
		return str;
		
//		StringBuilder sb = new StringBuilder();
//		sb.append("[ ");
//		sb.append(empNo);sb.append(", ");
//		sb.append(empName);sb.append(", ");
//		sb.append(job);sb.append(", ");
//		sb.append(mgr);sb.append(", ");
//		sb.append(hireDate);sb.append(", ");
//		sb.append(sal);sb.append(", ");
//		sb.append(comm);sb.append(", ");
//		sb.append(deptNo);sb.append(" ]");
//		return sb.toString();
	
	}
	

	
	
}
