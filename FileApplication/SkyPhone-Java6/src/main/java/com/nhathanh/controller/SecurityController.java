package com.nhathanh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/login")
	public String getLogin() {
		return "pageAccount/login";
	}
	
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message","Vui lòng đăng nhập vào hệ thống SkyPhone!");
		return "forward:/login";
	}

	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "forward:/SkyPhone/index";
	}

	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "forward:/login";
	}

	@RequestMapping("/security/logoff/success")
	public String logoffSucess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất thành công!");
		return "forward:/login";
	}

	@RequestMapping("/security/unauthoried")
	public void unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất");

	}
}
