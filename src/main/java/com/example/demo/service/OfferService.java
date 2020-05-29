package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.App;
import com.example.demo.domain.Offer;
import com.example.demo.repository.AppRepository;
import com.example.demo.repository.OfferRepository;

@Service
public class OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private AppRepository appRepository;


	//応募データinsert
	public boolean insert(int appid,String userId) {

		boolean chkInsert = false;
		App app = appRepository.findByAppId(appid);

		if(app != null) {
			Offer of = new Offer(app.getAppName(),userId,app.getAppId(),app.getCompanyId(),"99999999");
			of = offerRepository.save(of);

			if(of != null) {
				chkInsert = true;
			}
		}

		return chkInsert;
	}

}
