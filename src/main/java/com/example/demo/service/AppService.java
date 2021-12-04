package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.App;
import com.example.demo.model.AppSearchModel;
import com.example.demo.repository.AppRepository;

//求人DBとコントローラーとのやり取りのクラス
@Service
@Transactional
public class AppService {

	private final String DELFLG = "Y";

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private EntityManager entyty;

    public List<App> findAll(int pageNum) {

        return appRepository.appFind(entyty,pageNum);
    }

	public App findApp(int app_id) {

		App apps = entyty.find(App.class,app_id);

		return apps;
	}

	public List<App> findDetail(AppSearchModel appsearch,int pageNum){
		List<App> apps = appRepository.appFindDetail(appsearch,entyty,pageNum);
		return apps;
	}


}
