package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.RegisterModel;
import com.example.demo.service.UserService;

@Controller
public class UserInfoController {

	@Autowired
	private UserService userservice;

	@PostMapping(value="register")
	public ModelAndView register(@ModelAttribute RegisterModel remo) {
		boolean registerChk = userservice.register(remo);
		if(registerChk) {
			ModelAndView mv = new ModelAndView("html/login/login");
			return mv;
		}
		ModelAndView mv = new ModelAndView("html/register/registerForm");
		return mv;
	}
}
