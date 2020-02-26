package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.EmployeeDAO;
import com.kh.model.vo.Employee;
import com.kh.view.Menu;

// 1.�뜲�씠�꽣 媛�怨�
// 2.dao�뿉寃� �꽆寃⑥＜湲�
// 3.dao�뿉寃� 諛쏆� 寃곌낵媛믪쑝濡� 酉곌껐�젙

public class EmployeeController {
	//View에서 전달받은 데이터를 가공처리(데이터 변환, 티코딩 등) 하여 dao로 전달
	// dao로부터 전달 받은 결과에 따라 view(출력화면)를 결정하여 데이터 전송
	// db에 가려면 dao를 거쳐야함!
	private EmployeeDAO empDAO = new EmployeeDAO();
	private Menu menu = new Menu();
	
	

	// 전체 사원 정보 조회

	public void selectAll() {
		ArrayList<Employee> empList = empDAO.selectAll();
		
		if(!empList.isEmpty()) { //조회결과가 있을 경우 (임플로이에서 받아온 반환값이)
			menu.selectAll(empList);
			
		}else {	 //조회결과가 없을 경우
			menu.displayError("議고쉶 寃곌낵媛� �뾾�뒿�땲�떎.");
		}
		
	}
	
	// 메뉴 2번 사원으로 사원정보 조회
	public void selectEmployee() { 
		
		
		//사번을 입력하는 veiw를 호출하는 역할
		int empNo = menu.selectEmpNo();  
		// 誘몃━ 硫붿냼�뱶瑜� 留뚮뱾�븣 諛섑솚媛믩룄 �깮媛곹븯怨� �깮�꽦�븯硫� �옄�룞吏��젙, 醫��뜑 �슚�쑉�쟻�씠�떎.
		
		// 諛섑솚媛� : �븳紐� �븘�땲硫� �뿬�윭紐�
		Employee emp = empDAO.selectEmployee(empNo);
		
		if(emp != null) {
			menu.selectEmployee(emp);
		}else {
			menu.displayError("해당사번의 검색결과가 없습니다");
		}
	
	}

	public void insertEmployee() {
		Employee emp = menu.insertEmployee();
		
		int result = empDAO.insertEmployee(emp);
		
		if(result > 0) {
			menu.displaySuccess(result + "개의 행이 추가 되었습니다.");
		}else {
			menu.displayError("데이터 삽입 과정 중 오류 발생");
		}
		
	}
	
	public void updateEmployee() {
		int empNo = menu.selectEmpNo();
		
		Employee emp = menu.updateEmployee();
		
		emp.setEmpNo(empNo);
		int result = empDAO.updateEmployee(emp);
		
		if(result > 0 ) {
			menu.displayError(result + "개의 행이 수정되었습니다.");
		}else {
			menu.displayError("데이터 수정 과정 중 오류 발생");
		}
	}

	public void deleteEmployee() {
	
		// empNo 를 가지고 삭제
		int empNo = menu.selectEmpNo();
		char check = menu.deleteEmployee();
		
		if(check == 'y') {
			
			//1. DAO 에 empNo 를 보낸다. 반환값 result int 
			
			int result = empDAO.deleteEmployee(empNo);
			
			if(result > 0 ) {
				menu.displaySuccess(result + "개의 행이 삭제되었습니다.");
			}else {
				menu.displayError("데이터 삭제 과정 중 오류 발생");
			}
			
			
		}else if (check == 'n') {
			menu.displaySuccess("사원 정보 삭제 취소");
		}else {
			menu.displayError("잘못 입력하셨습니다. (y 또는 n 입력)");
		}
		// 삭제를 할건지 확인하고 
		
		
	}

}
