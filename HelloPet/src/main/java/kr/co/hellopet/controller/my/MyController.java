package kr.co.hellopet.controller.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	@GetMapping("my/info")
	public String info(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("user");
		
		model.addAttribute("user", user);
		
		return "my/info";
	}

}
