package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.AppService;

@RestController
public class TopPageController {

	@Autowired
	AppService appservice;

//	@GetMapping(value = "/")
//	@ResponseBody
//	public List<App> top() {
//		// 求人情報取得
//		List<App> apps = appservice.findAll();
//		return apps;
//	}

	@GetMapping(value = "/")
	public ModelAndView top(HttpSession ses) {
		ModelAndView mv = new ModelAndView("html/top/toppage");
		return mv;
	}
}
