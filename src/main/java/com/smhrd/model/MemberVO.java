package com.smhrd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 모든 매새변수를 받아 필드 초기화하는 생성자
@NoArgsConstructor // 기본생성자 
public class MemberVO {
	// 회원 데이터를 저장할 수 있도록 나만의 자료형
	// 1. 필드
	private String email;
	private String pw;
	private String tel;
	private String address;
	
}
