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
	@PostMapping(value = "/appcomp")
	public ModelAndView appConp(@RequestParam(name = "appid", defaultValue = "") String appid, HttpSession ses,
			HttpServletRequest req) {

		ModelAndView mv;
		System.out.print(req.getAttribute("apps"));
		User user = (User) ses.getAttribute("user");
		String insertMessage = "再度やりなおしてください";

		if (ses.getAttribute("user") != null) {
			int insertCheck = offerService.offerInsert(Integer.parseInt(appid), user.getUserId());

			switch (insertCheck) {

			case 0:
				insertMessage = ComMessage.OFFER_COMPLETE;
				break;
			case 1:
				insertMessage = ComMessage.OFFER_FAILURE_OVER_CAPACITY;
				break;
			case 2:
				insertMessage = ComMessage.OFFER_FAILURE;
				break;
			default:
				break;

			}

			ses.removeAttribute("apps");
			mv = new ModelAndView("html/apply/appcomp", "message", insertMessage);
			return mv;
		}
		mv = new ModelAndView("html/login/login");
		return mv;
	}

	/*
	 * 応募詳細
	 *
	 */
	@PostMapping(value = "/appDetail")
	public ModelAndView appDetail(@RequestParam(name = "appid", defaultValue = "") String appid, HttpSession ses) {

		ModelAndView mv;

		if (appid != "") {

			App appList = appservice.findApp(Integer.parseInt(appid));
			mv = new ModelAndView("html/apply/appdetail", "apps", appList);
			ses.setAttribute("apps", appList.getAppId());
			return mv;

		} else {

			mv = new ModelAndView("html/top/toppage");
			return mv;
		}
	}

	/*
	 * 応募検索
	 *
	 */
	@GetMapping(value = "/search")
	@ResponseBody
	public List<App> search(@ModelAttribute AppSearchModel appserch, HttpSession ses) {

		int pageNum = 1;
		ses.setAttribute("pageNum", pageNum);
		ses.setAttribute("searchSelect", appserch);
		System.out.println(ComMessage.APPS_SEARCH_START);

		List<App> apps;
		apps = appservice.findDetail(appserch,pageNum);

		System.out.println(ComMessage.APPS_SEARCH_END);
		return apps;
	}

	/*
	 * ページング処理
	*/
	@GetMapping(value="/paging")
	@ResponseBody
	public List<App> pageChange(@RequestParam(name = "pageNum", defaultValue = "") String pageNum,HttpSession ses){

		//二十リクエストを考えた実装
		int pageCnt=0;
		if(!pageNum.isBlank()) {
			if(pageNum.equals("next")) {
				pageCnt = Integer.parseInt(ses.getAttribute("pageNum").toString()) + 1;
			}else if(pageNum.equals("prev")) {
				pageCnt = Integer.parseInt(ses.getAttribute("pageNum").toString()) - 1;
			}else {
				pageCnt = Integer.parseInt(pageNum);
			}
		}

		System.out.println(ComMessage.APPS_SEARCH_PAGEING_START);
		List<App> apps;
		if(ses.getAttribute("searchSelect") == null) {
			apps = appservice.findAll(pageCnt);
		}else {
			AppSearchModel appserch = (AppSearchModel) ses.getAttribute("searchSelect");
			apps = appservice.findDetail(appserch,pageCnt);
		}
		ses.setAttribute("pageNum",String.valueOf(pageCnt));
		System.out.println(ComMessage.APPS_SEARCH_PAGEING_END);
		return apps;
	}

}
