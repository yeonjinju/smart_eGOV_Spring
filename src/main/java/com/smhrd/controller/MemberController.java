package com.smhrd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.mapper.MemberMapper;
import com.smhrd.model.MemberVO;

@Controller
public class MemberController {
	// MemberMapper는 인터페이스라 객체 생성불가, @AutoWired 사용
	@Autowired
	MemberMapper mapper;
	
	// 기능단위로 메서드 작성 main.jsp로 이동하는 메서드
	@PostMapping("/join")
	public String join(MemberVO vo, Model model) {
		// MemberDAO dao = new MemberDAO();
		// dao.join();
		mapper.join(vo);

		// 페이지 이동방법
		// 1) Redirect -> url변경됨 req/res 2번사용
		// 2) Forword -> url변경 안됨 req/res 1번만 사용
		// * viewName을 리턴해서 이동하는 방식 == forword !
		// model.addAttribute("모델에 저장되는 이름", "모델에 실제 저장되는 값");
		model.addAttribute("email", vo.getEmail());
		// 포워드 방식으로 이동했을때, 새로고침하면 동일한 계정이 DB에 재등록된다. -> 리다이렉트 방식 이용!
		// 쿼리스트링(쿼리 파라미터) : URL에 데이터를 포함시켜서 전송하는 법
		return "redirect:/join_success?email=" + vo.getEmail();
	}

	@GetMapping("/join_success")
	public String join_success() {
		return "join_success";
	}

	@RequestMapping("/")
	public String member() {
		return "main";
	}
	
	// login 메서드
	@RequestMapping("/login")
	public String login(MemberVO vo, HttpSession session) {
		// 로그인을 하고나서 쿼리문 실행결과를 mvo에 담기
		MemberVO mvo = mapper.login(vo);
		// 로그인을 성공한 계정의 정보를 session에 저장하기위해 매개변수에 작성
		session.setAttribute("mvo",mvo);
		return "main";
	}
	
	// logout메서드
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 로그아웃 : 세션을 무효화 시키기(=지우기)
		session.invalidate();
		return "main";
	}
	
	// select 메서드(목록조회) BoardList
	@RequestMapping("/select")
	public String select(MemberVO vo, Model model) {
		List<MemberVO> list = mapper.select(vo);
		model.addAttribute("list",list);
		return "select";
	}
	
	@GetMapping("/update")
	public String update() {
		return "update";
	}
	
	// update.jsp에서 입력받은 정보를 바탕으로 update기능 구현 -> update.jsp form태그완성
	@PostMapping("/update2")
	public String update2(MemberVO vo) {
		mapper.update(vo);
		return "main";
	}
	
	// 회원탈퇴 메서드
	@RequestMapping("/delete")
	public String delete(HttpSession session) {
		MemberVO vo = (MemberVO) session.getAttribute("mvo");
		mapper.delete(vo);
		session.invalidate();
		return "main";
	}
}
