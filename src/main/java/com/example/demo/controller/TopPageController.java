package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.App;
import com.example.demo.service.AppService;

//トップページに遷移するコントローラー
@RestController
public class TopPageController {

	@Autowired
	AppService appservice;


	@GetMapping(value = "/")
	public ModelAndView top(HttpSession ses) {
		System.out.println("トップページに遷移");
		ModelAndView mv = new ModelAndView("html/top/toppage");
		return mv;
	}

	@GetMapping(value = "topdata")
	@ResponseBody
	public List<App> topdata(HttpSession ses) {
		// 求人情報取得
		int pageNum = 1;
		ses.setAttribute("pageNum",pageNum);
		List<App> apps = appservice.findAll(pageNum);
		return apps;
	}

	@GetMapping(value="header.html")
	public ModelAndView header(HttpSession ses) {
		ModelAndView mv = new ModelAndView("html/header");

		return mv;
	}

	@GetMapping(value="footer.html")
	public ModelAndView footer(HttpSession ses) {
		ModelAndView mv = new ModelAndView("html/footer");
		return mv;
	}

}
