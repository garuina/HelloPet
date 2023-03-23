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
import java.util.Optional;

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

import kr.co.hellopet.service.ProductService;
import kr.co.hellopet.service.SearchService;
import kr.co.hellopet.vo.Api_HospitalVO;
import kr.co.hellopet.vo.Cate1VO;
import kr.co.hellopet.vo.Cate2VO;
import kr.co.hellopet.vo.MedicalVO;
import kr.co.hellopet.vo.MemberVO;
import kr.co.hellopet.vo.ProductVO;
import kr.co.hellopet.vo.ReserveVO;
import kr.co.hellopet.vo.SearchVO;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
	@GetMapping("product/list")
	public String list(Model model, String cate1, String cate2, String pg) {
		
		List<Cate1VO> cate1s = service.Cate1();
		List<Cate2VO> cate2s = service.Cate2();
		
		model.addAttribute("cate1s", cate1s);
		model.addAttribute("cate2s", cate2s);
		
		if (cate1 == null) {
	        cate1 = "10";
	    }
		
		List<Cate2VO> selectC2s = service.SelectCate2(cate1);
		model.addAttribute("selectC2s", selectC2s);
		
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);

		
		List<ProductVO> products = service.SelectProduct(cate1, cate2, start);
		model.addAttribute("products", products);
		
		
		// 페이징처리
        int total = 0;
        total = service.SelectCountTotal(cate1,cate2);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getpageStartNum(total, start);
        int[] groups = service.getPageGroup(currentPage, lastPageNum);
        
    	
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate2", cate2);
		model.addAttribute("products", products);
	    model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("pg", pg);
		
		return "product/list";
	}
	
	@ResponseBody
	@PostMapping("product/list")
	public String list(Model model, String type) {
		
		
		
		return "prodct/list";
	}
	
	
	@GetMapping("product/view")
	public String view() {
		return "product/view";
	}
	
	
}
