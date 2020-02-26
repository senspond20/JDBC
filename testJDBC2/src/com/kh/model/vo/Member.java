package com.kh.model.vo;

import java.sql.Date;

public class Member {
	/*
	 * 	- memberId : String
	 *  - memberPwd : String
	 *  - memberName : String
	 *  - gender : char
	 *  - email : String
	 *  - phone : String
	 *  - age   : int
	 *  - address : String
	 *  - enrollDate : Date (java.sql)
	 *  
	 *  + 기본 생성자
	 *  + Member(memberId:String, memberPwd:String, memberName: String, gender:char, email:String, phone : String
	 *  		  age:int, address:String, enrollDate:Date)
	 *  + Member(memberId:String, memberPwd:String, memberName: String, gender:char, email:String, phone : String
	 *  		  age:int, address:String)
	 *  + getter/setter
	 *  + toString() : String
	 *  	반환형식 : 아이디, 비밀번호, 이름 , 성별 , 이메일, 전화번호, 주소, 나이, 가입일자
	 *  	
	 */
		private String memberId;
		private String memberPwd;
		private String memberName;
		private char gender;
		private String email;
		private String phone;
		private int age;
		private String address;
		private Date enrollDate;
		
		public Member() {}
		public Member(String memberId, String memberPwd, String memberName, char gender, String email, String phone,
				int age, String address) {
			this.memberId = memberId;
			this.memberPwd = memberPwd;
			this.memberName = memberName;
			this.gender = gender;
			this.email = email;
			this.phone = phone;
			this.age = age;
			this.address = address;
		}
		public Member(String memberId, String memberPwd, String memberName, char gender, String email, String phone,
				int age, String address, Date enrollDate) {

			this(memberId,memberPwd,memberName,gender,email,phone,age,address);
			this.enrollDate = enrollDate;
		}
		
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		public String getMemberId() {
			return this.memberId;
		}
		
		public void setMemberPwd(String memberPwd) {
			this.memberPwd = memberPwd;
		}
		public String getMemberPwd() {
			return this.memberPwd;
		}
		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}
		public String getMemberName() {
			return this.memberName;
		}
		
		public void setGender(char gender) {
			this.gender = gender;
		}
		
		public char getGender() {
			return this.gender;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getEmail() {
			return this.email;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPhone() {
			return this.phone;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getAddress() {
			return this.address;
		}
		
		public void setAge(int age) {
			this.age = age;
		}
		public int getAge() {
			return this.age;
		}
		
		public void setEnrollDate(Date date) {
			this.enrollDate = date;
		}
		public Date getEnrollDate() {
			return this.enrollDate;
		}
		@Override
		public String toString() {
			//반환형식 : 아이디, 비밀번호, 이름 , 성별 , 이메일, 전화번호, 주소, 나이, 가입일자
			
			String str = memberId + ", " + memberPwd + ", " + memberName + ", " +
			gender + ", " + email + ", " + phone + ", " + address + ", " + age + ", " + enrollDate;
			
			return str;
		}
		
		
		
		
}
