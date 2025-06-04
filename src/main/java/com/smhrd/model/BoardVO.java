package com.smhrd.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok : 어노테이션을 이용해 사용, getter/setter 자동생성
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	// 게시글 데이터 1개(1행)를 표현할 수 있는 나만의 자료형(객체)
	// String int 날짜 데이터를한번에 묶어서 표현가능한 자료형
		
		// 1. 필드
		private int idx;
		private String title;
		private String contents;
		private String writer;
		private Date date;
		
}
