package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
	// - memberId : String
	// - memberPwd : String
	// - memberName : String
	// - gender : char
	// - email : String
	// - phone : String
	// - age : int
	// - address : String
	// - enrollDate : Date
	
	// + 기본 생성자
	// + Member(memberId : String, memberPwd:String, memberName :String, gender:char, email:String,String phone
	//			age:int, address:String, enrollDate:Date)
	// +  Member(memberId : String, memberPwd:String, memberName :String, gender:char, email:String,String phone
	//			age:int, address:String)
	// + getter/setter
	// + toString() : String
	// 	   반혼 값 : 아이디, 비밀번호, 이름, 성별, 이메일, 전화번호, 주소, 나이, 등록일
	
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char gender;
	private String email;
	private String phone;
	private int age;
	private String address;
	private Date enrollDate;
	
	public Member() {
	}
	public Member(String memberId, String memberPwd, String memberName,char gender,String email, String phone, int age, String address) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.address = address;
	}
	public Member(String memberId, String memberPwd, String memberName,char gender,String email, String phone, int age, String address,
			Date enrollDate) {
		this(memberId,memberPwd,memberName,gender,email,phone,age,address);
		this.enrollDate = enrollDate;
	}
	public String getMemberId() {
		return this.memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getMemberPwd() {
		return this.memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberName() {
		return this.memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public char getGender() {
		return this.gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getEnrollDate() {
		return this.enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	@Override
	public String toString() {

		String str = memberId + ", " +  memberPwd + ", " + memberName+ ", " + gender
				+ ", " + email+ ", " + phone+ ", " + age+ ", " + address+ ", " +enrollDate;
		return str;
	}
		
}
