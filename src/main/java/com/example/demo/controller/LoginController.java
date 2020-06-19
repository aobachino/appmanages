package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.common.ComMessage;
import com.example.demo.model.LoginForm;
import com.example.demo.service.UserService;

//ログイン処理を行うコントローラー
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppController appController;

	@GetMapping(value = "login")
	public String login() {
		return "html/login/login";
	}

	/*
	 * ログインチェック
	 * in ユーザーID、パスワード
	 * out
	 */
	@PostMapping(value = "loginChk")
	public ModelAndView loginChk(@ModelAttribute LoginForm logmo,HttpSession ses) {

		System.out.println(ComMessage.LOGIN_START);
		ModelAndView mv;
		boolean userChk = userService.userfind(logmo,ses);

		if(userChk) {
			if(ses.getAttribute("apps") != null) {
				String appId = ses.getAttribute("apps").toString();
				return appController.appDetail(appId, ses);
			}
			mv = new ModelAndView("html/top/toppage");
			System.out.println(ComMessage.LOGIN_END);
			return mv;
		}

		mv = new ModelAndView("html/login/login","message",ComMessage.USER_NOTHING);
		System.out.println(ComMessage.LOGIN_END);
		return mv;
	}

	/*
	 * ログアウト処理
	 */
	@PostMapping(value="logout")
	public ModelAndView logout(HttpSession ses) {

		ModelAndView mv = new ModelAndView("html/top/toppage");
		//ユーザー情報がnullでなければセッションを破棄する
		if(ses.getAttribute("user") != null) {
			ses.removeAttribute("user");

			return mv;
		}
		return mv;
	}
}
