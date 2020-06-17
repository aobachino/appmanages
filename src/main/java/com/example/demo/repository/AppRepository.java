package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.App;
import com.example.demo.model.AppSearchModel;

@Repository
public interface AppRepository  extends JpaRepository<App, Integer> {


	App findByAppId(int id);

	List<App> findByDelflg(String flg);

	public final String  STR_SQL = "select * from application where 1=1";
	default public List<App> appFindDetail(AppSearchModel appmo, EntityManager entyty){

		String sqlStr = makeQuery(appmo);
		Query query = entyty.createNativeQuery(sqlStr,App.class);
		List<App> apps;

		if(appmo.getOccupation() != null) {
			query.setParameter("occupationId", appmo.getOccupation());
		}
	    apps = query.getResultList();

		return apps;
	}

	default public String makeQuery(AppSearchModel appmo) {
		StringBuffer sb = new StringBuffer(STR_SQL);

//		if(appmo.getIndustry() != null) {
//			sb.append(" and industry_id = :industryId");
//		}

		if(appmo.getOccupation() != null) {
			sb.append(" and occupation_id = :occupationId");
		}

		String str = sb.toString();

		return str;
	}
}
