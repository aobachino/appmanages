package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.App;
import com.example.demo.service.AppService;

@Controller
public class TopPageController {

	@Autowired
	AppService appservice;

	@GetMapping(value = "/")
	public ModelAndView top(HttpSession ses) {
		// 求人情報取得
		List<App> apps = appservice.findAll();
		ModelAndView mv = new ModelAndView("html/top/toppage","apps",apps);

		return mv;
	}

//	@GetMapping(value = "/")
//	public String repage(Model model) {
//		List<App> applications = appService.findAll();
//		System.out.print(applications);
//		model.addAttribute("applications",applications);
//		return "html/top/toppage";
//	}
}
