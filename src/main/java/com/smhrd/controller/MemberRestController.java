package com.smhrd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.mapper.MemberMapper;
import com.smhrd.model.MemberVO;

@RestController // @Responsebody+@Controller
public class MemberRestController {
	// Spring에서 비동기 통신으로 요청이 들어오면 순수하게 데이터만 돌려주는 controller의 메서드를 분리해서 사용 -> Rest
	// Represent state transfer의 약자 => 데이터만 되돌려주면 요청을 한 주체에서 해당하는 데이터를 활용(이용,소비)하는 방식 결정
	// ex) web, android, ios app
	
	@Autowired
	MemberMapper mapper;

	// email 검색 자동완성 기능 메서드
		@RequestMapping("/searchMember")
		public List<MemberVO> searchMember(@RequestParam String search) {
			// 1. Member테이블에서 이메일과 search정보가 포함된 모든행을 가져오기
			List<MemberVO> result = mapper.searchMember(search);
			// 2. eclipse 콘솔에 조회한 데이터 출력
			System.out.println(result);
			// 3. result 데이터를 전송
			return result;
		}
		
		// 이메일 중복확인 메서드
		// @RespoonseBody : 메서드의 리턴값이 viewname이 아니라 화면에 출력하고싶을때 사용한다.
		@RequestMapping("/EmailCheck")
		public Map<String, Boolean> EmailCheck(@RequestParam String email) {
			int cnt = mapper.emailCheck(email);
			boolean available = false;
			if(cnt==0) { // DB에 중복된 이메일이 없다 -> 사용 가능한 이메일
				available = true;
			}
			// hashmap(자료구조) (key, value 한 쌍으로 구성되어있다.
			Map<String, Boolean> response = new HashMap<>();
			response.put("available", available);
			
			return response;
			
		}
		
}
