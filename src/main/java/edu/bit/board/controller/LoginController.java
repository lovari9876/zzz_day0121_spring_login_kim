package edu.bit.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.bit.board.service.LoginService;
import edu.bit.board.vo.UserVO;

@Controller
@RequestMapping("/member")
public class LoginController {
	
	@Inject
	LoginService loginService;
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, RedirectAttributes rttr) {
		System.out.println("login()");
		
		HttpSession session = req.getSession();
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		UserVO login = loginService.loginUser(id, pw);
		
		if(login==null) {
			//RedirectAttributes ���ΰ�ħ�ϸ� ���󰡴� ������(1ȸ��)
			rttr.addFlashAttribute("msg",false);
		} else {
			session.setAttribute("member", login);
		}
		
		return "redirect:/";
	}
	
	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) { 
		// 원래 세션은 request안에 있으나, 스프링은 HttpSession 지원함
		
		System.out.println("login()");
		
		session.invalidate();
		
		return "redirect:/";
	}
}
