package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.common.ComMessage;
import com.example.demo.domain.App;
import com.example.demo.domain.User;
import com.example.demo.model.AppSearchModel;
import com.example.demo.service.AppService;
import com.example.demo.service.OfferService;

//応募に関する画面に遷移するコントローラー
@Controller
public class AppController {

	//変数コメント
	@Autowired
	private AppService appservice;


	@Autowired
	private OfferService offerService;

	/*
	 * 応募完了
	 *
	 */
	@PostMapping(value="/appcomp")
	public String appConp(@RequestParam(name = "appid", defaultValue = "") String appid,HttpSession ses,HttpServletRequest req) {

		System.out.print(req.getAttribute("apps"));
		User user = (User) ses.getAttribute("user");

		if(ses.getAttribute("user") != null) {
			boolean app = offerService.offerInsert(Integer.parseInt(appid),user.getUserId());
			ses.removeAttribute("apps");
			return "html/apply/appcomp";
		}

		return "html/login/login";
	}

	/*
	 * 応募詳細
	 *
	 */
	@PostMapping(value="/appDetail")
	public ModelAndView appDetail(@RequestParam(name = "appid", defaultValue = "") String appid,HttpSession ses) {

		ModelAndView mv;

		if(appid != "") {

			App appList = appservice.findApp(Integer.parseInt(appid));
			mv = new ModelAndView("html/apply/appdetail","apps",appList);
			ses.setAttribute("apps", appList.getAppId());
			return mv;

		}else{

			mv = new ModelAndView("html/top/toppage");
			return mv;
		}
	}

	/*
	 * 応募検索
	 *
	 */
	@GetMapping(value="/search")
	@ResponseBody
	public List<App> search(@ModelAttribute AppSearchModel appserch){

		System.out.println(ComMessage.APPS_SEARCH_START);

		List<App> apps;
		if(appserch.getSearch_word() == null) {
			apps = appservice.findAll();
		}else {
			apps = appservice.findDetail(appserch);
		}

//		for(int i = 0;i<apps.size();i++) {
//			ArrayList<String> list = new ArrayList<>();
//			list.add(apps.get(i).getAppName());
//			list.add(apps.get(i).getCompanyName());
//			list.add(apps.get(i).getOccupationName());
//			list.add(String.valueOf(apps.get(i).getAppId()));
//			list2.add(list);
//		}
//		ModelAndView mv = new ModelAndView("html/apply/toppage","apps",apps);
		System.out.println(ComMessage.APPS_SEARCH_END);
		return apps;
	}
}
