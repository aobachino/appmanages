package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.App;
import com.example.demo.domain.Offer;
import com.example.demo.domain.User;
import com.example.demo.repository.AppRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.repository.UserRepository;

//応募管理DBとコントローラーの受け渡し
@Service
public class OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private AppRepository appRepository;

	@Autowired
	private UserRepository userRepository;


	/*
	 * 求人Insert
	 * 定員数の変更の改善が必要
	 * 定員数が0の場合のメッセージを返す
	 */
	public synchronized  int offerInsert(int appid,String userId) {

		int chkInsert = 0;

		List<App> appList = appRepository.findByAppIdAndDelflg(appid,"Y");
		User user = userRepository.findByUserId(userId);

		if(appList.size() != 0 && user != null) {
			App app = appList.get(0);
			if(app.getCapacity() != 0) {
				Offer of = new Offer(app.getAppName(),userId,app.getAppId(),app.getCompanyId(),"99999999");
				of = offerRepository.save(of);

				if(of != null) {

					int cap = app.getCapacity() - 1;

					if(cap <= 0) {
						cap = 0;
						app.setDelflg("N");
					}

					app.setCapacity(cap);
					app = appRepository.save(app);

					return chkInsert;
				}
			}
			chkInsert = 1;
			return chkInsert;/* インサート時のエラー */
		}
		chkInsert = 2;
		return chkInsert;/* 定員数がいっぱい、ユーザーが誰だかわからない、求人が取得できない */

	}
}
