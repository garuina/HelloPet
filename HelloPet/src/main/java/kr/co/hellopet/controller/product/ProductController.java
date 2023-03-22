package kr.co.hellopet.controller.product;
/*
 * 날짜 : 2023/03/22 ~
 * 내용 : ProductController 작성
 * 이름 : 장인화
 * */
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hellopet.service.SearchService;
import kr.co.hellopet.vo.Api_HospitalVO;
import kr.co.hellopet.vo.MedicalVO;
import kr.co.hellopet.vo.MemberVO;
import kr.co.hellopet.vo.ReserveVO;
import kr.co.hellopet.vo.SearchVO;

@Controller
public class ProductController {
	
	//@Autowired
	//private SearchService service;
	
	
	@GetMapping("product/list")
	public String list() {
		return "product/list";
	}
	
	@GetMapping("product/view")
	public String view() {
		return "product/view";
	}
	
	
}
