package kr.co.hellopet.controller.admin;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hellopet.service.AdminService;
import kr.co.hellopet.socket.NotificationHandler;
import kr.co.hellopet.vo.AdminReserveVO;
import kr.co.hellopet.vo.CommunityVO;
import kr.co.hellopet.vo.CsVO;
import kr.co.hellopet.vo.MedicalVO;
import kr.co.hellopet.vo.MemberVO;
import kr.co.hellopet.vo.MessageVO;

@Controller
public class AdminController {

	@Autowired
	private AdminService service;
	
	
	@GetMapping("admin/info")
	public String info(Model model, Principal principal) {

		String uid = principal.getName();
		
		MedicalVO vo = service.selectAdmin(uid);
		MemberVO user = service.selectUser(uid);
		
		model.addAttribute("vo",vo);
		model.addAttribute("user",user);
		return "admin/info";
	}
	@GetMapping("admin/confirm/list")
	public String confirmList(Model model, String pg, String medNo, Principal principal, @RequestParam(value="revNo", required=false) Integer revNo) {
		
		String uid = principal.getName();
		MedicalVO vo = service.selectAdmin(uid);
		
		model.addAttribute("vo",vo);
		
		// 페이징 처리
		int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        int pageSize = 10;
        
        int total = service.selectCountTotal(medNo);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getpageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
        // 예약 목록 출력
		List<AdminReserveVO> reserves = service.selectReserves(start, medNo, pageSize);
		int size = reserves.size();
		int idx = 1;
		for(AdminReserveVO res : reserves) {
			res.setId(1+ size - idx++);
		}
		// 팝업창 예약 정보 출력
		AdminReserveVO reserve = service.selectReserve(revNo);
		
		model.addAttribute("res",reserve);
		
		
		model.addAttribute("reserves", reserves);
		model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("medNo", medNo);
		
        return "admin/confirm/list";
	}
	
	@ResponseBody
	@GetMapping("admin/confirm/ok")
	public Map<String, AdminReserveVO> list(Model model,@RequestParam(value="revNo", required = false) Integer revNo,@RequestParam(value="medNo", required = false) Integer medNo) {
		AdminReserveVO reserve = service.selectReserve(revNo);
		Map<String, AdminReserveVO> map = new HashMap<>();
		map.put("result", reserve);
		
		return map;
	}
	
	@ResponseBody
	@PostMapping("admin/confirm/ok")
	public Map<String, Boolean> ok(@RequestParam(value="revNo", required = false) Integer revNo, MessageVO vo, String medical, String uid, String medNo) {
	    boolean success = service.updateConfirm(revNo);
	    Map<String, Boolean> map = new HashMap<>();
	    map.put("result", success);
	    
	    vo.setUid(uid);
	    vo.setMedical(medical);
	    
	    if(success == true) {
	    	vo.setTitle(vo.getMedical()+ "에서 예약을 수락하였습니다.");
	    	vo.setContent(vo.getMedical()+ "에서 예약을 수락하였습니다.");
	    	service.insertMsg(vo);
	    	service.updateReserve(medNo);
	    }
	    
	    return map;
	}
	
	@ResponseBody
	@GetMapping("admin/confirm/view")
	public Map<String, AdminReserveVO> view(@RequestParam(value="revNo", required = false) Integer revNo) {
		AdminReserveVO reserve = service.selectReserve(revNo);
		Map<String, AdminReserveVO> map = new HashMap<>();
		map.put("result", reserve);
		
		return map;
	}
	
	@ResponseBody
	@PostMapping("admin/confirm/view")
	public Map<String, Boolean> view(@RequestParam(value="revNo", required = false) Integer revNo, MessageVO vo, String medical, String uid) {
	    boolean success = service.updateReject(revNo);
	    Map<String, Boolean> map = new HashMap<>();
	    map.put("result", success);
	    
	    vo.setUid(uid);
	    vo.setMedical(medical);
	    
	    if(success == true) {
	    	vo.setTitle("거절사유 : " + vo.getMedical()+ "에서 예약을 거절하였습니다.");
	    	service.insertMsg(vo);
	    }
	    
	    return map;
	}
	
	@GetMapping("admin/infoModify")
	public String infoModify(Model model, Principal principal) {
		
		String uid = principal.getName();
		MedicalVO vo = service.selectAdmin(uid);
		
		model.addAttribute("vo",vo);
		return "admin/infoModify";
	}
	
	@PostMapping("admin/infoModify")
	public String infoModify(MedicalVO vo) {
		service.updateAdmin(vo);
		return "redirect:/admin/info";
	}
}
