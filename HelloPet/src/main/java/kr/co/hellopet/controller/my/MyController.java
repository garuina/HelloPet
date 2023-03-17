package kr.co.hellopet.controller.my;

/*
 * 날짜 : 2023/03/17 
 * 내용 : HelloPet/my 페이지 화면 및 기능구현
 * 담당 : 이민혁
 * 
 */

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hellopet.config.MyUserDetails;
import kr.co.hellopet.service.MyService;
import kr.co.hellopet.vo.MemberVO;

@Controller
public class MyController {
	
	@Autowired
	private MyService service;

	@GetMapping("my/info")
	public String info(Authentication authentication, Model model) {
		
		MyUserDetails userDetails = null;
		
		// authentication 으로 사용자 객체 가져오기
		String uid = authentication.getName();
		
		// 사용자 객체 유무 확인...
		if(authentication != null) {
			
			// 사용자 객체 조회 후 vo 에 담아서 화면 구현..
			MemberVO  vo = service.selectUser(uid);
			
			model.addAttribute("member", vo);
		}
		
		return "my/info";
	}
	
	@PostMapping("my/info")
	public String info(String name, String email, String nick, String hp, String uid) {
		
		// info update
		service.updateInfoModify(name, email, nick, hp, uid);
			
		return "redirect:/my/info";
	}

	@GetMapping("my/myArticle")
	public String myArticle() {
		return "my/myArticle";
	}
	
	@GetMapping("my/myQna")
	public String myQna() {
		return "my/myQna";
	}
	
	@GetMapping("my/myReserve")
	public String myReserve() {
		return "my/myReserve";
	}
}
