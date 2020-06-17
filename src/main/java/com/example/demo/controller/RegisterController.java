package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.RegisterModel;
import com.example.demo.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	 private UserService usersevice;

	/*
	 * ユーザー登録画面遷移
	 *
	 */
	@GetMapping(value = "register")
	public String register() {
		return "html/register/registerForm";
	}

	/*
	 * ユーザー登録
	 *
	 */
	@PostMapping(value = "registerForm")
	public ModelAndView registerForm(@ModelAttribute RegisterModel remo,HttpSession ses) {

		ModelAndView mv = new ModelAndView("html/register/registerForm");
//		if() {
			if(remo.getPass().equals("")) {
				if(remo.getPass().equals(remo.getPassConf()) && remo.getEmail().equals(remo.getEmailConf())) {
					boolean chk = usersevice.register(remo);

					if(chk) {
						mv = new ModelAndView("html/login/login");
						return mv;
					}
				}
			}
//		}
		return mv;
	}
}
