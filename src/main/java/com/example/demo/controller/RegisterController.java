package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.common.ComMessage;
import com.example.demo.model.RegisterModel;
import com.example.demo.service.UserService;


//ユーザー処理を受け取るコントローラー
@Controller
public class RegisterController {

	@Autowired
	private UserService usersevice;

	/*
	 * ユーザー登録画面遷移
	 *
	 */
	@GetMapping(value = "register")
	public String register(RegisterModel registerModel) {
		return "html/register/registerForm";
	}

	/*
	 * ユーザー登録
	 *
	 */
	@PostMapping(value = "registerForm")
	public ModelAndView registerForm(@Valid @ModelAttribute RegisterModel remo,BindingResult erorrs,HttpSession ses) {

		ModelAndView mv = new ModelAndView("html/register/registerForm");
		String chkResultMessage = "";

		if(erorrs.hasErrors()) {

			List<ObjectError> errorList = erorrs.getAllErrors();
			mv = new ModelAndView("html/register/registerForm","errors",errorList);
		}else if( !(remo.getPass().equals(remo.getPassConf())) ) {

			chkResultMessage = ComMessage.USER_REGISTER_FAILURE_NOT_SAME_PASS;
			mv = new ModelAndView("html/register/registerForm","message",chkResultMessage);
		}else if( !(remo.getEmail().equals(remo.getEmailConf())) ) {

			chkResultMessage = ComMessage.USER_REGISTER_FAILURE_NOT_SAME_EMAIL;
			mv = new ModelAndView("html/register/registerForm","message",chkResultMessage);
		}else {

			int chkRegister = usersevice.register(remo);

			switch(chkRegister) {
			case 0:
				chkResultMessage = ComMessage.USER_REGISTER_COMPLETE;
				mv = new ModelAndView("html/login/login","message",chkResultMessage);

				break;
			case 1:
				chkResultMessage = ComMessage.USER_REGISTER_FAILURE_SAME_INFO;
				mv = new ModelAndView("html/register/registerForm","message",chkResultMessage);
				break;
			case 2:
				chkResultMessage = ComMessage.USER_REGISTER_FAILURE;
				mv = new ModelAndView("html/register/registerForm","message",chkResultMessage);
				break;
			default: break;

			}
		}
		return mv;
	}
}
