package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.User;
import com.example.demo.model.LoginForm;
import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TopPageController topPageController;

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

		ModelAndView mv;
		User user = userService.userfind(logmo);

		if(user != null) {
			ses.setAttribute("user", user);
			if(ses.getAttribute("apps") != null) {
				String appId = ses.getAttribute("apps").toString();
				return appController.appDetail(appId, ses);
			}
			return topPageController.top(ses);
		}

		mv = new ModelAndView("html/login/login");
		return mv;
	}

	/*
	 * ログアウト処理
	 */
	@PostMapping(value="logout")
	public ModelAndView logout(HttpSession ses) {

		//ユーザー情報がnullでなければセッションを破棄する
		if(ses.getAttribute("user") != null) {
			ses.removeAttribute("user");
			return topPageController.top(ses);
		}
		return topPageController.top(ses);
	}
}
