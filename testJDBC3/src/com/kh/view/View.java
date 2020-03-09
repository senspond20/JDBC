package com.kh.view;

import java.util.Scanner;

import com.kh.member.controller.MemberController;
import com.kh.member.model.vo.Member;

public class View {
	private Scanner sc = new Scanner(System.in);
	private static Member mem = null;
	
	public void mainMenu() {
		MemberController mc = new MemberController();
		
		int select = 0;
		do {
			System.out.println("\n ***게시판 프로그램*** \n");
			
			if(mem == null) {
				System.out.println("1. 로그인");
				System.out.println("0. 프로그램 종료");
				System.out.print("번호 선택 : ");
				select = Integer.parseInt(sc.nextLine());
				
				switch(select) {
					case 1: mc.login(); break;
					case 0: System.out.println("프로그램을 종료합니다."); break;
					default: System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
				}
			}else {
				System.out.println("1. 로그아웃");
				System.out.println("2. 글 목록 조회");
				System.out.println("3. 게시글 상세조회");
				System.out.println("4. 글 쓰기");
				System.out.println("5. 글 수정");
				System.out.println("6. 글 삭제");
				System.out.println("0. 프로그램 종료");
				System.out.print("번호 입력 : ");
				select = Integer.parseInt(sc.nextLine());
				
				switch(select) {
					case 1: mem = null; System.out.println("<< 로그아웃 >>");break;
					case 2: break;
					case 3: break;
					case 4: break;
					case 5: break;
					case 6: System.out.println("프로그램을 종료합니다."); break;
					default: System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
				}
			}
		}while(select != 0);
	}

	public Member inputLogin() {
		mem = new Member();
		System.out.println("----- 로그인 -----");
		System.out.print("ID : ");
		mem.setMemberId(sc.nextLine());
		
		System.out.print("PW : ");
		mem.setMemberPwd(sc.nextLine());
		return mem;
	}

	public void displayLoginSuccess() {
		System.out.println(mem.getMemberId() + "님 환영합니다.");
	}

	public void displayLoginError() {
		// 로그인 실패시 view 게시판 프로그램으로 진입하지 안도록 mem을 null로 초기화
		mem = null;
		System.out.println("로그인 정보를 확인해주세요.");
	}

	
}
