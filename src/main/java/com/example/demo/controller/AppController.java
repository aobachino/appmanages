package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.App;
import com.example.demo.domain.User;
import com.example.demo.service.AppService;
import com.example.demo.service.OfferService;

@Controller
public class AppController {

	@Autowired
	private AppService appservice;

	@Autowired
	private TopPageController topPageController;

	@Autowired
	private OfferService offerService;

	@PostMapping(value="/appcomp")
	public String appConp(@RequestParam(name = "appid", defaultValue = "") String appid,HttpSession ses,HttpServletRequest req) {

		System.out.print(req.getAttribute("apps"));
		User user = (User) ses.getAttribute("user");

		if(ses.getAttribute("user") != null) {
			boolean app = offerService.insert(Integer.parseInt(appid),user.getUserId());
			ses.removeAttribute("apps");
			return "html/apply/appcomp";
		}

		return "html/login/login";
	}

	@PostMapping(value="/appDetail")
	public ModelAndView appDetail(@RequestParam(name = "appid", defaultValue = "") String appid,HttpSession ses) {

		ModelAndView mv;

		if(appid != "") {

			App appList = appservice.findApp(Integer.parseInt(appid));
			mv = new ModelAndView("html/apply/appdetail","apps",appList);
			ses.setAttribute("apps", appList.getAppId());
			return mv;

		}else{

			mv = topPageController.top(ses);
			return mv;

		}
	}

	@PostMapping(value="/search")
	public ModelAndView search(){

		List<App> apps = appservice.findAll();
		ModelAndView mv = new ModelAndView("html/apply/toppage","apps",apps);
		return mv;
	}
}
