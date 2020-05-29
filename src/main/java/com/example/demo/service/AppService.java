package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.App;
import com.example.demo.repository.AppRepository;

@Service
@Transactional
public class AppService {

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private EntityManager entyty;

    public List<App> findAll() {
        return appRepository.findAll();
    }

	public App findApp(int app_id) {
		// TODO 自動生成されたメソッド・スタブ
		App apps = entyty.find(App.class,app_id);
//		appList.add(apps.getApplication_name());
//		appList.add(apps.getCompany_name());
//		appList.add(apps.getApplocation_explain());
//		appList.add(apps.get_to());

		return apps;
	}


}
