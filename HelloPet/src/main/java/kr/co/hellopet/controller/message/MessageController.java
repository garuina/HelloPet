package kr.co.hellopet.controller.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hellopet.service.MessageService;
import kr.co.hellopet.vo.AdminReserveVO;
import kr.co.hellopet.vo.MessageVO;

@Controller
public class MessageController {

	@Autowired
	private MessageService service;
	
	@GetMapping("message/message")
	public String message(Model model,String uid, String msgNo) {
		
		List<MessageVO> msgs = service.selectMsgs(uid);
		model.addAttribute("msgs", msgs);
		
		return "message/message";
	}

	@ResponseBody
	@GetMapping("message/view")
	public Map<String, MessageVO> view(@RequestParam(value="msgNo", required = false) String msgNo) {
		MessageVO message = service.selectMsg(msgNo);
		Map<String, MessageVO> map = new HashMap<>();
		map.put("result", message);
		
		if(message != null) {
			service.updateMsg(msgNo);
		}
		
		return map;
	}
}
