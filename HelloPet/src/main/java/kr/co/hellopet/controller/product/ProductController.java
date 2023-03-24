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
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
	@GetMapping(value = {"product/", "product/list"})
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
	public Map<String, Object> list(@RequestParam("cate1")String cate1, 
					   @RequestParam("cate2")String cate2, 
					   @RequestParam("type") String type,
					   String pg) {
		
		log.info("cate1 : " + cate1);
		log.info("cate2 : " + cate2);
		log.info("type : " + type);
		
		Map<String, Object> map = new HashMap<>();
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		List<ProductVO> p2s = service.SelectProductType(cate1, cate2, type, start);
		map.put("p2s", p2s);
		
		
		// 페이징처리
        int total = 0;
        total = service.SelectCountTotalType(cate1,cate2,type);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getpageStartNum(total, start);
        int[] groups = service.getPageGroup(currentPage, lastPageNum);
        
    
        map.put("cate1", cate1);
        map.put("cate2", cate2);
        map.put("currentPage", currentPage);
        map.put("lastPageNum", lastPageNum);
        map.put("pageStartNum", pageStartNum);
        map.put("groups", groups);
        map.put("pg", pg);
		
		return map;
	}
	
	
	@GetMapping("product/view")
	public String view(Model model, String cate1, String cate2, String prodNo) {
		
		ProductVO product = service.SelectProductView(cate1, cate2, prodNo);
		model.addAttribute("product", product);

		MedicalVO med = service.SelectProductMap(prodNo);
		model.addAttribute("med", med);
		
		
		return "product/view";
	}
	
	
}
